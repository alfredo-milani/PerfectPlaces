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
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@page import="control.ControlloreVisualizzaLocazioni" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Locazione" %>
<%@ page import="exception.DeserializzazioneException" %>
<%

ControlloreVisualizzaLocazioni crl = new ControlloreVisualizzaLocazioni();

String username = c.getUser();
ArrayList<Locazione> locazioni = new ArrayList<Locazione>();
	try {
		locazioni = crl.visualizzaLocazioni(username);
	} catch (DeserializzazioneException e) {
		e.printStackTrace();
	}

	String nomeLocazione;
String provincia;
String indirizzo;
String prezzo; 
String descrizione;

String idNumber;


%>

<body>
	<div id="menu-wrapper">
		<div id="menu">
		</div>
		<!-- end #menu -->
	</div>

<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1><a href="#">Visualizza le tue locazioni</a></h1>
				
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
				<li><a href="logout.jsp">Esci</a></li>
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

					<div class="post">
							<h2><strong>Queste sono le tue locazioni</strong></h2>
							
					</div>
					
					<% for(int i=0;i<locazioni.size();i++){
						 nomeLocazione = locazioni.get(i).getNomeLocazione();
						 provincia = locazioni.get(i).getProvincia();
						 indirizzo = locazioni.get(i).getIndirizzo();
						 prezzo = locazioni.get(i).getPrezzo();
						 descrizione = locazioni.get(i).getDescrizione();
						 idNumber = ""+i;
						 
					
					%>

					<div class="post">
					
					<table style="width:100%">
						<tr>
							<td>
								<h2>Nome Locazione: <%out.println(nomeLocazione);%></h2>
								<h1>Provincia: <%out.println(provincia); %></h1>
								<h1>Indirizzo: <%out.println(indirizzo); %></h1>
								<h1>Prezzo: <%out.println(prezzo); %></h1>
								<h1>Descrizione: <%out.println(descrizione); %></h1>

							</td>
							<td>
								<center>
									<form method="get" action="rimuoviLocazione2.jsp" enctype="text/plain">
										<input type="hidden" name="id" value="<%out.println(idNumber);%>">
										<input type="submit" value="rimuovi">
									</form>
								<!--<a href="rimuoviLocazione2.jsp ?id=<% //out.println(idNumber); %>">
								<img src="../css/images/delete.jpg" width="150" height="100" alt="Press image to remove">
								</a>  -->
								</center>
							
							</td>
						</tr>
					</table>
					</div>
					
					<% }
						request.getSession().setAttribute("loc", locazioni);
					%>



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