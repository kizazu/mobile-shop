<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<title>Sign up facundo farm & resort</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<!---- boostrap.min link local ----->

<link href="/resources/css/style.css">
<!---- boostrap.min link local ----->

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!---- Boostrap js link local ----->

<link rel="icon" href="images/icon.png" type="image/x-icon" />
<!---- Icon link local ----->

<link href="https://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet">
<!---- Font awesom link local ----->
</head>
<body>
	<div class="container-fluid">
		<div class="container">
			<h2 class="text-center" id="title">Mobile-Store</h2>
			<br /> <p id="message"></p>
			<hr>
			<div class="row">
				<div class="col-md-5">
					<fieldset>
						<p class="text-uppercase">Login using admin:</p>

						<div class="form-group">
							<input type="text" name="username" id="username"
								class="form-control input-lg" placeholder="username">
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password"
								class="form-control input-lg" placeholder="Password">
						</div>
						<div>
							<input type="button" class="btn btn-md" id="register" value="Sign In">
						</div>

					</fieldset>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$("#register").click(function(){
	    $.ajax
	    ({ 
	        url: '/api/login/admin',
	        data: {"username": $("#username").val(),
	        		"password" : $("#password").val()
	        },
	        type: 'post',
	        success: function(data, textStatus, jqXHR){
	            console.log(textStatus + ": " + jqXHR.status);
	            // do something with data
	         },
	         error: function(jqXHR, textStatus, errorThrown){
	            console.log(textStatus + ": " + jqXHR.status + " " + errorThrown);
	            alert("Account can't login");
	         }
	})
	});
</script>
</html>