
package acme.features.employer.application;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Application;
import acme.entities.jobs.ApplicationStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationRejectService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int applicationId;
		Application application;
		Employer employer;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(applicationId);
		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId() && application.getStatus().equals(ApplicationStatus.PENDING);

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (request.isMethod(HttpMethod.POST)) {
			Integer applicationId = request.getModel().getInteger("id");
			Application application = this.repository.findOneApplicationById(applicationId);
			ApplicationStatus status = application.getStatus();

			request.getModel().setAttribute("appStatus", status);
			request.getModel().setAttribute("havePassword", this.repository.findPasswordByApplication(request.getModel().getInteger("id")) != 0);
		}

		request.bind(entity, errors, "reference", "moment", "status", "statement", "skills", "qualifications", "job.reference", "job.title", "worker.userAccount.identity.name", "worker.userAccount.identity.surname");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "resolutionJustification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("resolutionJustification")) {
			boolean notBlank;
			String resolution = entity.getResolutionJustification();
			notBlank = resolution != null && !resolution.trim().isEmpty();
			errors.state(request, notBlank, "resolutionJustification", "employer.application.error.justification-is-mandatory-when-rejecting");
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		entity.setResolutionJustification(entity.getResolutionJustification().trim());
		entity.setStatus(ApplicationStatus.REJECTED);
		entity.setResolutionMoment(Calendar.getInstance().getTime());

		this.repository.save(entity);
	}

}
