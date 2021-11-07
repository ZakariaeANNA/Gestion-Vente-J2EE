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
<title>Inscription</title>
<link rel="stylesheet" href="css/authStyle.css">
<script src="js/jquery-3.6.0.js"></script>
<style type="text/css">
.errorDiv,#message {
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
	<form action="signupApp" method="post">
		<table>
			<tr>
				<td style="width: 33.33%;">
					<div class="dash"></div>
				</td>
				<td style="padding: 0 6px;">
					<h1>Inscription</h1>
				</td>
				<td style="width: 33.33%;">
					<div class="dash"></div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<p>Créez votre compte. C'est gratuit et ne prend qu'une minute.</p>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<input type="text" name="username" placeholder="nom d'utilisateur" required/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<input id="pass" placeholder="Mot de passe" name="password" type="password" required/>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<input id="pass2" placeholder="Confirmation de mot de passe" name="confirmPassword" type="password" required/>
						<span id="message"></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div>
						<input type="submit" value="Inscription"/>
					</div>
				</td>
			</tr>
		</table>
		</form>
		<footer>
			<p>Vous avez déjà un compte? <a href="login">S'identifier</a></p>
		</footer>
	</div>
</body>
<script>
$(document).ready(function(){
    $('#pass2').keyup(function(){
        var pass = $('#pass').val();
        var pass2 = $('#pass2').val();
        if(pass != pass2){
        	 $('#message').text('les mots de pass ne sont pas identique');
        }else{
        	 $('#message').text('');
        }
    });
});
</Script>
</html>
