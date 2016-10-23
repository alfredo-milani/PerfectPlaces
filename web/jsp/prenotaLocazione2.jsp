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
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.*" %>
<%@page import="control.*" %>

<%

ControllorePrenotazione cp = new ControllorePrenotazione();

String command = request.getParameter("command");
int commandInt = Integer.parseInt(command);
String dataInizio = request.getParameter("dataInizio");
String dataFine = request.getParameter("dataFine");
String id = request.getParameter("id");
id = id.substring(0,1);
boolean controllo = false;

if(commandInt==0){
	ArrayList<Albergo> elencoAlberghi = (ArrayList<Albergo>) request.getSession().getAttribute("alb");
	request.getSession().removeAttribute("alb");
	controllo = cp.prenotaAlbergo(elencoAlberghi.get(Integer.parseInt(id)),dataInizio,dataFine);
	
} else if(commandInt==1){
	ArrayList<Appartamento> elencoAppartamenti = (ArrayList<Appartamento>) request.getSession().getAttribute("apt");
	request.getSession().removeAttribute("apt");
	controllo = cp.prenotaAppartamento(elencoAppartamenti.get(Integer.parseInt(id)),dataInizio,dataFine);
} else if(commandInt==2){
	ArrayList<Beb> elencoBeb = (ArrayList<Beb>) request.getSession().getAttribute("beb");
	request.getSession().removeAttribute("beb");
	controllo = cp.prenotaBeb(elencoBeb.get(Integer.parseInt(id)),dataInizio,dataFine);
} else if(commandInt == 3){
	ArrayList<CasaVacanza> elencoCasaVacanze = (ArrayList<CasaVacanza>) request.getSession().getAttribute("cvz");
	request.getSession().removeAttribute("cvz");
	controllo = cp.prenotaCasaVacanza(elencoCasaVacanze.get(Integer.parseInt(id)),dataInizio,dataFine);
} else if(commandInt == 4){
	ArrayList<Ostello> elencoOstelli = (ArrayList<Ostello>) request.getSession().getAttribute("ost");
	request.getSession().removeAttribute("ost");
	controllo = cp.prenotaOstello(elencoOstelli.get(Integer.parseInt(id)),dataInizio,dataFine);
}




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
			<div id="page-bgbtm">
				<div id="content">
				
					
					<% if(controllo){ %>
				
					<div class="post">
						
						<h1>Prenotazione effettuata con successo!</h1>
						
					</div>

					<%} else { %>
						
						<div class="post">
						
						<h1>Errore in fase di prenotazione: Non &egrave; pi&ugrave; possibile prenotare nella data selezionata!</h1>
						<a href="areaViaggiatore.jsp">Torna alla pagina di ricerca!</a>
						
					</div>
					
					<% } %>
						
								
					
					
					
					
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->
				<!-- Menu -->
				
				<div id="sidebar">
					<% if (b.getLogged()) {  %>
					
					<ul>
						<li>
						<center>
						<h2><strong><a href="areaViaggiatore.jsp">Area viaggiatore</a></strong></h2>
						</center>
						</li>
					</ul>
					<ul>
						<li>
						<center>
						<h2><strong><a href="areaProprietario.jsp">Area proprietario</a></strong></h2>
						</center>
						</li>
					</ul>
					<ul>
						<li>
						<center>
						<h2><strong><a href="_it_profiloUtente.jsp">Visualizza profilo</a></strong></h2>
						</center>
						</li>
					</ul>		
					<ul>
						<li>
						<center>
						<h2><strong><a href="_it_posta.jsp">Posta</a></strong></h2>
						</center>
						</li>
					</ul>	
					<ul>
						<li>
						<center>
						<h2><strong><a href="_it_logout.jsp">Logout</a></strong></h2>
						</center>
						</li>
					</ul>						
					
					<%  }  %>
				</div>
				<!-- end #sidebar -->
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