
package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.jobs.Xxxx;
import acme.entities.roles.Employer;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		boolean result = true;

		//Assure this is the owner of the job
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();

		result = result && employer.getUserAccount().getId() == principal.getAccountId();

		// It is not Published
		result = result && job.getStatus() == Status.DRAFT;

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

		if (request.isMethod(HttpMethod.POST)) {

			Collection<Worker> workers;
			workers = this.repository.findWorkersByJob(request.getModel().getInteger("id"));
			boolean haveApplications = workers.size() > 0 ? true : false;
			request.getModel().setAttribute("haveApplications", haveApplications);
			Integer i = this.repository.findXxxxByJob(request.getModel().getInteger("id"));
			boolean haveXxxx = i != 0;
			request.getModel().setAttribute("haveXxxx", haveXxxx);
		}
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "status", "salary", "moreInfo", "employer");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAfter, inEuros, positive, hasDescriptor, duties100, descriptionNotBlank, isSpam;
		//Xxxx
		if (entity.getXxxx() != null) {
			if (!errors.hasErrors("xxxx.pieceOfText")) {
				boolean pieceOfTextBlank = !request.getModel().getString("xxxx.pieceOfText").trim().isEmpty();
				errors.state(request, pieceOfTextBlank, "xxxx.pieceOfText", "employer.job.form.error.xxxxPieceOfTextBlank");
			}
		}
		//DEADLINE
		if (!errors.hasErrors("deadline")) {

			isAfter = entity.getDeadline().after(new Date(System.currentTimeMillis()));
			errors.state(request, isAfter, "deadline", "employer.job.error.deadline-must-be-in-the-future");
		}
		//Salary
		if (!errors.hasErrors("salary")) {

			inEuros = entity.getSalary().getCurrency().equals("€") || entity.getSalary().getCurrency().equals("EUR");
			errors.state(request, inEuros, "salary", "employer.job.form.error.salary-in-euros");

			positive = entity.getSalary().getAmount() >= 0.;
			errors.state(request, positive, "salary", "employer.job.form.error.salary-not-positive");
		}
		//Description
		if (!errors.hasErrors("description")) {
			descriptionNotBlank = request.getModel().getString("description") != "";
			if (!descriptionNotBlank) {
				String errorMsg = request.getLocale().getDisplayLanguage().equals("English") ? "The description can not be empty" : "La descripción no puede estar vacía";
				errors.add("description", errorMsg);
			}
		}

		// Can it be saved in final Mode?
		if (!errors.hasErrors("status")) {

			if (entity.getStatus() == Status.PUBLISHED) {
				// Has descriptor
				hasDescriptor = this.repository.findOneDescriptorByJobId(entity.getId()) != null;
				errors.state(request, hasDescriptor, "status", "employer.job.form.error.dont-have-descriptor");

				//Duties sum up to 100%
				duties100 = false;
				Double sumDuties = this.repository.sumPercentagesDutiesByJobId(entity.getId());

				if (sumDuties != null) {
					duties100 = sumDuties == 100;
				}
				errors.state(request, duties100, "status", "employer.job.form.error.duties-100");

				//Not Spam
				isSpam = false;
				Collection<Duty> duties = this.repository.findManyDutiesByJobId(entity.getId());
				String descriptionText = " ";
				String dutiesText = " ";

				if (hasDescriptor) {
					descriptionText += this.repository.findOneDescriptorByJobId(entity.getId()).getDescription();
				}
				if (duties != null) {
					for (Duty d : duties) {
						dutiesText += d.getTitleDuty() + " ";
						dutiesText += d.getDescriptionDuty() + " ";
					}
				}

				String text = entity.getTitle() + descriptionText + dutiesText;

				isSpam = this.isSpam(text);

				errors.state(request, !isSpam, "status", "employer.job.form.error.isSpam");

				//If it has errors, put it in draft so you can edit it
				if (!hasDescriptor || !duties100 || isSpam) {
					request.getModel().setAttribute("status", Status.DRAFT);
				}
			}
		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		if (entity.getXxxx() != null) {
			Xxxx x = entity.getXxxx();
			x.setPieceOfText(request.getModel().getString("xxxx.pieceOfText"));
			x.setLinkInfo(request.getModel().getString("xxxx.linkInfo"));

			this.repository.save(x);
		}

		Descriptor d = this.repository.findOneDescriptorByJobId(entity.getId());
		d.setDescription(request.getModel().getString("description"));
		this.repository.save(d);

		this.repository.save(entity);
	}

	private boolean isSpam(final String text) {

		// Put in repository:
		// @Query("select cp from CustomisationParameters cp where cp.identifier = '1'")
		// CustomisationParameters findOneCustomisationParameters();

		boolean result = false;
		List<String> spamList = new ArrayList<String>();
		Float spamThreshold;
		CustomisationParameters cp = this.repository.findOneCustomisationParameters();
		float count = 0;

		spamList = Arrays.asList(cp.getSpamList().trim().split(";"));
		spamThreshold = cp.getSpamThreshold();

		for (String s : spamList) {
			count += StringUtils.countOccurrencesOf(text.toLowerCase(), s);
		}

		float numberWords = text.trim().split(" ").length;
		float spamPorcentage = count / numberWords;

		result = spamPorcentage >= spamThreshold;

		return result;
	}

}
