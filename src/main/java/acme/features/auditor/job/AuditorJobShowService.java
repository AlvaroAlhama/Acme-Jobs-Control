
package acme.features.auditor.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	@Autowired
	AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Integer jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJob(jobId);

		return job.getStatus() == Status.PUBLISHED;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Integer jobId = request.getModel().getInteger("id");
		Integer auditorId = request.getPrincipal().getActiveRoleId();

		Boolean valid = this.repository.checkIfAuditRecordExist(jobId, auditorId) == 0;
		request.getServletRequest().setAttribute("valid", valid);

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "status", "employer.userAccount.username", "xxxx.pieceOfText", "xxxx.linkInfo");

		Descriptor desc = this.repository.findOneDescriptor(jobId);
		model.setAttribute("description", desc.getDescription());
		model.setAttribute("descriptorId", desc.getId());

		Integer i = this.repository.findXxxxByJob(request.getModel().getInteger("id"));
		boolean haveXxxx = i != 0;
		model.setAttribute("haveXxxx", haveXxxx);
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job j;
		int id;
		id = request.getModel().getInteger("id");

		j = this.repository.findOne(id);

		return j;
	}
}
