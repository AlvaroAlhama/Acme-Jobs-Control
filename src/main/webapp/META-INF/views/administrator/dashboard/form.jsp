<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
				labels : [
					"FIRST", "SECOND", "THIRD"
				],
				datasets : [
					{
						data : [
							<jstl:out value="${numberInvestorRecordFirstSector}"/>
							<jstl:out value="${numberInvestorRecordSecondSector}"/>
							<jstl:out value="${numberInvestorRecordThirdSector}"/>
						]
					}
				]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0,
							suggestedMax : 5
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>