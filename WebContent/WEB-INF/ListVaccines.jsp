<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>ListVaccines</title>
</head>
<body>
	<p>
		<a href='NewVaccine'>New Vaccine</a> | <a href='NewDoses'>New
			Doses</a>
	</p>
	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">Vaccine</th>
				<th scope="col">Doses Required</th>
				<th scope="col">Days Between Doses</th>
				<th scope="col">Total Doses Received</th>
				<th scope="col">Total Doses Left</th>
				<th scope="col"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${entries}" var="entry">
				<tr>
					<td>${entry.vaccineName}</td>
					<td>${entry.dosesRequired}</td>
					<td><c:if test="${entry.daysBetweenDoses == 0}"></c:if> <c:if
							test="${entry.daysBetweenDoses != 0}">${entry.daysBetweenDoses}</c:if>
					</td>
					<td>${entry.totalDosesReceived}</td>
					<td>${entry.totalDosesLeft}</td>
					<td><a href="EditVaccine?id=${entry.id}">Edit</a></td>
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