<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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

String command = request.getParameter("command");
int commandInt = Integer.parseInt(command);
String dataInizio = request.getParameter("dataInizio");
String dataFine = request.getParameter("dataFine");
String id = request.getParameter("id");
id = id.substring(0,1);
boolean controllo = false;

///////////////////////DA RIVEDERE //////////////////////////////////////////////////////////
	ArrayList<Locazione> elencoLocazioni= (ArrayList<Locazione>) request.getSession().getAttribute("loc");
	for(Locazione locazione : elencoLocazioni)
		out.println(locazione);
	try {
		controllo=cp.prenotazione(elencoLocazioni.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
	request.getSession().removeAttribute("loc");
	/*
if(commandInt==100){
	ArrayList<Locazione> elencoLocazioni= (ArrayList<Locazione>) request.getSession().getAttribute("loc");
	request.getSession().removeAttribute("loc");
	try {
		controllo=cp.prenotaAlbergo((Albergo) elencoLocazioni.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
}
if(commandInt==0){
	ArrayList<Albergo> elencoAlberghi = (ArrayList<Albergo>) request.getSession().getAttribute("alb");
	request.getSession().removeAttribute("alb");
	try {
		controllo = cp.prenotaAlbergo(elencoAlberghi.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}

} else if(commandInt==1){
	ArrayList<Appartamento> elencoAppartamenti = (ArrayList<Appartamento>) request.getSession().getAttribute("apt");
	request.getSession().removeAttribute("apt");
	try {
		controllo = cp.prenotaAppartamento(elencoAppartamenti.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
} else if(commandInt==2){
	ArrayList<Beb> elencoBeb = (ArrayList<Beb>) request.getSession().getAttribute("beb");
	request.getSession().removeAttribute("beb");
	try {
		controllo = cp.prenotaBeb(elencoBeb.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
} else if(commandInt == 3){
	ArrayList<CasaVacanza> elencoCasaVacanze = (ArrayList<CasaVacanza>) request.getSession().getAttribute("cvz");
	request.getSession().removeAttribute("cvz");
	try {
		controllo = cp.prenotaCasaVacanza(elencoCasaVacanze.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
} else if(commandInt == 4){
	ArrayList<Ostello> elencoOstelli = (ArrayList<Ostello>) request.getSession().getAttribute("ost");
	request.getSession().removeAttribute("ost");
	try {
		controllo = cp.prenotaOstello(elencoOstelli.get(Integer.parseInt(id)),dataInizio,dataFine);
	} catch (DeserializzazioneException | SerializzazioneException e) {
		e.printStackTrace();
	}
}

*/


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

				<font size="4px" color="red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </font>

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