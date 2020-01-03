<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="jobId"/>

	<acme:form-textbox code="worker.application.form.reference" path="reference" placeholder="EMP1-JOB1:WORK1"/>	
	<jstl:if test="${command == 'show'}">
		<acme:form-moment code="worker.application.form.moment" path="moment"/>	
		<acme:form-textbox code="worker.application.form.status" path="status"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:form-hidden path="status"/>	
	</jstl:if>
	<acme:form-textarea code="worker.application.form.statement" path="statement"/>
	<acme:form-textarea code="worker.application.form.skills" path="skills"/>
	<acme:form-textarea code="worker.application.form.qualifications" path="qualifications"/>
	
	<!-- xxxxApplication -->
	<acme:message code="worker.application.xxxxApplication"/>
	<acme:form-textbox code="worker.application.form.answer" path="xxxxApplication.answer" readonly="${command != 'create'}"/>
	<acme:form-textbox code="worker.application.form.xxxx" path="xxxxApplication.xxxx" readonly="${command != 'create'}"/>
	<jstl:if test="${command == 'create'}">
		<acme:form-password code="worker.application.form.password" path="xxxxApplication.password"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" 
	    code="worker.application.form.button.create" action="/worker/application/create?jobId=${jobId}"/>
	    
	<jstl:if test="${command == 'show'}">
    	<acme:form-panel code="worker.application.form.job">
    		<acme:form-textbox code="worker.application.form.job.reference" path="job.reference"/>
    		<acme:form-textbox code="worker.application.form.job.title" path="job.title"/>
    	</acme:form-panel>
    	<acme:form-panel code="worker.application.form.resolution">
    		<jstl:if test="${status != 'PENDING'}">
				<acme:form-moment readonly="true" code="worker.application.form.resolutionMoment" path="resolutionMoment"/>
			</jstl:if>
			<acme:form-textarea code="worker.application.form.resolutionJustification" path="resolutionJustification"/>
		</acme:form-panel>
	</jstl:if>

	<acme:form-return code="worker.application.form.return"/>	
</acme:form>