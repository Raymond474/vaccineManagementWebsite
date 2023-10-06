<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Login</title>
</head>
<body>
	<form action='Login' method='post'>
		<div class="row">
			<div class="col-sm-2">Name</div>
			<div class="col">
				<input type='text' name='name'>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2">Username</div>
			<div class="col">
				<input type='text' name='username'>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2">Password</div>
			<div class="col">
				<input type='text' name='password'>
			</div>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" name="adminastrator">
  			<label class="form-check-label" for="flexCheckDefault">
    			Administrator
  			</label>
		</div>
		<div class="row">
			<button type="submit" class="btn btn-primary">Login</button>
		</div>
	</form>
</body>
</html>