<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html >
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
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.*" %>
<%@page import="control.*" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>

<%

ControllorePrenotazione cp = new ControllorePrenotazione();

String dataInizio = request.getParameter("dataInizio");
String dataFine = request.getParameter("dataFine");
String id = request.getParameter("id");
id = id.substring(0,1);
boolean controllo = false;

	ArrayList<Locazione> elencoLocazioni= (ArrayList<Locazione>) request.getSession().getAttribute("loc");
	try {
		controllo=cp.prenotazione(elencoLocazioni.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
	request.getSession().removeAttribute("loc");



%>
	<div id="menu-wrapper">
		<div id="menu">
			
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Prenotazione</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<ul class="topnav" id=myTopnav">
				<li><a href="_it_utente.jsp">HOME</a></li>
				<li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
				<li><a href="areaProprietario.jsp">Area Proprietario</a></li>
				<li><a href="_it_profiloUtente.jsp">Visualizza profilo</a></li>
				<li><a href="_it_posta.jsp">Posta</a></li>
				<li><a href="_it_logout.jsp">Esci</a></li>
			</ul>
			<div class="post">
				<%
					if (!c.getLogged()) {
				%>

				<p style="size: 40px; color: red" > Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

				<%
					}
				%>
			</div>
			<div id="page-bgbtm">
				<div id="content">
				
					
					<% if(controllo){ %>
				
					<div class="post">
						
						<h1>Prenotazione effettuata con successo</h1>
						
					</div>

					<%} else { %>
						
						<div class="post">
						<h1>Prenotazione fallita: Non &egrave; pi&ugrave; possibile prenotare nella data selezionata</h1>
						<a href="areaViaggiatore.jsp">Torna alla pagina di ricerca</a>
						
					</div>
					
					<% } %>
						
								
					
					
					
					
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