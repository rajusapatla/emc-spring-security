<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>Home Page</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">
var contextPath = '<%=request.getContextPath()%>';
	$(document)
			.ready(
					function() {
						$('#cityWeather')
								.change(
										function(e) {
											var url = 'http://api.openweathermap.org/data/2.5/weather?id=';
											var value = $(this).val();
											var urlFinal = url + value;
											//alert("url==>" + urlFinal);
											var iconUrl = "http://openweathermap.org/img/w/";

											$
													.ajax({
														type : "POST",
														url : urlFinal,
														dataType : "json",
														success : function(
																result) {
															$("#output")
																	.html(
																			JSON
																					.stringify(result));
															//alert(result.id);
															var convTemp = result.main.temp * 9 / 5 - 459.67;
															$(
																	"#cityTemperature")
																	.html(
																			""
																					+ convTemp);
															$(
																	"#weatherDescription")
																	.html(
																			result.weather[0].description);
															$("#cityName")
																	.html(
																			result.name);
															$("#iconValue")
																	.html(
																			result.weather[0].icon);
															$("#icon")
																	.html(
																			"<img src="+
																			"http://openweathermap.org/img/w/"
																			+ result.weather[0].icon
																			+ ".png"+">");
															return $
																	.saveRestResponseInDB(result);
														}
													});
											$.saveRestResponseInDB = function(
													result) {
												var userN = "${pageContext.request.userPrincipal.name}";
												var id = result.id;
												var jsonRes = JSON
														.stringify(result);
												var dataR = JSON
														.stringify({
															weatherId : id,
															userName : userN,
															jsonServiceResponse : jsonRes
														});
												//alert("userN==>" + userN);
												//alert("id==>" + id);
												//alert("jsonRes==>" + jsonRes);
												$.ajax({
													url : contextPath
															+ "/weather/check",
													type : "POST",
													data : "outputData="
															+ dataR,
													dataType : "json",
													success : function() {
														//alert("done");

													}
												});
											}
										});
					});
</script>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body>
	<h1>${message}</h1>
	<div>
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<div>
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>


		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				Welcome : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
		<br />
	</div>
	<div>
		<form name="weatherCheck" action="/weather/check" method="post">
			<select id="cityWeather" name="cityWeather">
				<option value="4508722">Cincinnati</option>
				<option value="5391959">San Francisco</option>
				<option value="4887398">Chicago</option>
				<option value="4930956">Boston</option>
				<option value="4164138">Miami</option>
				<option value="5809844">Seattle</option>
			</select>
		</form>
	</div>
	Temperature :
	<div id="cityTemperature"></div>
	<br /> Weather Description :
	<div id="weatherDescription"></div>
	<br /> Name of the City :
	<div id="cityName"></div>
	<br /> Icon Value :
	<div id="iconValue"></div>
	<br /> Icon :
	<div id="icon"></div>
</body>
</html>