
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	//Tablero Sector

	@Query("select distinct r.sector from InvestorRecord r order by r.sector")
	String[] investorSector();

	@Query("select count(r) from InvestorRecord r group by r.sector order by r.sector")
	String[] dataInvestor();

	@Query("select distinct c.workSector from CompanyRecords c where c.workSector not in (?1)")
	String[] companySector(String[] sector);

	@Query("select workSector,count(c) from CompanyRecords c group by c.workSector")
	String[] dataCompany();

	//Tablero Status Jobs

	@Query("select distinct j.status from Job j order by j.status")
	String[] jobStatus();

	@Query("select count(j) from Job j group by j.status order by j.status")
	String[] dataJob();

	//Tablero Status Application

	@Query("select distinct a.status from Application a order by a.status")
	String[] applicationStatus();

	@Query("select count(a) from Application a group by a.status order by a.status")
	String[] dataApplication();

	//Tablero D05

	@Query("select count(a) FROM Application a WHERE a.status = 0 and datediff(current_date(),a.moment)<28 group by DATE(a.moment) ORDER BY DATE(a.moment)")
	Integer[] pendingApplicationsValue();

	@Query("select DATE(a.moment) FROM Application a WHERE a.status = 0 and datediff(current_date(),a.moment)<28 group by DATE(a.moment) ORDER BY DATE(a.moment)")
	String[] pendingApplicationsLabels();

	@Query("select count(a) FROM Application a WHERE a.status = 1 and datediff(current_date(),a.resolutionMoment)<28 group by DATE(a.resolutionMoment) ORDER BY DATE(a.resolutionMoment)")
	Integer[] acceptedApplicationsValue();

	@Query("select DATE(a.resolutionMoment) FROM Application a WHERE a.status = 1 and datediff(current_date(),a.resolutionMoment)<28 group by DATE(a.resolutionMoment) ORDER BY DATE(a.resolutionMoment)")
	String[] acceptedApplicationsLabels();

	@Query("select count(a) FROM Application a WHERE a.status = 2 and datediff(current_date(),a.resolutionMoment)<28 group by DATE(a.resolutionMoment) ORDER BY DATE(a.resolutionMoment)")
	Integer[] rejectedApplicationsValue();

	@Query("select DATE(a.resolutionMoment) FROM Application a WHERE a.status = 2 and datediff(current_date(),a.resolutionMoment)<28 group by DATE(a.resolutionMoment) ORDER BY DATE(a.resolutionMoment)")
	String[] rejectedApplicationsLabels();

	//Listing D02:

	@Query("select count(r) from Announcement r")
	Integer countAllAnnouncement();

	@Query("select count(r) from CompanyRecords r")
	Integer countAllCompanyRecord();

	@Query("select count(r) from InvestorRecord r")
	Integer countAllInvestorRecord();

	@Query("select max(r.maxReward.amount) from Offer r where r.deadline > ?1")
	Double getMaxOffer(Date d);

	@Query("select min(r.minReward.amount) from Offer r where r.deadline > ?1")
	Double getMinOffer(Date d);

	@Query("select max(r.reward.amount) from Request r where r.deadline > ?1")
	Double getMaxRequest(Date d);

	@Query("select min(r.reward.amount) from Request r where r.deadline > ?1")
	Double getMinRequest(Date d);

	@Query("select avg(r.reward.amount) from Request r where r.deadline > ?1")
	Double getAvgRequest(Date d);

	@Query("select stddev(r.reward.amount) from Request r where r.deadline > ?1")
	Double getStdRequest(Date d);

	//Querys no directas D02:

	@Query("select avg(r.maxReward.amount) from Offer r where r.deadline > ?1")
	Double getMaxAvgOffer(Date d);

	@Query("select avg(r.minReward.amount) from Offer r where r.deadline > ?1")
	Double getMinAvgOffer(Date d);

	@Query("select stddev(r.maxReward.amount) from Offer r where r.deadline > ?1")
	Double getStdMaxOffer(Date d);

	@Query("select stddev(r.minReward.amount) from Offer r where r.deadline > ?1")
	Double getStdMinOffer(Date d);

	//Listing D04:
	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double avgNumberJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
	Double avgNumberApplicationsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double avgNumberApplicationsPerWorker();

	//Control

	@Query("select count(j.xxxx.id)/count(j.id)*100.0 from Job j")
	Double ratioJobsWithXxxx();

	//select count(distinct a.job.id)/(select count(j.xxxx) from Job j)*100.0 from Application a where a.xxxxApplication.id IS NOT NULL Si no es cada challenge
	@Query("select count(a.xxxxApplication)/(select count(j.xxxx) from Job j)*100.0 from Application a")
	Double ratioXxxxWithXxxxApplication();

	@Query("select count(x.password)/(select count(a.id) from Application a)*100.0 from XxxxApplication x")
	Double ratioApplicationsWithXxxxApplication();

}
