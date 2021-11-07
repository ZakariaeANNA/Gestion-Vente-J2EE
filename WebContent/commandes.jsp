<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>   
<%                        
  response.setHeader("Cache-control", "no-cache, no-store");           
  response.setHeader("Expires", "0");
  response.setHeader("Vary", "*");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion de vente</title>
<link rel="stylesheet" href="css/normalize.min.css">
<link rel="stylesheet" href="css/indexStyle.css">
<link rel="stylesheet" href="css/modalStyle.css">
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"/>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.dataTables.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<s:action name="commandes"></s:action>
<script language="javascript" type="text/javascript"> 
	$(document).ready(function() {
		$('#id01').hide();
		$('#mytbl').dataTable();
	});  
</script>
<sx:head />
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
      <div class="dashboard__item dashboard__item--col">
        <div class="card"><button class="button">Ajouter une Commande</button></div>
      </div>
      <div class="dashboard__item dashboard__item--full">
        <div class="card">
          <table class="table table-hover table-bordered" id="mytbl">
				<thead>
					<tr class="success">
						<th>Code Commande</th>
						<th>Code Article</th>
						<th>Date Commande</th>
						<th>Quantité Commande</th>
					</tr>
				</thead>
				<s:iterator value="mescommandes">
					<tr>
						<td>
							<s:property value="codeCmd" />
						</td>
						<td>
							<s:property value="codeArt" />
						</td>
						<td>
							<s:property value="dateCmd" />
						</td>
						<td>
							<s:property value="qteCmd" />
						</td>
					</tr>
				</s:iterator>
			</table>
        </div>
      </div>
    </div>
    
  </main>
</div>

  <div id="id01" class="modal">
        <span class="close cbtn" title="Close Modal">×</span>
        <form class="modal-content animate" action="ajoutercommande">
            <div class="container">
                <label><b>Code Article</b></label>
                <input type="number" placeholder="Code Article" name="codeArt" required>
 
                <label><b>Date Commande</b></label>
                <input type="text" id="datepicker" placeholder="Date Commande(Ex : 13/08/2021)" name="dateCMD" required pattern="\d{1,2}/\d{1,2}/\d{4}">
                <div id="message"></div></br>
                
 				<label><b>Quantite Commande</b></label>
                <input type="number" placeholder="Quantité Commande" name="qteArt" required>
                
                <div class="clearfix">
                    <button type="button" class="button cancelbtn cbtn">Cancel</button>
                    <input type="submit" class="button signupbtn" value="Ajouter"/>
                </div>
            </div>
        </form>
    </div>
<s:if test="hasActionMessages()">
	<div id="myModal" class="mpmodal">
	
	  <!-- Modal content -->
	  <div class="mpmodal-content">
	    <span class="mpclose">&times;</span>
	    <p><s:actionmessage/> <a class="cl" href="facturecommande">Telecharger Facture</a> </p>
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
</body>
<script> 
	$(document).ready(function() {
		$('.button').on('click', function(event) {
			$('#id01').show();
		});
		$('.cbtn').on('click', function(event) {
			$('#id01').hide();
		});
		$('.mpclose').on('click', function(event) {
			$('#myModal').hide();
		});
		$('.cl').on('click', function(event) {
			$('#myModal').hide();
		});
		$('#datepicker').change(evt => {
			  $(".signupbtn").attr("disabled", true);
			  var selectedText = $('#datepicker').val();
			  var dateParts = selectedText.split("/");
			  var selectedDate = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
			  var now = new Date();
			  now.setHours(0,0,0,0);
			  if (selectedDate < now) {
				  $("#message").text("La date est inférieur a aujourd'hui ");
				  $(".signupbtn").attr("disabled", true);
			  } else {
				  $("#message").text("");
				  $(".signupbtn").attr("disabled", false);
			  }
		});
	});  
</script>
</html>