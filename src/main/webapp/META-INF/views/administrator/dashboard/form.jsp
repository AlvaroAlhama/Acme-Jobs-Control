<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<!-- Control -->
	<acme:form-double code="administrator.dashboard.form.label.jobsWithXxxx" path="jobsWithXxxx"/>
	<acme:form-double code="administrator.dashboard.form.label.xxxxWithXxxxApplication" path="xxxxWithXxxxApplication"/>
	<acme:form-double code="administrator.dashboard.form.label.applicationWithXxxxPassword" path="applicationWithXxxxPassword"/>

	<!-- D02: -->
	<acme:form-integer code="administrator.dashboard.form.label.numberAnnouncement" path="numberAnnouncement"/>
	<acme:form-integer code="administrator.dashboard.form.label.numberCompanyRecords" path="numberCompanyRecords"/>
	<acme:form-integer code="administrator.dashboard.form.label.numberInvestorRecords" path="numberInvestorRecord"/>
	<acme:form-money code="administrator.dashboard.form.label.minimunRewardOffer" path="minimunRewardOffer"/>
	<acme:form-money code="administrator.dashboard.form.label.maximunRewardOffer" path="maximunRewardOffer"/>
	<acme:form-money code="administrator.dashboard.form.label.averageRewardOffer" path="averageRewardOffer"/>
	<acme:form-money code="administrator.dashboard.form.label.stdOffer" path="stdOffer"/>
	<acme:form-money code="administrator.dashboard.form.label.minimunRewardRequest" path="minimunRewardRequest"/>
	<acme:form-money code="administrator.dashboard.form.label.maximunRewardRequest" path="maximunRewardRequest"/>
	<acme:form-money code="administrator.dashboard.form.label.averageRewardRequest" path="averageRewardRequest"/>
	<acme:form-money code="administrator.dashboard.form.label.stdRequest" path="stdRequest"/>
	
	<!-- D04: -->
	<acme:form-double code="administrator.dashboard.form.label.avgNumberJobsPerEmployer" path="avgNumberJobsPerEmployer"/>
	<acme:form-double code="administrator.dashboard.form.label.avgNumberApplicationsPerEmployer" path="avgNumberApplicationsPerEmployer"/>
	<acme:form-double code="administrator.dashboard.form.label.avgNumberApplicationsPerWorker" path="avgNumberApplicationsPerWorker"/>
	
	<div>
		<b><acme:message code="administrator.dashboard.form.label.canvasSector"/></b>
		<canvas id="canvasSector"></canvas>
	</div>

	<div>
		<b><acme:message code="administrator.dashboard.form.label.canvasApplicationStatus"/></b>
		<canvas id="canvasApplicationStatus"></canvas>
	</div>

	<div>
		<b><acme:message code="administrator.dashboard.form.label.canvasJobStatus"/></b>
		<canvas id="canvasJobStatus"></canvas>
	</div>
	
	<div>
		<b><acme:message code="administrator.dashboard.form.label.canvasApplication"/></b>
		<canvas id="canvasTest"></canvas>
	</div>
	
</acme:form>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels : [
					<jstl:choose>
						<jstl:when test="${gridLabels} == null">
							""
						</jstl:when>
						<jstl:otherwise>
							<jstl:forEach var="item" items="${gridLabels}">
								<jstl:out value="\"${item}\"" escapeXml="false"/>,
							</jstl:forEach>						
						</jstl:otherwise>
					</jstl:choose>
				],
				datasets : [
					{
						label : <acme:message code="administrator.dashboard.form.label.investor"/>,
						backgroundColor : "rgba(22, 38, 212, 0.3)",
						borderColor : "rgba(22, 38, 212, 1)",
						data : [
							
							<jstl:choose>
								<jstl:when test="${dataInvestor} == null">
									""
								</jstl:when>
								<jstl:otherwise>
									<jstl:forEach var="item" items="${dataInvestor}">
										<jstl:out value="\"${item}\"" escapeXml="false"/>,
									</jstl:forEach>				
								</jstl:otherwise>
							</jstl:choose>							
							
						]
					},{
						label : <acme:message code="administrator.dashboard.form.label.company"/>,
						backgroundColor : "rgba(19, 157, 16, 0.3)",
						borderColor : "rgba(19, 157, 16, 1)",
						data : [
							
								<jstl:choose>
									<jstl:when test="${dataCompany} == null">
										""
									</jstl:when>
									<jstl:otherwise>
										<jstl:forEach var="item" items="${dataCompany}">
											<jstl:out value="\"${item}\"" escapeXml="false"/>,
										</jstl:forEach>				
									</jstl:otherwise>
								</jstl:choose>							
						]
					}
				]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							min : 0,
							stepSize : 1,
							autoSkip : true
						}
					}
				]
			},
			legend : {
				display : true
			}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvasSector");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
	$(document).ready(function(){
		var data = {
				labels : [
					<jstl:choose>
						<jstl:when test="${statusApplicationLabels} == null">
							""
						</jstl:when>
						<jstl:otherwise>
							<jstl:forEach var="item" items="${statusApplicationLabels}">
								<jstl:out value="\"${item}\"" escapeXml="false"/>,
							</jstl:forEach>						
						</jstl:otherwise>
					</jstl:choose>
				],
				datasets : [
					{
						label : <acme:message code="administrator.dashboard.form.label.applications"/>,
						backgroundColor : "rgba(22, 38, 212, 0.3)",
						borderColor : "rgba(22, 38, 212, 1)",
						data : [
								
							<jstl:choose>
								<jstl:when test="${dataApplication} == null">
									""
								</jstl:when>
								<jstl:otherwise>
									<jstl:forEach var="item" items="${dataApplication}">
										<jstl:out value="\"${item}\"" escapeXml="false"/>,
									</jstl:forEach>				
								</jstl:otherwise>
							</jstl:choose>							
								
						]
					}
				]
		};
		var options = {
				scales : {
					yAxes : [
							{
							ticks : {
								min : 0,
								stepSize : 1,
								autoSkip : true
							}
						}
					]
				},
				legend : {
					display : true
				}
			};
			
			var canvas, context;
			
			canvas = document.getElementById("canvasApplicationStatus");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
		});
	
		$(document).ready(function(){
			var data = {
					labels : [
						<jstl:choose>
							<jstl:when test="${statusJobLabels} == null">
								""
							</jstl:when>
							<jstl:otherwise>
								<jstl:forEach var="item" items="${statusJobLabels}">
									<jstl:out value="\"${item}\"" escapeXml="false"/>,
								</jstl:forEach>						
							</jstl:otherwise>
						</jstl:choose>
					],
					datasets : [
						{
							label : <acme:message code="administrator.dashboard.form.label.job"/>,
							backgroundColor : "rgba(22, 38, 212, 0.3)",
							borderColor : "rgba(22, 38, 212, 1)",
							data : [
										
								<jstl:choose>
									<jstl:when test="${dataJob} == null">
										""
									</jstl:when>
									<jstl:otherwise>
										<jstl:forEach var="item" items="${dataJob}">
											<jstl:out value="\"${item}\"" escapeXml="false"/>,
										</jstl:forEach>				
									</jstl:otherwise>
								</jstl:choose>							
										
							]
						}
					]
			};
			var options = {
					scales : {
						yAxes : [
								{
								ticks : {
									min : 0,
									stepSize : 1,
									autoSkip : true
								}
							}
						]
					},
					legend : {
						display : true
					}
				};
					
				var canvas, context;
					
				canvas = document.getElementById("canvasJobStatus");
				context = canvas.getContext("2d");
				new Chart(context, {
					type : "bar",
					data : data,
					options : options
				});
			});
		
			$(document).ready(function(){
				
				var canvas = document.getElementById("canvasTest");
				var context = canvas.getContext("2d");
				
				var config = {
				        type:    'line',
				        data:    {
				        	datasets: [
				                {
				                    label: <acme:message code="administrator.dashboard.form.label.pending"/>,
				                    data: [
				                    	
										<jstl:forEach var="i" begin="0" end="${sizePending}">
											{
												x: <jstl:out value="\"${pendingApplicationLabels[i]}\"" escapeXml="false" />,
												y: <jstl:out value="${pendingApplicationData[i]}"/>,
											},
										</jstl:forEach>
										
				                        ],
				                    fill: false,
				                    borderColor: 'blue'
				                },
				                {
				                	 label: <acme:message code="administrator.dashboard.form.label.accepted"/>,
					                    data: [
					                    	
											<jstl:forEach var="i" begin="0" end="${sizeAccepted}">
												{
													x: <jstl:out value="\"${acceptedApplicationLabels[i]}\"" escapeXml="false" />,
													y: <jstl:out value="${acceptedApplicationData[i]}"/>,
												},
											</jstl:forEach>
											
					                        ],
					                    fill: false,
					                    borderColor: 'green'
				                },
				                {
				                	 label: <acme:message code="administrator.dashboard.form.label.rejected"/>,
					                    data: [
					                    	
											<jstl:forEach var="i" begin="0" end="${sizeRejected}">
												{
													x: <jstl:out value="\"${rejectedApplicationLabels[i]}\"" escapeXml="false" />,
													y: <jstl:out value="${rejectedApplicationData[i]}"/>,
												},
											</jstl:forEach>
											
					                        ],
					                    fill: false,
					                    borderColor: 'red'
				                }
				            ]
				        },
				        options: {
				            title:      {
				                display: true
				            },
				            scales:     {
				                xAxes: [{
				                    type:       "time",
				                    time:       {
				                        unit: "day"   
				                    }
				                }],
				                yAxes : [{
				                	ticks : {
										min : 0,
										max: <jstl:out value="${maxGraph}" />,
										stepSize : 1,
										autoSkip : true
									}
				                }]
				            }
				        }
				    };
				
				var chart = new Chart(context,config);
						
						
			});
</script>