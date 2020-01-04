
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneApplicationById(int id);

	@Query("select a from Application a where a.job.employer.id = ?1")
	Collection<Application> findManyByEmployerId(int employerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.reference")
	Collection<Application> findAppToMyJobsGroupByReference(int employerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.status")
	Collection<Application> findAppToMyJobsGroupByStatus(int employerId);

	@Query("select a from Application a where a.job.employer.id = ?1 order by a.moment")
	Collection<Application> findAppToMyJobsGroupByCreationMoment(int employerId);

	@Query("select count(a.xxxxApplication) from Application a where a.id = ?1")
	Integer findXxxxApplicationByApplication(int applicationId);

	@Query("select count(a.xxxxApplication.password) from Application a where a.id = ?1")
	Integer findPasswordByApplication(int applicationId);

	@Query("select count(a) from Application a where a.id = ?1 and a.xxxxApplication.password = ?2")
	Integer findPasswordByApplicationId(int applicationId, String password);

	@Query("select a.xxxxApplication.xxxx from Application a where a.id = ?1")
	String findXxxxByApplicationId(int applicationId);

}
