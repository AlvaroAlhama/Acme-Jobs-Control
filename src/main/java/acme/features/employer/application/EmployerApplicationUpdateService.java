
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

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
		result = employer.getUserAccount().getId() == principal.getAccountId();

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

		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications", "resolutionJustification", "resolutionMoment", "job.reference", "job.title", "worker.userAccount.identity.name",
			"worker.userAccount.identity.surname", "job.xxxx.pieceOfText", "job.xxxx.linkInfo", "xxxxApplication.answer");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("haveXxxx", this.repository.findXxxxApplicationByApplication(request.getModel().getInteger("id")) != 0);
			model.setAttribute("havePassword", this.repository.findPasswordByApplication(request.getModel().getInteger("id")) != 0);
		}

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

		int appId = request.getModel().getInteger("id");

		if (!request.getModel().getString("xxxxApplication.password").trim().isEmpty()) {
			Integer password = this.repository.findPasswordByApplicationId(appId, request.getModel().getString("xxxxApplication.password"));
			boolean isPassword = password == 1;
			if (isPassword) {
				request.getModel().setAttribute("correctPassword", true);
				request.getModel().setAttribute("xxxxApplication.xxxx", this.repository.findXxxxByApplicationId(appId));
				errors.state(request, !isPassword, "xxxxApplication.password", "employer.application.error.correctPassword");
			} else {
				request.getModel().setAttribute("wrongPassword", true);
				errors.state(request, isPassword, "xxxxApplication.password", "employer.application.error.wrongPassword");
			}
		} else if (request.getModel().getString("xxxxApplication.password").trim().isEmpty()) {
			request.getModel().setAttribute("wrongPassword", true);
			errors.state(request, false, "xxxxApplication.password", "employer.application.error.wrongPassword");
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		// TODO Auto-generated method stub

	}

}
