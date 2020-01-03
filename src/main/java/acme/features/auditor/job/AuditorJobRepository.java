
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j WHERE j.id IN(select a.job.id from AuditRecords a where a.auditor.id = ?1)")
	Collection<Job> findMyJobs(int auditorId);

	@Query("select j from Job j WHERE j.id = ?1")
	Job findOneJob(int jobId);

	@Query("select j from Job j WHERE j.status = ?2 AND j.id NOT IN(select a.job.id from AuditRecords a where a.auditor.id = ?1)")
	Collection<Job> findManyJobs(int auditorId, Status status);

	@Query("select j from Job j WHERE j.id = ?1")
	Job findOne(int auditRecordId);

	@Query("select count(j.xxxx) from Job j where j.id = ?1")
	Integer findXxxxByJob(int jobId);

	@Query("select count(a) from AuditRecords a WHERE a.job.id = ?1 AND a.auditor.id = ?2")
	Integer checkIfAuditRecordExist(int jobId, int auditorId);

	@Query("select d from Descriptor d WHERE d.job.id = ?1")
	Descriptor findOneDescriptor(int jobId);

}
