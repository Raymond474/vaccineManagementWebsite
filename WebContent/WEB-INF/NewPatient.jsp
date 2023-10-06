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
<title>New Patient</title>
</head>
<body>
	<form action='NewPatient' method='post'>
		<div class="row">
			<div class="col-sm-2">Patient</div>
			<div class="col-sm-3">
				<input type="text" class="form-control" name='patientName'>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2">Vaccine</div>
			<div class="col">
				<select name='vaccineName'>
					<c:forEach items="${entries}" var="entry">
						<c:if test="${entry.totalDosesLeft > 0}">
						<option value="${entry.vaccineName}">
							${entry.vaccineName}</option></c:if>
						
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="row">
			<button type="submit" class="btn btn-primary">Save</button>
		</div>
	</form>
	
	<p>
		<a href='Logout' class="btn btn-secondary">Logout</a>
		<a href='Profile' class="btn btn-secondary">Profile</a>
	</p>
</body>
</html>