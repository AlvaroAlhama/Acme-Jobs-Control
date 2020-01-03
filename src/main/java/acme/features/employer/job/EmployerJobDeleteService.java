
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Xxxx;
import acme.entities.roles.Employer;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		//Asegurarnos que el que lo quiere borrar puede
		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();

		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
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

		request.unbind(entity, model);
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

		//If a worker has applied cannot  be deleted
		Collection<Worker> workers;
		int jobId;

		jobId = request.getModel().getInteger("id");
		workers = this.repository.findWorkersByJob(jobId);

		if (!workers.isEmpty()) {
			errors.state(request, workers.isEmpty(), "reference", "employer.job.error.workers-has-applied");
		}

	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Descriptor descriptor;
		Xxxx xxxx;
		Collection<Duty> duties;
		Collection<AuditRecords> auditRecords;
		int jobId;
		jobId = request.getModel().getInteger("id");

		descriptor = this.repository.findOneDescriptorByJobId(jobId);
		if (descriptor != null) {
			duties = this.repository.findManyDutiesByDescriptorId(descriptor.getId());

			if (duties != null) {
				for (Duty duty : duties) {
					this.repository.delete(duty);
				}
			}
			this.repository.delete(descriptor);
		}

		auditRecords = this.repository.findManyAuditRecordsByJobId(jobId);
		if (auditRecords != null) {
			for (AuditRecords a : auditRecords) {
				this.repository.delete(a);
			}
		}

		xxxx = this.repository.findOneXxxxByJobId(jobId);
		if (xxxx != null) {
			this.repository.delete(xxxx);
		}

		this.repository.delete(entity);
	}

}
