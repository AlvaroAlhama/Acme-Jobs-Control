
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.jobs.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.reference = ?1")
	Object findOneApplicationByReference(String reference);

	@Query("select a from Application a where a.worker.id = ?1")
	Collection<Application> findManyByWorkerId(int workerId);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int jobId);

	@Query("select w from Worker w where w.id = ?1")
	Worker findOneWorkerById(int workerId);

	@Query("select count(j.xxxx) from Job j where j.id = ?1")
	Integer findXxxxByJob(int jobId);

	@Query("select count(a.xxxxApplication) from Application a where a.id = ?1")
	Integer findXxxxApplicationByApplication(int applicationId);

	@Query("select count(a.xxxxApplication.password) from Application a where a.id = ?1")
	Integer findPasswordByApplication(int applicationId);

	@Query("select count(a) from Application a where a.id = ?1 and a.xxxxApplication.password = ?2")
	Integer findPasswordByApplicationId(int applicationId, String password);

	@Query("select a.xxxxApplication.xxxx from Application a where a.id = ?1")
	String findXxxxByApplicationId(int applicationId);
}
