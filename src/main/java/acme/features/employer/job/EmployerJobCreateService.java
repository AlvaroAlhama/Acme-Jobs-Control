
package acme.features.employer.job;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.jobs.Xxxx;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "status", "employer");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Job result;
		result = new Job();
		Employer employer;
		employer = new Employer();
		Xxxx xxxx;
		xxxx = new Xxxx();

		int idEmployer = request.getPrincipal().getActiveRoleId();
		employer = this.repository.findOneEmployerById(idEmployer);

		result.setEmployer(employer);
		result.setStatus(Status.DRAFT);
		result.setXxxx(xxxx);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isAfter, uniqueReference, inEuros, positive, descriptionNotBlank, pieceOfTextBlank;

		//Deadline
		if (!errors.hasErrors("deadline")) {
			isAfter = entity.getDeadline().after(new Date(System.currentTimeMillis()));
			errors.state(request, isAfter, "deadline", "employer.job.error.deadline-must-be-in-the-future");
		}
		//Reference
		if (!errors.hasErrors("reference")) {
			uniqueReference = this.repository.findOneJobByReference(entity.getReference()) == null;
			errors.state(request, uniqueReference, "reference", "employer.job.form.error.uniqueReference");
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
		//Xxxx
		if (!errors.hasErrors("xxxx.pieceOfText")) {
			if (!request.getModel().getString("xxxx.linkInfo").trim().isEmpty()) {
				pieceOfTextBlank = !request.getModel().getString("xxxx.pieceOfText").trim().isEmpty();
				errors.state(request, pieceOfTextBlank, "xxxx.pieceOfText", "employer.job.form.error.pieceOfTextBlank");
			}
		}

	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		if (!request.getModel().getString("xxxx.pieceOfText").trim().isEmpty()) {
			Xxxx xxxx = new Xxxx();
			xxxx.setPieceOfText(request.getModel().getString("xxxx.pieceOfText"));
			xxxx.setLinkInfo(request.getModel().getString("xxxx.linkInfo"));

			this.repository.save(xxxx);

			entity.setXxxx(xxxx);
		} else {
			entity.setXxxx(null);
		}

		this.repository.save(entity);

		Descriptor d = new Descriptor();
		d.setJob(entity);
		d.setDescription(request.getModel().getString("description"));
		this.repository.save(d);
	}

}
