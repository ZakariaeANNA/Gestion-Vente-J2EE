<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>
<%
    HttpSession sess = request.getSession(true);
    if (sess.getAttribute("username")!=null)
    {
    %>
        <jsp:forward page="/index"></jsp:forward>
    <%
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Authentification</title>
<link rel="stylesheet" href="css/authStyle.css">
<script src="js/jquery-3.6.0.js"></script>
<style type="text/css">
.errorDiv {
	color:red;
	display: flex;
	justify-content: center;
}
</style>
</head>
<body>
	<div class="center">
	<s:if test="hasActionErrors()">
		<div class="errorDiv">
		<s:actionerror/>
		</div>
	</s:if>
	<form action="loginApp" method="post">
		<table>
			<tr>
				<td style="width: 33.33%;">
					<div class="dash"></div>
				</td>
				<td style="padding: 0 6px;">
					<h1>Authentification</h1>
				</td>
				<td style="width: 33.33%;">
					<div class="dash"></div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<input name="username" placeholder="Nom d'utilisateur" type="text" required/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<input placeholder="Mot de passe" name="password" type="password" required/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<input type="submit" value="Authentifier"/>
					</div>
				</td>
			</tr>
		</table>
		</form>
		<footer>
			<p>Vous n'avez pas un compte? <a href="signup">S'inscrire</a></p>
		</footer>
	</div>
</body>
</html>