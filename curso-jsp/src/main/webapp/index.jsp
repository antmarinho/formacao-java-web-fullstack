<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet" type="text/css">
	<title>Curso JSP</title>

</head>
<body>
	<h3>Bem vindo ao curso de JSP</h3>		
	<form action="ServletLogin" method="post" class="row g-3">
		<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
		<div class="col-md-6">
			<label class="form-label">Login:</label>
			<input class="form-control" name="login" type="text">
		</div>
		<div class="col-md-6">
			<label class="form-label">Senha:</label>
			<input class="form-control" name="senha" type="password">
		</div>
		<div >
			<input class="btn btn-primary" type="submit" value="Acessar">
		</div>
	</form>
	<h5>${msg}</h5>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>