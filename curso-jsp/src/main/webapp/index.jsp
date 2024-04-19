<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Curso JSP</title>
</head>
<body>
	<h1>Bem vindo ao curso de JSP</h1>		
	<form action="ServletLogin" method="post">
		<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
		<table>
			<tr>
				<td><label>Login:</label></td>
				<td><input name="login" type="text"></td>
			</tr>
			<tr>
				<td><label>Senha:</label></td>
				<td><input name="senha" type="password"></td>
			</tr>
			<tr>
				<td/>
				<td><input type="submit" value="Enviar"></td>
			</tr>
		</table>
	</form>
	<h4>${msg}</h4>
</body>
</html>