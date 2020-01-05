
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Application;
import acme.entities.roles.Employer;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

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
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications", "resolutionJustification", "resolutionMoment", "job.reference", "job.title", "worker.userAccount.identity.name",
			"worker.userAccount.identity.surname", "job.xxxx.pieceOfText", "job.xxxx.linkInfo", "xxxxApplication.answer");

		if (this.repository.findXxxxApplicationByApplication(request.getModel().getInteger("id")) != 0) {
			request.unbind(entity, model, "xxxxApplication.xxxx");
		}
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

}
