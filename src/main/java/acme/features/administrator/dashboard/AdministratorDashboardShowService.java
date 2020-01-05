
package acme.features.administrator.dashboard;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	//Internal state
	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "gridLabels", "dataInvestor", "dataCompany", "statusApplicationLabels", "statusJobLabels", "dataJob", "dataApplication", "pendingApplicationData", "pendingApplicationLabels", "sizePending", "acceptedApplicationData",
			"acceptedApplicationLabels", "sizeAccepted", "maxGraph", "numberAnnouncement", "rejectedApplicationData", "rejectedApplicationLabels", "sizeRejected", "numberCompanyRecords", "numberInvestorRecord", "minimunRewardOffer", "maximunRewardOffer",
			"averageRewardOffer", "minimunRewardRequest", "maximunRewardRequest", "averageRewardRequest", "stdRequest", "stdOffer", "avgNumberJobsPerEmployer", "avgNumberApplicationsPerEmployer", "avgNumberApplicationsPerWorker", "jobsWithXxxx",
			"xxxxWithXxxxApplication", "applicationWithXxxxPassword");
	}

	@SuppressWarnings("deprecation")
	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Date d = Calendar.getInstance().getTime();

		//DO2:
		Dashboard result;
		result = new Dashboard();
		Money maxOffer = new Money();
		Money minOffer = new Money();
		Money avgOffer = new Money();
		Money stdOffer = new Money();
		Money maxRequest = new Money();
		Money minRequest = new Money();
		Money avgRequest = new Money();
		Money stdRequest = new Money();

		//D04:

		//D05:

		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -28);
		Date d2 = now.getTime();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateToStringMin = formatter.format(d2).toString();
		String dateToStringMax = formatter.format(d).toString();

		String[] pendingLabels = this.repository.pendingApplicationsLabels();
		Integer[] pendingValues = this.repository.pendingApplicationsValue();

		if (!ArrayUtils.contains(pendingLabels, dateToStringMin)) {

			pendingLabels = ArrayUtils.add(pendingLabels, 0, dateToStringMin);
			pendingValues = ArrayUtils.add(pendingValues, 0, 0);
		}

		if (!ArrayUtils.contains(pendingLabels, dateToStringMax)) {

			pendingLabels = ArrayUtils.add(pendingLabels, dateToStringMax);
			pendingValues = ArrayUtils.add(pendingValues, 0);
		}

		Integer maxPending = Arrays.stream(pendingValues).max(Comparator.naturalOrder()).get();

		result.setPendingApplicationData(pendingValues);
		result.setPendingApplicationLabels(pendingLabels);
		result.setSizePending(pendingValues.length - 1);

		String[] acceptedLabels = this.repository.acceptedApplicationsLabels();
		Integer[] acceptedValues = this.repository.acceptedApplicationsValue();

		if (!ArrayUtils.contains(acceptedLabels, dateToStringMin)) {

			acceptedLabels = ArrayUtils.add(acceptedLabels, 0, dateToStringMin);
			acceptedValues = ArrayUtils.add(acceptedValues, 0, 0);
		}

		if (!ArrayUtils.contains(acceptedLabels, dateToStringMax)) {

			acceptedLabels = ArrayUtils.add(acceptedLabels, dateToStringMax);
			acceptedValues = ArrayUtils.add(acceptedValues, 0);
		}

		Integer maxAccepted = Arrays.stream(acceptedValues).max(Comparator.naturalOrder()).get();

		result.setAcceptedApplicationData(acceptedValues);
		result.setAcceptedApplicationLabels(acceptedLabels);
		result.setSizeAccepted(acceptedLabels.length - 1);

		String[] rejectedLabels = this.repository.rejectedApplicationsLabels();
		Integer[] rejectedValues = this.repository.rejectedApplicationsValue();

		if (!ArrayUtils.contains(rejectedLabels, dateToStringMin)) {

			rejectedLabels = ArrayUtils.add(rejectedLabels, 0, dateToStringMin);
			rejectedValues = ArrayUtils.add(rejectedValues, 0, 0);
		}

		if (!ArrayUtils.contains(rejectedLabels, dateToStringMax)) {

			rejectedLabels = ArrayUtils.add(rejectedLabels, dateToStringMax);
			rejectedValues = ArrayUtils.add(rejectedValues, 0);
		}

		Integer maxRejected = Arrays.stream(rejectedValues).max(Comparator.naturalOrder()).get();

		result.setRejectedApplicationData(rejectedValues);
		result.setRejectedApplicationLabels(rejectedLabels);
		result.setSizeRejected(rejectedLabels.length - 1);

		Integer[] maxVals = {
			maxAccepted, maxPending, maxRejected
		};
		result.setMaxGraph(Arrays.stream(maxVals).max(Comparator.naturalOrder()).get() + 1);

		//Listing D02:
		result.setNumberAnnouncement(this.repository.countAllAnnouncement());
		result.setNumberCompanyRecords(this.repository.countAllCompanyRecord());
		result.setNumberInvestorRecord(this.repository.countAllInvestorRecord());
		maxOffer.setAmount(this.repository.getMaxOffer(d) != null ? this.repository.getMaxOffer(d) : 0.0);
		maxOffer.setCurrency("EUR");
		result.setMaximunRewardOffer(maxOffer);
		minOffer.setAmount(this.repository.getMinOffer(d) != null ? this.repository.getMinOffer(d) : 0.0);
		minOffer.setCurrency("EUR");
		result.setMinimunRewardOffer(minOffer);
		Double avg = ((this.repository.getMaxAvgOffer(d) != null ? this.repository.getMaxAvgOffer(d) : 0.0) + (this.repository.getMinAvgOffer(d) != null ? this.repository.getMinAvgOffer(d) : 0.0)) / 2;
		avgOffer.setAmount(avg);
		avgOffer.setCurrency("EUR");
		result.setAverageRewardOffer(avgOffer);
		maxRequest.setAmount(this.repository.getMaxRequest(d) != null ? this.repository.getMaxRequest(d) : 0.0);
		maxRequest.setCurrency("EUR");
		result.setMaximunRewardRequest(maxRequest);
		minRequest.setAmount(this.repository.getMinRequest(d) != null ? this.repository.getMinRequest(d) : 0.0);
		minRequest.setCurrency("EUR");
		result.setMinimunRewardRequest(minRequest);
		avgRequest.setAmount(this.repository.getAvgRequest(d) != null ? this.repository.getAvgRequest(d) : 0.0);
		avgRequest.setCurrency("EUR");
		result.setAverageRewardRequest(avgRequest);
		stdRequest.setAmount(this.repository.getStdRequest(d) != null ? this.repository.getStdRequest(d) : 0.0);
		stdRequest.setCurrency("EUR");
		result.setStdRequest(stdRequest);
		Double stdOff = ((this.repository.getStdMaxOffer(d) != null ? this.repository.getStdMaxOffer(d) : 0.0) + (this.repository.getStdMinOffer(d) != null ? this.repository.getStdMinOffer(d) : 0.0)) / 2;
		stdOffer.setAmount(stdOff);
		stdOffer.setCurrency("EUR");
		result.setStdOffer(stdOffer);

		//Listing D04:
		result.setAvgNumberJobsPerEmployer(this.repository.avgNumberJobsPerEmployer() != null ? this.repository.avgNumberJobsPerEmployer() : 0.);
		result.setAvgNumberApplicationsPerEmployer(this.repository.avgNumberApplicationsPerEmployer() != null ? this.repository.avgNumberApplicationsPerEmployer() : 0.);
		result.setAvgNumberApplicationsPerWorker(this.repository.avgNumberApplicationsPerWorker() != null ? this.repository.avgNumberApplicationsPerWorker() : 0.);

		//Tablero Sectores

		String[] iSector = this.repository.investorSector();
		String[] cSector = this.repository.companySector(iSector.length != 0 ? iSector : new String[1]);
		String[] labels = iSector;
		labels = ArrayUtils.addAll(labels, cSector);

		String[] tempDataCompany = this.repository.dataCompany();
		String[] dataCompany = new String[labels.length];

		for (String element : tempDataCompany) {
			String[] s = element.split(",");

			for (int i = 0; i < labels.length; i++) {
				if (labels[i].matches(s[0])) {
					dataCompany[i] = s[1];
				}
			}
		}

		String[] dataInvestor = this.repository.dataInvestor();

		dataInvestor = ArrayUtils.addAll(dataInvestor, new String[labels.length - dataInvestor.length]);

		result.setGridLabels(labels);

		result.setDataCompany(dataCompany);

		result.setDataInvestor(dataInvestor);

		//Tablero Status

		result.setStatusJobLabels(this.repository.jobStatus());

		result.setStatusApplicationLabels(this.repository.applicationStatus());

		result.setDataJob(this.repository.dataJob());

		result.setDataApplication(this.repository.dataApplication());

		//Control

		Double jobsWithXxxx = this.repository.ratioJobsWithXxxx() != null ? this.repository.ratioJobsWithXxxx() : 0.;
		result.setJobsWithXxxx(jobsWithXxxx + "%");
		Double xxxxWithXxxxApplication = this.repository.ratioXxxxWithXxxxApplication() != null ? this.repository.ratioXxxxWithXxxxApplication() : 0.;
		result.setXxxxWithXxxxApplication(xxxxWithXxxxApplication + "%");
		Double applicationWithXxxxApplication = this.repository.ratioApplicationsWithXxxxApplication() != null ? this.repository.ratioApplicationsWithXxxxApplication() : 0.;
		result.setApplicationWithXxxxPassword(applicationWithXxxxApplication + "%");

		return result;
	}

}
