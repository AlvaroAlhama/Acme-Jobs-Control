
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Application;
import acme.entities.jobs.ApplicationStatus;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.jobs.XxxxApplication;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result = true;

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("jobId", request.getModel().getInteger("jobId"));
			model.setAttribute("haveXxxx", this.repository.findXxxxByJob(request.getModel().getInteger("jobId")) != 0);
		}
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result = new Application();
		XxxxApplication xxxxApplication = new XxxxApplication();

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findOneJobById(jobId);
		result.setJob(job);

		int workerId = request.getPrincipal().getActiveRoleId();
		Worker worker = this.repository.findOneWorkerById(workerId);

		result.setQualifications(worker.getQualifications());
		result.setSkills(worker.getSkills());
		result.setWorker(worker);
		result.setStatus(ApplicationStatus.PENDING);
		result.setXxxxApplication(xxxxApplication);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		//No descriptor already created
		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findOneJobById(jobId);

		//we can't show the error because published is not a field shown in the form
		if (!errors.hasErrors("jobId")) {
			boolean published;
			published = job.getStatus() == Status.PUBLISHED;
			errors.state(request, published, "jobId", "worker.published.error.not-published");
		}
		if (!errors.hasErrors("jobId")) {
			boolean deadline;
			Date currentTime = new Date(System.currentTimeMillis());
			deadline = job.getDeadline().after(currentTime);
			errors.state(request, deadline, "jobId", "worker.published.error.not-published");
		}
		//Reference
		if (!errors.hasErrors("reference")) {
			boolean uniqueReference;
			uniqueReference = this.repository.findOneApplicationByReference(entity.getReference()) == null;
			errors.state(request, uniqueReference, "reference", "worker.application.form.error.uniqueReference");
		}
		//xxxxApplication
		if (!errors.hasErrors("xxxxApplication.password")) {
			Boolean answerBlank = true;
			Boolean xxxxBlank = true;
			String answer = request.getModel().getString("xxxxApplication.answer");
			String xxxx = request.getModel().getString("xxxxApplication.xxxx");

			if (!request.getModel().getString("xxxxApplication.password").trim().isEmpty()) {
				xxxxBlank = !xxxx.trim().isEmpty();
				answerBlank = !answer.trim().isEmpty();
			} else {
				if (!xxxx.trim().isEmpty()) {
					answerBlank = !answer.trim().isEmpty();
				}
			}
			if (!answer.isEmpty()) {
				answerBlank = !answer.trim().isEmpty();
			}
			if (!xxxx.isEmpty()) {
				xxxxBlank = !xxxx.trim().isEmpty();
			}

			errors.state(request, answerBlank, "xxxxApplication.answer", "worker.application.form.error.answerBlank");
			errors.state(request, xxxxBlank, "xxxxApplication.xxxx", "worker.application.form.error.xxxxBlank");
		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		if (!request.getModel().getString("xxxxApplication.answer").trim().isEmpty()) {
			XxxxApplication xxxxApplication = new XxxxApplication();
			xxxxApplication.setAnswer(request.getModel().getString("xxxxApplication.answer"));
			xxxxApplication.setXxxx(request.getModel().getString("xxxxApplication.xxxx"));
			xxxxApplication.setPassword(request.getModel().getString("xxxxApplication.password"));

			this.repository.save(xxxxApplication);

			entity.setXxxxApplication(xxxxApplication);
		} else {
			entity.setXxxxApplication(null);
		}

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
