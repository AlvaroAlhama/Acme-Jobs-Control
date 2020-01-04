<%@page language = "java"%>

<%@taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "acme" tagdir = "/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="jobId"/>

	<acme:form-textbox code="worker.application.form.reference" path="reference" placeholder="EMP1-JOB1:WORK1" readonly="${command != 'create'}"/>	
	<jstl:if test="${command == 'show' || command == 'update'}">
		<acme:form-moment code="worker.application.form.moment" path="moment" readonly="${command != 'create'}"/>	
		<acme:form-textbox code="worker.application.form.status" path="status" readonly="${command != 'create'}"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:form-hidden path="status"/>	
	</jstl:if>
	<acme:form-textarea code="worker.application.form.statement" path="statement" readonly="${command != 'create'}"/>
	<acme:form-textarea code="worker.application.form.skills" path="skills" readonly="${command != 'create'}"/>
	<acme:form-textarea code="worker.application.form.qualifications" path="qualifications" readonly="${command != 'create'}"/>
	
	<!-- xxxxApplication -->
	<jstl:if test="${haveXxxx}">
	<acme:form-hidden path="haveXxxx"/>
		<acme:form-panel code="worker.application.xxxxApplication">
		<acme:form-textbox code="worker.application.form.answer" path="xxxxApplication.answer" readonly="${command != 'create'}"/>
		<jstl:if test="${command == 'create' || (!havePassword && command == 'show') || (correctPassword && command == 'update')}">
			<acme:form-textbox code="worker.application.form.xxxx" path="xxxxApplication.xxxx" readonly="${command != 'create'}"/>
		</jstl:if>
		<jstl:if test="${command == 'create' || (havePassword && command == 'show') || (wrongPassword && command == 'update')}">
			<acme:form-password code="worker.application.form.password" path="xxxxApplication.password"/>
		</jstl:if>
		<jstl:if test="${(havePassword && command == 'show') || (wrongPassword && command == 'update')}">
			<acme:form-submit code="worker.application.button.password" action="/worker/application/update"/>
		</jstl:if>
		</acme:form-panel>
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" 
	    code="worker.application.form.button.create" action="/worker/application/create?jobId=${jobId}"/>
	    
	<jstl:if test="${command == 'show' || command == 'update'}">
    	<acme:form-panel code="worker.application.form.job">
    		<acme:form-textbox code="worker.application.form.job.reference" path="job.reference" readonly="${command != 'create'}"/>
    		<acme:form-textbox code="worker.application.form.job.title" path="job.title" readonly="${command != 'create'}"/>
    	</acme:form-panel>
    	<jstl:if test="${status != 'PENDING'}">
    		<acme:form-panel code="worker.application.form.resolution">
				<acme:form-moment readonly="true" code="worker.application.form.resolutionMoment" path="resolutionMoment"/>
				<acme:form-textarea code="worker.application.form.resolutionJustification" path="resolutionJustification" readonly="true"/>
			</acme:form-panel>
		</jstl:if>
	</jstl:if>

	<acme:form-return code="worker.application.form.return"/>	
</acme:form>