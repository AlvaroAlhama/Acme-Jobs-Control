
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.Xxxx;
import acme.entities.roles.Employer;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select d from Descriptor d where d.job.id = ?1")
	Descriptor findOneDescriptorByJobId(int jobId);

	@Query("select e from Employer e where e.id = ?1")
	Employer findOneEmployerById(int employerId);

	@Query("select a.worker from Application a where a.job.id = ?1")
	Collection<Worker> findWorkersByJob(int jobId);

	@Query("select count(j.xxxx) from Job j where j.id = ?1")
	Integer findXxxxByJob(int jobId);

	@Query("select j.xxxx from Job j where j.id = ?1")
	Xxxx findOneXxxxByJobId(int jobId);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyDutiesByDescriptorId(int descriptorId);

	@Query("select a from AuditRecords a where a.job.id = ?1")
	Collection<AuditRecords> findManyAuditRecordsByJobId(int jobId);

	@Query("select j from Job j where j.reference = ?1")
	Object findOneJobByReference(String reference);

	@Query("select d from Duty d where d.descriptor.job.id = ?1")
	Collection<Duty> findManyDutiesByJobId(int jobId);

	@Query("select sum(d.percentage) from Duty d where d.descriptor.job.id = ?1")
	Double sumPercentagesDutiesByJobId(int jobId);

	@Query("select cp from CustomisationParameters cp where cp.identifier = '1'")
	CustomisationParameters findOneCustomisationParameters();

}
