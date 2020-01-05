/*
 * CustomCommand.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.components;

import acme.framework.components.Command;

public enum CustomCommand implements Command {
	LIST_FIVESTARS, LIST_MINE, LIST_TO_MY_JOBS, ACCEPT_APPLICATION, REJECT_APPLICATION, CREATE_COMMERCIAL, CREATE_NON_COMMERCIAL, UPDATE_COMMERCIAL, UPDATE_NON_COMMERCIAL, LIST_MINE_BY_REFERENCE, LIST_MINE_BY_STATUS, LIST_MINE_BY_CREATION
}
