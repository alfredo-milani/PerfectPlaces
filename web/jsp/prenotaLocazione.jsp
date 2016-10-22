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
<%@page import="utils.VerificaInput" %>
<%@page import="entity.*" %>
<%@page import="control.ControlloreRicercaLocazione" %>
<%@page import="java.util.ArrayList" %>
<%

VerificaInput vi = new VerificaInput();

ControlloreRicercaLocazione crl = new ControlloreRicercaLocazione();

String nomeLocazione;
String indirizzo;
String userLocatore;
String descrizione;
boolean parcheggio;
boolean wifi;
boolean pet;


String prezzo = request.getParameter("prezzo");
String dataInizio=request.getParameter("dataInizio");
String dataFine = request.getParameter("dataFine");
String command = request.getParameter("command");
int commandInt = Integer.parseInt(command);
boolean control;
ArrayList<Albergo> elencoAlberghi = new ArrayList<Albergo>();
ArrayList<Appartamento> elencoAppartamenti = new ArrayList<Appartamento>();
ArrayList<Beb> elencoBeb = new ArrayList<Beb>();
ArrayList<CasaVacanza> elencoCasaVacanze = new ArrayList<CasaVacanza>();
ArrayList<Ostello> elencoOstelli = new ArrayList<Ostello>();

if(vi.verificaGiorno(dataInizio)&&vi.verificaGiorno(dataFine)&&vi.verificaPrezzo(prezzo)){
	
	if(commandInt==0){
		elencoAlberghi=crl.ricercaAlbergo(prezzo,dataInizio,dataFine);
	} else if(commandInt == 1){
		elencoAppartamenti=crl.ricercaAppartamento(prezzo,dataInizio,dataFine);
	} else if(commandInt==2){
		elencoBeb=crl.ricercaBeb(prezzo,dataInizio,dataFine);
	} else if(commandInt==3){
		elencoCasaVacanze=crl.ricercaCasaVacanze(prezzo,dataInizio,dataFine);
	} else if(commandInt==4){
		elencoOstelli=crl.ricercaOstello(prezzo,dataInizio,dataFine);
	}
	
	control=true;
	
	
} else {
	control = false;
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
				<h1><a href="#">Ecco le locazioni disponibili!</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
				
				<% if(!control){ %>
				
					<div class="post">
						<h2>
						Il prezzo o le date inserite non sono accettabili!<br />
						<a href="areaViaggiatore.jsp">Torna all'area viaggiatore!</a>
						</h2>
					</div>
							
					<% } else { 
							
						if(commandInt==0){
								for(int i=0;i<elencoAlberghi.size();i++){
									
									nomeLocazione = elencoAlberghi.get(i).getNomeLocazione();
									indirizzo = elencoAlberghi.get(i).getIndirizzo();
									userLocatore = elencoAlberghi.get(i).getUserLocatore();
									descrizione = elencoAlberghi.get(i).getDescrizione();
									String sParcheggio;
									String sWifi;
									String sPet;
																		
									if(elencoAlberghi.get(i).isParcheggio()){
										sParcheggio = "S�";
									} else {
										sParcheggio = "No";
									}
									if(elencoAlberghi.get(i).isWifi()){
										sWifi = "S�";
									} else {
										sWifi = "No";
									}
									if(elencoAlberghi.get(i).isPet()){
										sPet = "S�";
									} else {
										sPet = "No";
									}
									String tipoPensione = elencoAlberghi.get(i).getTipoPensione();
									String orarioColazione = elencoAlberghi.get(i).getOrarioColazione();
									String orarioPranzo = elencoAlberghi.get(i).getOrarioPranzo();
									String orarioCena = elencoAlberghi.get(i).getOrarioCena();
									request.getSession().setAttribute("alb", elencoAlberghi);
								%>
								
								<div class="post">
						
									<h2>Nome Locazione: <%out.println(nomeLocazione);%></h2>
									<table style="width:100%">
										<tr>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
											<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
										</tr>
										<tr>
											<td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
											<td><b>Orario colazione:</b> <%out.println(orarioColazione);%></td>
										</tr>
										<tr>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Orario pranzo:</b> <%out.println(orarioPranzo);%></td>
										</tr>
										<tr>
											<td><b>Tipo pensione:</b> <%out.println(tipoPensione);%></td>
											<td><b>Orario cena:</b> <%out.println(orarioCena);%></td>
										</tr>
										<tr>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione2.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="command" value="0">
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" value="PRENOTA!">
									</div>
									</form>
								</div>	
								
								<%
								}
						} else if(commandInt==1){
							for(int i=0;i<elencoAppartamenti.size();i++){
								
								nomeLocazione = elencoAppartamenti.get(i).getNomeLocazione();
								indirizzo = elencoAppartamenti.get(i).getIndirizzo();
								userLocatore = elencoAppartamenti.get(i).getUserLocatore();
								descrizione = elencoAppartamenti.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;
																	
								if(elencoAppartamenti.get(i).isParcheggio()){
									sParcheggio = "S�";
								} else {
									sParcheggio = "No";
								}
								if(elencoAppartamenti.get(i).isWifi()){
									sWifi = "S�";
								} else {
									sWifi = "No";
								}
								if(elencoAppartamenti.get(i).isPet()){
									sPet = "S�";
								} else {
									sPet = "No";
								}
								String numeroStanze = elencoAppartamenti.get(i).getNumeroStanze();
								String numeroBagni = elencoAppartamenti.get(i).getNumeroBagni();
								String sGiardino;
								if(elencoAppartamenti.get(i).isGiardino()){
									sGiardino = "S�";
								} else {
									sGiardino = "No";
								}
								String numeroLetti = elencoAppartamenti.get(i).getNumeroLetti();
								request.getSession().setAttribute("apt", elencoAppartamenti);
							%>
							
							<div class="post">
						
									<h2>Nome Locazione: <%out.println(nomeLocazione);%></h2>
									<table style="width:100%">
										<tr>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
											<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
										</tr>
										<tr>
											<td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
											<td><b>Numero stanze:</b> <%out.println(numeroStanze);%></td>
										</tr>
										<tr>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Numero bagni:</b> <%out.println(numeroBagni);%></td>
										</tr>
										<tr>
											<td><b>Giardino:</b> <%out.println(sGiardino);%></td>
											<td><b>Numero letti:</b> <%out.println(numeroLetti);%></td>
										</tr>
										<tr>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione2.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="command" value="1">
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" value="PRENOTA!">
										</div>
									</form>
								</div>	
								
							
							<%
							}
							
						} else if(commandInt == 2){
								for(int i=0;i<elencoBeb.size();i++){
								
								nomeLocazione = elencoBeb.get(i).getNomeLocazione();
								indirizzo = elencoBeb.get(i).getIndirizzo();
								userLocatore = elencoBeb.get(i).getUserLocatore();
								descrizione = elencoBeb.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;
																	
								if(elencoBeb.get(i).isParcheggio()){
									sParcheggio = "S�";
								} else {
									sParcheggio = "No";
								}
								if(elencoBeb.get(i).isWifi()){
									sWifi = "S�";
								} else {
									sWifi = "No";
								}
								if(elencoBeb.get(i).isPet()){
									sPet = "S�";
								} else {
									sPet = "No";
								}
								String orarioColazione = elencoBeb.get(i).getOrarioColazione();
								request.getSession().setAttribute("beb", elencoBeb);
							%>
							
							<div class="post">
					
								<h2>Nome Locazione: <%out.println(nomeLocazione);%></h2>
								
								<table style="width:100%">
										<tr>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
											<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
										</tr>
										<tr>
											<td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
											<td><b>Orario colazione:</b> <%out.println(orarioColazione);%></td>
										</tr>
										<tr>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione2.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="command" value="2">
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" value="PRENOTA!">
										</div>
									</form>
								</div>	
							
							<%
							}
					} else if(commandInt == 3){
						for(int i=0;i<elencoCasaVacanze.size();i++){
							
							nomeLocazione = elencoCasaVacanze.get(i).getNomeLocazione();
							indirizzo = elencoCasaVacanze.get(i).getIndirizzo();
							userLocatore = elencoCasaVacanze.get(i).getUserLocatore();
							descrizione = elencoCasaVacanze.get(i).getDescrizione();
							String sParcheggio;
							String sWifi;
							String sPet;
																
							if(elencoCasaVacanze.get(i).isParcheggio()){
								sParcheggio = "S�";
							} else {
								sParcheggio = "No";
							}
							if(elencoCasaVacanze.get(i).isWifi()){
								sWifi = "S�";
							} else {
								sWifi = "No";
							}
							if(elencoCasaVacanze.get(i).isPet()){
								sPet = "S�";
							} else {
								sPet = "No";
							}
							String numeroCamere = elencoCasaVacanze.get(i).getNumeroCamere();
							String numeroBagni = elencoCasaVacanze.get(i).getNumeroBagni();
							String sGiardino;
							if(elencoCasaVacanze.get(i).isGiardino()){
								sGiardino = "S�";
							} else {
								sGiardino = "No";
							}
							String numeroLetti = elencoCasaVacanze.get(i).getNumeroLetti();
							request.getSession().setAttribute("cvz", elencoCasaVacanze);
							
						%>
						
						<div class="post">
						
									<h2>Nome Locazione: <%out.println(nomeLocazione);%></h2>
									<table style="width:100%">
										<tr>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
											<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
										</tr>
										<tr>
											<td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
											<td><b>Numero camere:</b> <%out.println(numeroCamere);%></td>
										</tr>
										<tr>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Numero bagni:</b> <%out.println(numeroBagni);%></td>
										</tr>
										<tr>
											<td><b>Giardino:</b> <%out.println(sGiardino);%></td>
											<td><b>Numero letti:</b> <%out.println(numeroLetti);%></td>
										</tr>
										<tr>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione2.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="command" value="3">
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" value="PRENOTA!">
										</div>
									</form>
								</div>	
						
					
						<%
						}
					} else if(commandInt == 4){
						for(int i=0;i<elencoOstelli.size();i++){
							
							nomeLocazione = elencoOstelli.get(i).getNomeLocazione();
							indirizzo = elencoOstelli.get(i).getIndirizzo();
							userLocatore = elencoOstelli.get(i).getUserLocatore();
							descrizione = elencoOstelli.get(i).getDescrizione();
							String sParcheggio;
							String sWifi;
							String sPet;
																
							if(elencoOstelli.get(i).isParcheggio()){
								sParcheggio = "S�";
							} else {
								sParcheggio = "No";
							}
							if(elencoOstelli.get(i).isWifi()){
								sWifi = "S�";
							} else {
								sWifi = "No";
							}
							if(elencoOstelli.get(i).isPet()){
								sPet = "S�";
							} else {
								sPet = "No";
							}
							request.getSession().setAttribute("ost", elencoOstelli);
						%>
						
						<div class="post">
						
									<h2>Nome Locazione: <%out.println(nomeLocazione);%></h2>
									<table style="width:100%">
										<tr>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
											<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
										</tr>
										<tr>
											<td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
										<tr>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione2.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="command" value="4">
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" value="PRENOTA!">
										</div>
									</form>
								</div>	
												
						<%
						}
					}
						
				}
						
						%>
					
					<div style="clear: both;">&nbsp;</div>
				</div>
				<!-- end #content -->
				<!-- Menu -->
				
				<div id="sidebar">
					<% if (c.getLogged()) {  %>
					
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
						<h2><strong><a href="profiloUtente.jsp">Visualizza profilo</a></strong></h2>
						</center>
						</li>
					</ul>		
					<ul>
						<li>
						<center>
						<h2><strong><a href="posta.jsp">Posta</a></strong></h2>
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
