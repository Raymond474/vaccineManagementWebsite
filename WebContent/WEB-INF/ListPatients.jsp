<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
	integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w=="
	crossorigin="anonymous" />
<title>ListPatients</title>
</head>
<body>
	<p>
		<a href='NewPatient' class="btn btn-secondary">New Patient</a>
	</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Name</th>
				<th scope="col">Vaccine</th>
				<th scope="col">1st Dose</th>
				<th scope="col">2nd Dose</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${patients}" var="patient">
				<tr>
					<td>${patient.id}</td>
					<td>${patient.name}</td>
					<td>${patient.vaccineName}</td>
					<td><fmt:formatDate value="${patient.firstDose}" pattern="M/d/yyyy" /></td>
					
					<td>
					<c:choose>
					<c:when test="${patient.vaccine.dosesRequired == 1}">-</c:when>
					<c:when test="${not empty patient.secondDose}"><fmt:formatDate value="${patient.secondDose}" pattern="M/d/yyyy" /></c:when>
					<c:when test="${patient.vaccine.totalDosesLeft <= 0}">Out of Stock</c:when>
					<c:when test="${empty patient.secondDose}"><a href='Received?id=${patient.id}'>Received</a></c:when>
					
					</c:choose>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>
		<a href='Logout' class="btn btn-secondary">Logout</a>
		<a href='Profile' class="btn btn-secondary">Profile</a>
	</p>
</body>
</html>