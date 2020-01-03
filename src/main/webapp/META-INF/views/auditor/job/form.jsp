<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:form>	
	
	<jstl:if test="${status == 'PUBLISHED' }">
		
		<acme:message code="auditor.job.employer"/>
		<acme:form-textbox code="auditor.job.form.employer" path="employer.userAccount.username" readonly ="true"/>
		
		<acme:message code="auditor.job.title"/>
		
		<acme:form-textbox code="auditor.job.form.reference" path="reference" placeholder="EEEE-JJJJ" readonly ="true"/>
		
		<acme:form-textbox code="auditor.job.form.title" path="title" readonly="true"/>
		<acme:form-moment code="auditor.job.form.deadline" path="deadline" readonly="true"/>
		<acme:form-money code="auditor.job.form.salary" path="salary" placeholder="30000 EUR" readonly="true"/> 
		<acme:form-url code="auditor.job.form.moreInfo" path="moreInfo" readonly="true"/>
		<acme:form-textbox code="auditor.job.form.status" path="status" readonly="true"/>
		
		<acme:message code="auditor.descriptor.title"/>
		
		<acme:form-textarea code="auditor.job.form.description" path="description" readonly="true"/>
		
		<jstl:if test="${haveXxxx}">
			<acme:message code="auditor.xxxx.title"/>
	
			<acme:form-textbox code="auditor.job.form.pieceOfText" path="xxxx.pieceOfText" readonly="true"/>
			<acme:form-url code="auditor.job.form.linkInfo" path="xxxx.linkInfo" readonly="true"/>
		</jstl:if>
			
		<jstl:if test="${valid == true }">
			<acme:redirect-button code="auditor.job.redirect.create" action="/auditor/audit-records/create?jobId=${id}"/>
		</jstl:if>
		
		<acme:redirect-button code="employer.job.redirect.listDuties" action="/auditor/duty/list?descriptorId=${descriptorId}"/>
		<acme:redirect-button code="employer.job.redirect.auditRecord" action="/auditor/audit-records/list?jobId=${id}"/>
			
		<acme:form-return code="employer.job.form.return"/>	
	</jstl:if>
</acme:form>



	
	

