<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:form>
	
	<acme:message code="auditor.job.employer"/>
	<acme:form-textbox code="authenticated.job.form.username" path="employer.userAccount.username" readonly="true"/>

	<acme:message code="auditor.job.title"/>
	
	<acme:form-textbox code="authenticated.job.form.reference" path="reference" placeholder="EEEE-JJJJ"/>
	<acme:form-textbox code="authenticated.job.form.title" path="title"/>
	<acme:form-moment code="authenticated.job.form.deadline" path="deadline"/>
	<acme:form-money code="authenticated.job.form.salary" path="salary"/>
	<acme:form-url code="authenticated.job.form.moreInfo" path="moreInfo"/>
	<acme:form-textbox code="authenticated.job.form.status" path="status"/>
	
	<acme:message code="auditor.descriptor.title"/>
	
	<acme:form-textarea code="authenticated.job.form.description" path="description"/>
	
	<jstl:if test="${haveXxxx}">
		<acme:message code="authenticated.xxxx.title"/>
	
		<acme:form-textbox code="authenticated.job.form.pieceOfText" path="xxxx.pieceOfText" readonly="true"/>
		<acme:form-url code="authenticated.job.form.linkInfo" path="xxxx.linkInfo" placeholder="" readonly="true"/>
	</jstl:if>
	
	<acme:form-return code="authenticated.job.form.return"/>	
</acme:form>
<acme:redirect-button code="authenticated.job.redirect.duties" action="/authenticated/duty/list?jobId=${id}"/>
<acme:redirect-button code="authenticated.job.redirect.auditRecord" action="/authenticated/audit-records/list?id=${id}"/>
<acme:redirect-button code="authenticated.job.redirect.application" action="/worker/application/create?jobId=${id}" access="hasRole('Worker')"/>
