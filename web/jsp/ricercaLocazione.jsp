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
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@page import="utils.VerificaInput" %>
<%@page import="entity.*" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="control.ControlloreRicercaPerLocazione" %>
<%@ page import="control.AdapterRicercaPerLocazione" %>
<%@ page import="control.ControlloreRicercaGlobale" %>
<%@ page import="utils.DeserializzaOggetti" %>
<%

VerificaInput vi = new VerificaInput();
ControlloreRicercaGlobale globale =  new ControlloreRicercaGlobale();
	ControlloreRicercaPerLocazione ctrl = new AdapterRicercaPerLocazione(globale);

String nomeLocazione;
String indirizzo;
String userLocatore;
String descrizione;
String parcheggio;
String wifi;
String pet;

boolean control=false;

String provincia;
String prezzo ;
String dataInizio ;
String dataFine;
provincia = request.getParameter("provincia");
prezzo = request.getParameter("prezzo");
dataInizio=request.getParameter("dataInizio");
dataFine = request.getParameter("dataFine");
String command = request.getParameter("command");
String commandAvanzata = request.getParameter("commandAvanzata");
int commandInt=10;
int commandavAnzataInt=10;


//necessario per determinare se la ricerca sara normale o avanzata
if(command !=null &&commandAvanzata==null){
	commandInt = Integer.parseInt(command);
	commandavAnzataInt=Integer.parseInt("10");
}
if(commandAvanzata!=null){
	commandInt = Integer.parseInt(commandAvanzata);
	commandavAnzataInt = Integer.parseInt(commandAvanzata);
}


ArrayList<Locazione> elencoLocazioni = new ArrayList<Locazione>();
ArrayList<Albergo> elencoAlberghi = new ArrayList<Albergo>();
ArrayList<Appartamento> elencoAppartamenti = new ArrayList<Appartamento>();
ArrayList<Beb> elencoBeb = new ArrayList<Beb>();
ArrayList<CasaVacanza> elencoCasaVacanze = new ArrayList<CasaVacanza>();
ArrayList<Ostello> elencoOstelli = new ArrayList<Ostello>();
Albergo albergo =new Albergo();
Appartamento appartamento = new Appartamento();
Beb beb = new Beb();
CasaVacanza casa = new CasaVacanza();
Ostello ostello = new Ostello();

	if(vi.verificaProvincia(provincia)&&vi.verificaDate(dataInizio,dataFine)&&vi.verificaPrezzo(prezzo)){
			if(commandavAnzataInt==10) {
				if(commandInt==100){
					elencoLocazioni = globale.ricercaGlobale(provincia,prezzo);
				}
				if (commandInt == 0) {
						elencoAlberghi = (ArrayList<Albergo>) ctrl.ricerca(albergo, provincia, prezzo);
				} else if (commandInt == 1) {
						elencoAppartamenti = (ArrayList<Appartamento>) ctrl.ricerca(appartamento, provincia, prezzo);
				} else if (commandInt == 2) {
						elencoBeb = (ArrayList<Beb>) ctrl.ricerca(beb, provincia, prezzo);
				} else if (commandInt == 3) {
						elencoCasaVacanze = (ArrayList<CasaVacanza>) ctrl.ricerca(casa, provincia, prezzo);

				} else if (commandInt == 4) {
						elencoOstelli = (ArrayList<Ostello>) ctrl.ricerca(ostello, provincia, prezzo);
				}
			}else{
				parcheggio = request.getParameter("parcheggio");
				wifi = request.getParameter("wifi");
				pet = request.getParameter("pet");
				if(commandavAnzataInt==0) {
						String tipoPensione = request.getParameter("tipoPensione");
						elencoAlberghi = (ArrayList<Albergo>) ctrl.ricercaAvanzata(albergo,provincia,prezzo, parcheggio, wifi, pet,tipoPensione);
					}
				else if(commandavAnzataInt==1) {
						String numeroStanze = request.getParameter("numeroStanze");
						elencoAppartamenti = (ArrayList<Appartamento>) ctrl.ricercaAvanzata(appartamento,provincia,prezzo, parcheggio, wifi, pet,numeroStanze);
					}
				else if(commandavAnzataInt==2) {
						elencoBeb = (ArrayList<Beb>) ctrl.ricercaAvanzata(beb,provincia,prezzo, parcheggio, wifi, pet,"caratt");////////////
					}
				else if(commandavAnzataInt==3) {
					String numeroCamere = request.getParameter("numeroCamere");
					elencoCasaVacanze = (ArrayList<CasaVacanza>) ctrl.ricercaAvanzata(casa,provincia,prezzo, parcheggio, wifi, pet, numeroCamere);
				}
				else if(commandavAnzataInt==4) {
					String tipoPensione = request.getParameter("tipoPensione");
					elencoOstelli = (ArrayList<Ostello>) ctrl.ricercaAvanzata(ostello, provincia,prezzo, parcheggio, wifi, pet,tipoPensione);
				}

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
				<h1><a href="#">Ecco le locazioni disponibili</a></h1>
				
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

				<p style="size:  40px; color: red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

				<%
					}
				%>

			</div>
			<div id="page-bgbtm">
				<div id="content">
				
				
				<% if(!control){ %>
				
					<div class="post">
						<h2>
						Il prezzo,le date inserite o la provincia non sono accettabili
						</h2>
						<br><br>
						<h4>Ricorda di completare tutti i campi, di inserire una provincia con il primo carattere in maiuscolo(es. Roma),<br>
							se la provincia è composta da più parole ognuna di queste deve essere inserita con il primo carattere in maiuscolo (es. Reggio Di Calabria)<br>
							e di inserire date nel formato gg/mm/aaaa (es. 10/10/2016)</h4>
						<br>
						<h2><a href="areaViaggiatore.jsp">Torna all'area viaggiatore</a></h2>
					</div>
							
					<% } else {
						if(commandInt==100){
							for(int i=0; i<elencoLocazioni.size();i++) {
								nomeLocazione = elencoLocazioni.get(i).getNomeLocazione();
								prezzo = elencoLocazioni.get(i).getPrezzo();
								provincia = elencoLocazioni.get(i).getProvincia();
								indirizzo = elencoLocazioni.get(i).getIndirizzo();
								userLocatore = elencoLocazioni.get(i).getUserLocatore();
								descrizione = elencoLocazioni.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;

								if (elencoLocazioni.get(i).isParcheggio()) {
									sParcheggio = "Si";
								} else {
									sParcheggio = "No";
								}
								if (elencoLocazioni.get(i).isWifi()) {
									sWifi = "Si";
								} else {
									sWifi = "No";
								}
								if (elencoLocazioni.get(i).isPet()) {
									sPet = "Si";
								} else {
									sPet = "No";
								}
								request.getSession().setAttribute("loc", elencoLocazioni);
								%>
					<div class="post">
                        <%
                            if(elencoLocazioni.get(i).getClass()==Albergo.class){
                        %>
                        <img src="../css/images/albergo.png"  style="width:50px;height:50px; float: left">
                        <span class="span">Nome albergo: <%out.println(nomeLocazione);%></span>
                        <%}%>
                        <%
                            if(elencoLocazioni.get(i).getClass()==Appartamento.class){
                        %>
                        <img src="../css/images/appartamento.png"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome appartamento: <%out.println(nomeLocazione);%></span>
                        <%}%>
                        <%
                            if(elencoLocazioni.get(i).getClass()==Beb.class){
                        %>
                        <img src="../css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
                        <span class="span">Nome b&b: <%out.println(nomeLocazione);%></span>

                        <%}%>
                        <%
                            if(elencoLocazioni.get(i).getClass()==CasaVacanza.class){
                        %>
                        <img src="../css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome casa vacanza: <%out.println(nomeLocazione);%></span>

                        <%}%>
                        <%
                            if(elencoLocazioni.get(i).getClass()==Ostello.class){
                        %>
                        <img src="../css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome ostello: <%out.println(nomeLocazione);%></span>
                        <%}%>
						<table style="width:100%">
							<tr>
								<td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
								<td><b>Prezzo :</b> <%out.println(prezzo);%></td>
							</tr>
							<tr>
								<td><b>Provincia:</b> <%out.println(provincia);%></td>
								<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>

							</tr>
							<tr>
								<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
								<td><b>Wifi:</b> <%out.println(sWifi);%></td>
								<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
							</tr>
						</table>
						<div class="break-word">
							<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
						</div>
						<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
							<div>
								<input type="hidden" name="id" value="<%out.println(i);%>">
								<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
								<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
								<input type="submit" class="btnBlue200" value="PRENOTA">
							</div>
						</form>
					</div>
					<%} %>
					<%
						}else if(commandInt==0){
								for(int i=0;i<elencoAlberghi.size();i++){
									
									nomeLocazione = elencoAlberghi.get(i).getNomeLocazione();
                                    prezzo = elencoAlberghi.get(i).getPrezzo();
									provincia = elencoAlberghi.get(i).getProvincia();
									indirizzo = elencoAlberghi.get(i).getIndirizzo();
									userLocatore = elencoAlberghi.get(i).getUserLocatore();
									descrizione = elencoAlberghi.get(i).getDescrizione();
									String sParcheggio;
									String sWifi;
									String sPet;
																		
									if(elencoAlberghi.get(i).isParcheggio()){
										sParcheggio = "Si";
									} else {
										sParcheggio = "No";
									}
									if(elencoAlberghi.get(i).isWifi()){
										sWifi = "Si";
									} else {
										sWifi = "No";
									}
									if(elencoAlberghi.get(i).isPet()){
										sPet = "Si";
									} else {
										sPet = "No";
									}
									String tipoPensione = elencoAlberghi.get(i).getTipoPensione();
									String orarioColazione = elencoAlberghi.get(i).getOrarioColazione();
									String orarioPranzo = elencoAlberghi.get(i).getOrarioPranzo();
									String orarioCena = elencoAlberghi.get(i).getOrarioCena();
									request.getSession().setAttribute("loc", elencoAlberghi);
								%>
								
								<div class="post">
                                    <img src="../css/images/albergo.png"  style="width:50px;height:50px; float: left">
                                    <span class="span">Nome albergo: <%out.println(nomeLocazione);%></span>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo :</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>

										</tr>
                                        <tr>
                                            <td><b>Tipo pensione:</b> <%out.println(tipoPensione);%></td>

                                        </tr>
										<tr>
											<td><b>Orario colazione:</b> <%out.println(orarioColazione);%></td>
											<td><b>Orario pranzo:</b> <%out.println(orarioPranzo);%></td>
											<td><b>Orario cena:</b> <%out.println(orarioCena);%></td>
										</tr>
										<tr>
											<td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" class="btnBlue200" value="PRENOTA">
									</div>
									</form>
								</div>	
								
								<%
								}
						} else if(commandInt==1){
							for(int i=0;i<elencoAppartamenti.size();i++){
								
								nomeLocazione = elencoAppartamenti.get(i).getNomeLocazione();
                                prezzo =elencoAppartamenti.get(i).getPrezzo();
								provincia = elencoAppartamenti.get(i).getProvincia();
								indirizzo = elencoAppartamenti.get(i).getIndirizzo();
								userLocatore = elencoAppartamenti.get(i).getUserLocatore();
								descrizione = elencoAppartamenti.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;
																	
								if(elencoAppartamenti.get(i).isParcheggio()){
									sParcheggio = "Si";
								} else {
									sParcheggio = "No";
								}
								if(elencoAppartamenti.get(i).isWifi()){
									sWifi = "Si";
								} else {
									sWifi = "No";
								}
								if(elencoAppartamenti.get(i).isPet()){
									sPet = "Si";
								} else {
									sPet = "No";
								}
								String numeroStanze = elencoAppartamenti.get(i).getNumeroStanze();
								String numeroBagni = elencoAppartamenti.get(i).getNumeroBagni();
								String sGiardino;
								if(elencoAppartamenti.get(i).isGiardino()){
									sGiardino = "Si";
								} else {
									sGiardino = "No";
								}
								String numeroLetti = elencoAppartamenti.get(i).getNumeroLetti();
								request.getSession().setAttribute("loc", elencoAppartamenti);
							%>
							
							<div class="post">
                                <img src="../css/images/appartamento.png"  style="width:50px;height:50px;float: left">
                                <span class="span">Nome appartamento: <%out.println(nomeLocazione);%></span>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo :</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
                                            <td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
										<tr>
                                            <td><b>Numero stanze:</b> <%out.println(numeroStanze);%></td>
											<td><b>Numero letti:</b> <%out.println(numeroLetti);%></td>
                                            <td><b>Numero bagni:</b> <%out.println(numeroBagni);%></td>
										</tr>
										<tr>
                                            <td><b>Giardino:</b> <%out.println(sGiardino);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit"class="btnBlue200" value="PRENOTA">
										</div>
									</form>
								</div>	
								
							
							<%
							}
							
						} else if(commandInt == 2){
								for(int i=0;i<elencoBeb.size();i++){
								
								nomeLocazione = elencoBeb.get(i).getNomeLocazione();
                                prezzo = elencoBeb.get(i).getPrezzo();
								provincia = elencoBeb.get(i).getProvincia();
								indirizzo = elencoBeb.get(i).getIndirizzo();
								userLocatore = elencoBeb.get(i).getUserLocatore();
								descrizione = elencoBeb.get(i).getDescrizione();
								String sParcheggio;
								String sWifi;
								String sPet;
																	
								if(elencoBeb.get(i).isParcheggio()){
									sParcheggio = "Si";
								} else {
									sParcheggio = "No";
								}
								if(elencoBeb.get(i).isWifi()){
									sWifi = "Si";
								} else {
									sWifi = "No";
								}
								if(elencoBeb.get(i).isPet()){
									sPet = "Si";
								} else {
									sPet = "No";
								}
								String orarioColazione = elencoBeb.get(i).getOrarioColazione();
								request.getSession().setAttribute("loc", elencoBeb);
							%>
							
							<div class="post">
                                <img src="../css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
                                <span class="span">Nome b&b: <%out.println(nomeLocazione);%></span>
								
								<table style="width:100%">
                                         <tr>
                                             <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                             <td><b>Prezzo :</b> <%out.println(prezzo);%></td>
                                         </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
											<td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
                                        <tr>
                                            <td><b>Orario colazione:</b> <%out.println(orarioColazione);%></td>
                                        </tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" class="btnBlue200" value="PRENOTA">
										</div>
									</form>
								</div>	
							
							<%
							}
					} else if(commandInt == 3){
						for(int i=0;i<elencoCasaVacanze.size();i++){
							
							nomeLocazione = elencoCasaVacanze.get(i).getNomeLocazione();
                            prezzo = elencoCasaVacanze.get(i).getPrezzo();
							provincia = elencoCasaVacanze.get(i).getProvincia();
							indirizzo = elencoCasaVacanze.get(i).getIndirizzo();
							userLocatore = elencoCasaVacanze.get(i).getUserLocatore();
							descrizione = elencoCasaVacanze.get(i).getDescrizione();
							String sParcheggio;
							String sWifi;
							String sPet;
																
							if(elencoCasaVacanze.get(i).isParcheggio()){
								sParcheggio = "Si";
							} else {
								sParcheggio = "No";
							}
							if(elencoCasaVacanze.get(i).isWifi()){
								sWifi = "Si";
							} else {
								sWifi = "No";
							}
							if(elencoCasaVacanze.get(i).isPet()){
								sPet = "Si";
							} else {
								sPet = "No";
							}
							String numeroCamere = elencoCasaVacanze.get(i).getNumeroCamere();
							String numeroBagni = elencoCasaVacanze.get(i).getNumeroBagni();
							String sGiardino;
							if(elencoCasaVacanze.get(i).isGiardino()){
								sGiardino = "Si";
							} else {
								sGiardino = "No";
							}
							String numeroLetti = elencoCasaVacanze.get(i).getNumeroLetti();
							request.getSession().setAttribute("loc", elencoCasaVacanze);
							
						%>
						
						<div class="post">
                            <img src="../css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
                            <span class="span">Nome casa vacanza: <%out.println(nomeLocazione);%></span>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo :</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
                                            <td><b>Animali ammessi:</b> <%out.println(sPet);%></td>

										</tr>
										<tr>
                                            <td><b>Numero camere:</b> <%out.println(numeroCamere);%></td>
											<td><b>Numero letti:</b> <%out.println(numeroLetti);%></td>
                                            <td><b>Numero bagni:</b> <%out.println(numeroBagni);%></td>
										</tr>
										<tr>
                                            <td><b>Giardino:</b> <%out.println(sGiardino);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" class="btnBlue200" value="PRENOTA">
										</div>
									</form>
								</div>	
						
					
						<%
						}
					} else if(commandInt == 4){
						for(int i=0;i<elencoOstelli.size();i++){
							
							nomeLocazione = elencoOstelli.get(i).getNomeLocazione();
                            prezzo = elencoOstelli.get(i).getPrezzo();
							provincia = elencoOstelli.get(i).getProvincia();
							indirizzo = elencoOstelli.get(i).getIndirizzo();
							userLocatore = elencoOstelli.get(i).getUserLocatore();
							descrizione = elencoOstelli.get(i).getDescrizione();
							String sParcheggio;
							String sWifi;
							String sPet;
																
							if(elencoOstelli.get(i).isParcheggio()){
								sParcheggio = "Si";
							} else {
								sParcheggio = "No";
							}
							if(elencoOstelli.get(i).isWifi()){
								sWifi = "Si";
							} else {
								sWifi = "No";
							}
							if(elencoOstelli.get(i).isPet()){
								sPet = "Si";
							} else {
								sPet = "No";
							}
							request.getSession().setAttribute("loc", elencoOstelli);
						%>
						
						<div class="post">
                            <img src="../css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
                            <span class="span">Nome ostello: <%out.println(nomeLocazione);%></span>
									<table style="width:100%">
                                        <tr>
                                            <td><b>Username locatore:</b> <%out.println(userLocatore);%></td>
                                            <td><b>Prezzo :</b> <%out.println(prezzo);%></td>
                                        </tr>
										<tr>
											<td><b>Provincia:</b> <%out.println(provincia);%></td>
											<td><b>Indirizzo:</b> <%out.println(indirizzo);%></td>
										</tr>
										<tr>
                                            <td><b>Parcheggio:</b> <%out.println(sParcheggio);%></td>
											<td><b>Wifi:</b> <%out.println(sWifi);%></td>
                                            <td><b>Animali ammessi:</b> <%out.println(sPet);%></td>
										</tr>
									</table>
									<div class="break-word">
									<p><b>Descrizione:</b> <%out.println(descrizione);%></p>
									</div>
									<form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
									<div>
										<input type="hidden" name="id" value="<%out.println(i);%>">
										<input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
										<input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
										<input type="submit" class="btnBlue200" value="PRENOTA">
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
