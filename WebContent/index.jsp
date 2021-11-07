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
<title>Gestion de vente</title>
<link rel="stylesheet" href="css/normalize.min.css">
<link rel="stylesheet" href="css/indexStyle.css">
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"/>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/jquery.dataTables.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script language="javascript" type="text/javascript"> 
	$(document).ready(function() {
		$('#mytbl').dataTable();
	});  
</script>
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
          <table class="table table-hover table-bordered" id="mytbl">
				<thead>
					<tr class="success">
						<th>Code Article</th>
						<th>Nom Article</th>
						<th>Description Article</th>
						<th>Prix Article</th>
						<th>Quantité Article</th>
					</tr>
				</thead>
				<s:iterator value="Article">
					<tr>
						<td>
							<s:property value="codeArt" />
						</td>
						<td>
							<s:property value="nomArt" />
						</td>
						<td>
							<s:property value="descArt" />
						</td>
						<td>
							<s:property value="prixArt" />
						</td>
						<td>
							<s:property value="qteArt" />
						</td>
					</tr>
				</s:iterator>
			</table>
        </div>
      </div>
    </div>
    
  </main>
</div>
<!-- partial -->
  
</body>
</html>