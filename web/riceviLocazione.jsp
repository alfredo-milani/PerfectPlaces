<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>S&amp;M</title>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<%@page import="control.ControlloreInserimentoLocazione"  %>
<jsp:useBean id="b" scope="session" class="control.ControlloreLogin"/>
<%
	String command = request.getParameter("command");
	String username = b.getUser();
	
	boolean control=false;

	ControlloreInserimentoLocazione cil = new ControlloreInserimentoLocazione();
	


	if(command.equals("0")){
		
		// Caso Albergo
		String nomeLocazione = request.getParameter("nomeLocazione");
		String indirizzo = request.getParameter("indirizzo");
		String prezzo = request.getParameter("prezzo");
		String descrizione = request.getParameter("descrizione");
		String parcheggio = request.getParameter("parcheggio");
		boolean bpark;
		if(parcheggio.equals("true")){
			bpark = true;
		} else {
			bpark = false;
		}
		String wifi = request.getParameter("wifi");
		boolean bwifi;
		if(wifi.equals("true")){
			bwifi = true;
		} else {
			bwifi = false;
		}
		String pet = request.getParameter("pet");
		boolean bpet;
		if(pet.equals("true")){
			bpet = true;
		} else {
			bpet = false;
		}
		String camereTotali = request.getParameter("camereTotali");
		String tipoPensione = request.getParameter("tipoPensione");
		String orarioColazione = request.getParameter("orarioColazione");
		String orarioPranzo = request.getParameter("orarioPranzo");
		String orarioCena = request.getParameter("orarioCena");
		
		
		control = cil.inserisciAlbergo(nomeLocazione, indirizzo, username, prezzo, descrizione, bpark, bwifi, bpet, camereTotali, 
				tipoPensione, orarioColazione, orarioPranzo, orarioCena);
		
	} else if(command.equals("1")){
		//Caso Appartamento
		String nomeLocazione = request.getParameter("nomeLocazione");
		String indirizzo = request.getParameter("indirizzo");
		String prezzo = request.getParameter("prezzo");
		String descrizione = request.getParameter("descrizione");
		String parcheggio = request.getParameter("parcheggio");
		boolean bpark;
		if(parcheggio.equals("true")){
			bpark = true;
		} else {
			bpark = false;
		}
		String wifi = request.getParameter("wifi");
		boolean bwifi;
		if(wifi.equals("true")){
			bwifi = true;
		} else {
			bwifi = false;
		}
		String pet = request.getParameter("pet");
		boolean bpet;
		if(pet.equals("true")){
			bpet = true;
		} else {
			bpet = false;
		}
		String numeroStanze = request.getParameter("numeroStanze");
		String numeroBagni = request.getParameter("numeroBagni");
		String giardino = request.getParameter("giardino");
		boolean bgiardino;
		if(giardino.equals("true")){
			bgiardino = true;
		} else {
			bgiardino = false;
		}
		String numeroLetti = request.getParameter("numeroLetti");
	
		
		
		control = cil.inserisciAppartamento(nomeLocazione, indirizzo, username, prezzo, descrizione,
				bpark, bwifi, bpet, numeroStanze, numeroBagni, bgiardino, numeroLetti);
		
	
	} else if(command.equals("2")){
		// Caso Beb
		String nomeLocazione = request.getParameter("nomeLocazione");
		String indirizzo = request.getParameter("indirizzo");
		String prezzo = request.getParameter("prezzo");
		String descrizione = request.getParameter("descrizione");
		String parcheggio = request.getParameter("parcheggio");
		boolean bpark;
		if(parcheggio.equals("true")){
			bpark = true;
		} else {
			bpark = false;
		}
		String wifi = request.getParameter("wifi");
		boolean bwifi;
		if(wifi.equals("true")){
			bwifi = true;
		} else {
			bwifi = false;
		}
		String pet = request.getParameter("pet");
		boolean bpet;
		if(pet.equals("true")){
			bpet = true;
		} else {
			bpet = false;
		}
		String camereTotali = request.getParameter("camereTotali");
		String orarioColazione = request.getParameter("orarioColazione");
		
		control = cil.inserisciBeb(nomeLocazione, indirizzo, username, prezzo, descrizione,
				bpark, bwifi, bpet, camereTotali, orarioColazione);
		
	}else if(command.equals("3")){
		// Caso Casa Vacanze
		String nomeLocazione = request.getParameter("nomeLocazione");
		String indirizzo = request.getParameter("indirizzo");
		String prezzo = request.getParameter("prezzo");
		String descrizione = request.getParameter("descrizione");
		String parcheggio = request.getParameter("parcheggio");
		boolean bpark;
		if(parcheggio.equals("true")){
			bpark = true;
		} else {
			bpark = false;
		}
		String wifi = request.getParameter("wifi");
		boolean bwifi;
		if(wifi.equals("true")){
			bwifi = true;
		} else {
			bwifi = false;
		}
		String pet = request.getParameter("pet");
		boolean bpet;
		if(pet.equals("true")){
			bpet = true;
		} else {
			bpet = false;
		}
		String numeroCamere = request.getParameter("numeroCamere");
		String numeroBagni = request.getParameter("numeroBagni");
		String giardino = request.getParameter("giardino");
		boolean bgiardino;
		if(giardino.equals("true")){
			bgiardino = true;
		} else {
			bgiardino = false;
		}
		String numeroLetti = request.getParameter("numeroLetti");
	
		
		
		control = cil.inserisciCasaVacanza(nomeLocazione, indirizzo, username, prezzo, descrizione,
				bpark, bwifi, bpet, numeroCamere, numeroBagni, bgiardino, numeroLetti);
		
	} else if(command.equals("4")){
		// Caso Ostello
		String nomeLocazione = request.getParameter("nomeLocazione");
		String indirizzo = request.getParameter("indirizzo");
		String prezzo = request.getParameter("prezzo");
		String descrizione = request.getParameter("descrizione");
		String parcheggio = request.getParameter("parcheggio");
		boolean bpark;
		if(parcheggio.equals("true")){
			bpark = true;
		} else {
			bpark = false;
		}
		String wifi = request.getParameter("wifi");
		boolean bwifi;
		if(wifi.equals("true")){
			bwifi = true;
		} else {
			bwifi = false;
		}
		String pet = request.getParameter("pet");
		boolean bpet;
		if(pet.equals("true")){
			bpet = true;
		} else {
			bpet = false;
		}
		String numeroLetti = request.getParameter("numeroLetti");
	
		
		
		control = cil.inserisciOstello(nomeLocazione, indirizzo, username, prezzo, descrizione,
				bpark, bwifi, bpet, numeroLetti);
		
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
				<h1><a href="#">Conferma Inserimento</a></h1>
				
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
			<div id="page-bgbtm">
				<div id="content">
				
					<% if(control){%> 
					<div class="post">
							<h2><strong>Inserimento completato</strong></h2>
							
							
	
							
					</div>
					<% }else{ %>
					<div class="post">
							<h2><strong>Hai lasciato qualche campo vuoto</strong></h2>
							
							
	
							
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
						<h2><strong><a href="logout.jsp">Logout</a></strong></h2>
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