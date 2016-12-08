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
<%@page import="java.lang.String" %>
<%@page import="control.ControlloreInserimentoLocazione"  %>
<%@page import="exception.SerializzazioneException" %>
<%@page import="exception.DeserializzazioneException" %>
<%@ page import="entity.Locazione" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%
	String command = request.getParameter("command");
	String username = c.getUser();
	
	boolean control=false;
	ControlloreInserimentoLocazione cil = new ControlloreInserimentoLocazione();

    String nomeLocazione = request.getParameter("nomeLocazione");
    String provincia = request.getParameter("provincia");
    String indirizzo = request.getParameter("indirizzo");
    String prezzo = request.getParameter("prezzo");
    String descrizione = request.getParameter("descrizione");
    String parcheggio = request.getParameter("parcheggio");
    String wifi = request.getParameter("wifi");
    String pet = request.getParameter("pet");
	boolean bpark = false;
    boolean bwifi = false;
    boolean bpet = false;

    out.println("command" + command);
    switch (command) {
        case "0": {
            // Caso Albergo
            if (parcheggio.equals("true")) {
                bpark = true;
            }
            if (wifi.equals("true")) {
                bwifi = true;
            }
            if (pet.equals("true")) {
                bpet = true;
            }
            String postiTotali = request.getParameter("postiTotali");
            String tipoPensione = request.getParameter("tipoPensione");
            String orarioColazione = request.getParameter("orarioColazione");
            String orarioPranzo = request.getParameter("orarioPranzo");
            String orarioCena = request.getParameter("orarioCena");

            try {
                control = cil.inserisciAlbergo(nomeLocazione,postiTotali,provincia, indirizzo, username, prezzo,
                        descrizione, bpark, bwifi, bpet,
                        tipoPensione, orarioColazione, orarioPranzo, orarioCena);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "1": {
            //Caso Appartamento
            if (parcheggio.equals("true")) {
                bpark = true;
            }
            if (wifi.equals("true")) {
                bwifi = true;
            }
            if (pet.equals("true")) {
                bpet = true;
            }
            String numeroLetti = request.getParameter("numeroLetti");
            String numeroStanze = request.getParameter("numeroStanze");
            String numeroBagni = request.getParameter("numeroBagni");
            String giardino = request.getParameter("giardino");
            boolean bgiardino = false;
            if (giardino.equals("true")) {
                bgiardino = true;
            }
            Integer i = 1;
            String s = Integer.toString(i);
            out.println(s);
            try {
                control = cil.inserisciAppartamento(nomeLocazione,s, provincia, indirizzo, username, prezzo, descrizione,
                        bpark, bwifi, bpet, numeroStanze, numeroBagni, bgiardino, numeroLetti);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "2": {
            // Caso Beb
            if (parcheggio.equals("true")) {
                bpark = true;
            }
            if (wifi.equals("true")) {
                bwifi = true;
            }
            if (pet.equals("true")) {
                bpet = true;
            }
            String postiTotali = request.getParameter("postiTotali");
            String orarioColazione = request.getParameter("orarioColazione");

            try {
                control = cil.inserisciBeb(nomeLocazione,postiTotali,provincia, indirizzo, username, prezzo, descrizione,
                        bpark, bwifi, bpet, orarioColazione);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "3": {
            // Caso Casa Vacanze
            if (parcheggio.equals("true")) {
                bpark = true;
            }
            if (wifi.equals("true")) {
                bwifi = true;
            }
            if (pet.equals("true")) {
                bpet = true;
            }

            String numeroLetti = request.getParameter("numeroLetti");
            String numeroCamere = request.getParameter("numeroCamere");
            String numeroBagni = request.getParameter("numeroBagni");
            String giardino = request.getParameter("giardino");
            boolean bgiardino = false;

            if (giardino.equals("true")) {
                bgiardino = true;
            }
            Integer i = 1;
            String s = Integer.toString(i);
            try {
                control = cil.inserisciCasaVacanza(nomeLocazione, s, provincia, indirizzo, username, prezzo, descrizione,
                        bpark, bwifi, bpet, numeroCamere, numeroBagni, bgiardino, numeroLetti);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
        case "4": {
            // Caso Ostello
            if (parcheggio.equals("true")) {
                bpark = true;
            }
            if (wifi.equals("true")) {
                bwifi = true;
            }
            if (pet.equals("true")) {
                bpet = true;
            }
            String postiTotali = request.getParameter("postiTotali");
            String tipoPensione = request.getParameter("tipoPensione");

            try {
                control = cil.inserisciOstello(nomeLocazione,postiTotali,provincia, indirizzo, username, prezzo, descrizione,
                        bpark, bwifi, bpet, tipoPensione);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
            break;
        }
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

                </h2>
            </div>
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