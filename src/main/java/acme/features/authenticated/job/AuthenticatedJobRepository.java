
package acme.features.authenticated.job;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Descriptor;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.deadline > ?1 and j.status = 1")
	Collection<Job> findManyActiveJobs(Date d);

	@Query("select j.job from Descriptor j where j.job.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Descriptor j where j.job.id = ?1")
	Descriptor findOneDescriptorById(int jobId);

	@Query("select count(j.xxxx) from Job j where j.id = ?1")
	Integer findXxxxByJob(int jobId);

}
