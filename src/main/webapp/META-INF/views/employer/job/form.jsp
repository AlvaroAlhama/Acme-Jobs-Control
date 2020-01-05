<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:form>
	
	<jstl:set var = "rdonly" value ="${status == 'PUBLISHED'}"/>
	
	<acme:message code="auditor.job.title"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="employer.job.form.reference" path="reference" placeholder="EEEE-JJJJ" readonly ="${rdonly}"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="employer.job.form.reference" path="reference" readonly="true"/>
	</jstl:if>
	
	<acme:form-textbox code="employer.job.form.title" path="title" readonly="${rdonly}"/>
	<acme:form-moment code="employer.job.form.deadline" path="deadline" readonly="${rdonly}"/>
	<acme:form-money code="employer.job.form.salary" path="salary" placeholder="30000 EUR" readonly="${rdonly}"/> 
	<acme:form-url code="employer.job.form.moreInfo" path="moreInfo" readonly="${rdonly}"/>
	
	<jstl:if test="${command != 'create'}">
		<jstl:if test="${rdonly == 'true'}">
			<acme:form-textbox code="authenticated.job.form.status" path="status" readonly="${rdonly}"/>
		</jstl:if>
		<jstl:if test="${rdonly != 'true'}">
			<acme:form-select code="authenticated.job.form.status" path="status">
				<acme:form-option code="authenticated.job.form.status.draft" value="DRAFT"/>
				<acme:form-option code="authenticated.job.form.status.published" value="PUBLISHED"/>
			</acme:form-select>
		</jstl:if>
	</jstl:if>
	
	<acme:message code="auditor.descriptor.title"/>
	
	<acme:form-textarea code="employer.job.form.description" path="description" readonly="${rdonly}"/>
	
	<!-- xxxx -->
	<jstl:if test="${haveXxxx || command == 'create'}">
		<acme:form-panel code="employer.xxxx.title">
			<acme:form-textbox code="employer.job.form.pieceOfText" path="xxxx.pieceOfText" readonly="${status == 'PUBLISHED'}"/>
			<acme:form-url code="employer.job.form.linkInfo" path="xxxx.linkInfo" readonly="${status == 'PUBLISHED'}"/>
		</acme:form-panel>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" 
	    code="employer.job.form.button.create" action="/employer/job/create"/>
	<acme:form-submit test="${command == 'show' && status == 'DRAFT'}" 
		code="employer.job.form.button.update" action="/employer/job/update"/>
	<acme:form-submit test="${command == 'update' && status == 'DRAFT'}" 
		code="employer.job.form.button.update" action="/employer/job/update"/>	
	<acme:form-submit test="${(command == 'show' || command == 'update') && haveApplications == 'false'}" 
		code="employer.job.form.button.delete" action="/employer/job/delete"/>
		
	
	<acme:form-return code="employer.job.form.return"/>	
	
</acme:form>

<jstl:if test="${command == 'show' || command == 'update'}">

	<jstl:if test="${status == 'DRAFT'}">
		  <acme:redirect-button code="employer.job.redirect.create-duty" action="/employer/duty/create?jobId=${id}"/>
    </jstl:if>
	<acme:redirect-button code="employer.job.redirect.listDuties" action="/employer/duty/list?jobId=${id}"/>
	<acme:redirect-button code="employer.job.redirect.auditRecord" action="/employer/audit-records/list?jobId=${id}"/>
	
</jstl:if>