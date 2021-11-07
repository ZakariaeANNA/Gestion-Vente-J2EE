<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    HttpSession sess = request.getSession(true);
    if (sess.getAttribute("username")==null)
    {
    %>
        <jsp:forward page="/"></jsp:forward>
    <%
    }
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/indexStyle.css">
<title>Modification Profile</title>
<link rel="stylesheet" href="css/normalize.min.css">
<link rel="stylesheet" href="css/modalStyle.css">
<link rel="stylesheet" href="css/updateStyle.css">

<script src="js/jquery-3.6.0.js"></script>
</head>
<body>
<!-- partial:index.partial.html -->
<div class="admin">
  <header class="admin__header">
    <a href="#" class="logo">
      <h1>Ray-Ban</h1>
    </a>
    <div class="toolbar">
      <a href="modifier"><button class="btn btn--primary">Modifier Profile</button></a>
      <a href="logout" class="logout">
        Déconnexion
      </a>
    </div>
  </header>
  <nav class="admin__nav">
    <ul class="menu">
      <li class="menu__item">
        <a class="menu__link" href="index">Tableau de board</a>
      </li>
      <li class="menu__item">
        <a class="menu__link" href="commandes">Mes Commandes</a>
      </li>
    </ul>
  </nav>
  <main class="admin__main">
    <h2>Dashboard</h2> 
    <div class="dashboard">
      <div class="dashboard__item dashboard__item--full">
        <div class="card">
		 <form action="update" method="post">
			<table>
				<tr>
					<td style="width: 33.33%;">
						<div class="dash"></div>
					</td>
					<td style="padding: 0 6px;">
						<h1 class="title">Modification Profile</h1>
					</td>
					<td style="width: 33.33%;">
						<div class="dash"></div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<p>Modifier votre compte. C'est simple et ne prend qu'une minute.</p>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div class="input">
							<input placeholder="Mot de passe actuelle" type="password" name="actpassword" required>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div class="input">
							<input id="pass" placeholder="Nouveau Mot de passe " type="password" name="nouvpassword" required>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div class="input">
							<input id="pass2" placeholder="Confirmation de mot de passe" type="password" name="confnouvpassword" required>
							<div id="message"></div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div class="input">
							<input type="submit" value="Modification">
						</div>
					</td>
				</tr>
			</table>
			</form>
        </div>
      </div>
    </div>
    
  </main>
</div>
<!-- partial -->
<s:if test="hasActionMessages()">
	<div id="myModal" class="mpmodal">
	
	  <!-- Modal content -->
	  <div class="mpmodal-content">
	    <span class="mpclose">&times;</span>
	    <p><s:actionmessage/></p>
	  </div>
	
	</div>
</s:if>
<s:if test="hasActionErrors()">
	<div id="myModal" class="mpmodal">
	
	  <!-- Modal content -->
	  <div class="mpmodal-content">
	    <span class="mpclose">&times;</span>
	    <p><s:actionerror/></p>
	  </div>
	
	</div>
</s:if>
<script>
$(document).ready(function(){
	$('.mpclose').on('click', function(event) {
		$('#myModal').hide();
	});
    $('#pass2').keyup(function(){
        var pass = $('#pass').val();
        var pass2 = $('#pass2').val();
        if(pass != pass2){
        	 $('#message').text('Les mots de passe ne sont pas identique');
        }else{
        	 $('#message').text('');
        }
    });
});
</Script>
</html>