<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Perfect Places</title>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<jsp:useBean id="bl" scope="session" class="boundary.BoundaryLogin"/>
	<div id="menu-wrapper">
		<div id="menu">
			
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">AREA PROPRIETARIO</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<ul class="topnav" id=myTopnav">
				<li><a href="utente.jsp">HOME</a></li>
				<li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
				<li><a href="areaProprietario.jsp">Area Proprietario</a></li>
				<li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
				<li><a href="posta.jsp">Posta</a></li>
				<li><a href="areaFaq.jsp">FAQ</a></li>
				<li><a href="logout.jsp">Esci</a></li>
			</ul>
			<div >
				<%
					if (!bl.controlloAccesso()) {
				%>

				<p style="font-size: 30px; color: red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

				<%
					}
				%>

			</div>
			<div id="page-bgbtm">
				<div id="content">
				
					
					<div class="post">
							<h2><strong>Scegli cosa fare con le tue locazioni</strong></h2>
							
					</div>
					<div class="post">
					<table>
						<tr>
							<td><h1>Visualizza</h1></td>
							<td><h1>Inserisci</h1></td>
							<td><h1>Rimuovi</h1></td>
							<td><h1>Prenotazioni</h1></td>

						</tr>
						<tr>
							<td><h2 class="title"><a href="visualizzaLocazioni.jsp"><img src="../css/images/iconaLente.png" width="250" height="250" alt="vis locazione"/></a></h2></td>
							<td><h2 class="title"><a href="inserisciLocazione.jsp"><img src="../css/images/iconaInserimento.png" width="250" height="250" alt="ins locazione"/></a></h2></td>
							<td><h2 class="title"><a href="rimuoviLocazione.jsp"><img src="../css/images/iconaRimozione.png" width="250" height="250" alt="rem locazione"/></a></h2></td>
							<td><h2 class="title"><a href="visualizzaPrenotazioniPerProprietario.jsp"><img src="../css/images/visualizzaPrenotazioni.jpg" width="250" height="250" alt="visualizza prenotazioni"/></a></h2> </td>
						</tr>
						

						</table>
					</div>
					</center>
				
					
					
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->
				<div style="clear: both;">&nbsp;</div>
			</div>
		</div>
	</div>
	<!-- end #page -->
</div>
<div id="footer">
	
</div>
<!-- end #footer -->
</body>
</html>
