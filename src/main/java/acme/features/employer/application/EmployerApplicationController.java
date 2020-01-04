
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.jobs.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {

	@Autowired
	private EmployerApplicationShowService						showService;

	@Autowired
	private EmployerApplicationListToMyJobsService				listToMyJobsService;

	@Autowired
	private EmployerApplicationListToMyJobByReferenceService	listAppByReferenceService;

	@Autowired
	private EmployerApplicationListToMyJobByStatusService		listAppByStatusService;

	@Autowired
	private EmployerApplicationListToMyJobByCreationService		listAppByCreationService;

	@Autowired
	private EmployerApplicationAcceptService					acceptService;

	@Autowired
	private EmployerApplicationRejectService					rejectService;

	@Autowired
	private EmployerApplicationUpdateService					updateService;


	@PostConstruct
	private void initialise() {

		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.LIST_TO_MY_JOBS, BasicCommand.LIST, this.listToMyJobsService);

		super.addCustomCommand(CustomCommand.LIST_MINE_BY_REFERENCE, BasicCommand.LIST, this.listAppByReferenceService);

		super.addCustomCommand(CustomCommand.LIST_MINE_BY_STATUS, BasicCommand.LIST, this.listAppByStatusService);

		super.addCustomCommand(CustomCommand.LIST_MINE_BY_CREATION, BasicCommand.LIST, this.listAppByCreationService);

		super.addCustomCommand(CustomCommand.ACCEPT_APPLICATION, BasicCommand.UPDATE, this.acceptService);

		super.addCustomCommand(CustomCommand.REJECT_APPLICATION, BasicCommand.UPDATE, this.rejectService);

		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
