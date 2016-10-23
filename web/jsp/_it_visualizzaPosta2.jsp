<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<jsp:useBean id="cgp" scope="session" class="control.ControlloreGestioneProfilo"/>
<%@page import="control.ControlloreGestionePosta" %>
<%@page import="entity.Messaggio" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="entity.Utente" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="it" xml:lang="it" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
<div id="wrapper">
	<div id="header-wrapper">
		<div id="header">
			<div id="logo">
				<h1>Messaggi</h1>
				<h2> Sei registrato come: <% out.println(c.getUser()); %> </h2>
			</div>
		</div>
	</div>
	<!-- end #header -->
	<div id="page">
		<div id="page-bgtop">
            <!-- Menu -->
            <ul class="topnav" id=myTopnav">
                <li><a href="_it_utente.jsp">HOME</a></li>
                <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                <li><a href="_it_profiloUtente.jsp">Visualizza profilo</a></li>
                <li><a href="_it_posta.jsp">Posta</a></li>
                <li><a href="_it_logout.jsp">Esci</a></li>
            </ul>

			<div id="page-bgbtm">
				<div id="content">
					
					<div class="post">
							<h2><strong>Messaggio selezionato:</strong>

                                <%
                                    String strCodice = request.getParameter("Cod");
                                    Messaggio messaggio = null;
                                    if (c.getLogged()) {
                                        if (strCodice != null) {
                                            int codice = Integer.parseInt(strCodice);
                                            ControlloreGestionePosta cgposta = new ControlloreGestionePosta();
                                            try {
                                                messaggio = cgposta.ricercaMessaggioPerCodice(codice);
                                            } catch (DeserializzazioneException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    } else {
                                %>

                                        <font color="red"> Errore! Effettua di nuovo l'accesso per visualizzare i messaggi </font>

                                <% } %>

                            </h2>
					</div>
					
					<div class="post">
						<table width="100%">
							<tr>
								<td><label>Mittente:</label></td>

                                <%
                                    if (c.getLogged() && strCodice != null && messaggio != null) {
                                %>

								        <td><%out.println(messaggio.getMittente());%></td>

                                <% } %>

							</tr>

							<tr>
								<td><label>Oggetto:</label></td>

                                <%
                                    if (c.getLogged() && strCodice != null && messaggio != null) {
                                %>

								        <td><%out.println(messaggio.getOggetto());%></td>

                                <% } %>

							</tr>
                        </table>

                        <br />
						<label>Contenuto:</label><br /><br />

                        <%
                            if (c.getLogged() && strCodice != null && messaggio != null) {
                        %>

                                <div class="break-word">
                                    <%out.println(messaggio.getContenuto());%>
                                </div>

                        <% } %>

                        <br /><br />

                        <table align="center">
                            <tr>
                                <td>
                                    <form action="_it_scriviMessaggio.jsp?Dest=<%= messaggio == null ? "" : messaggio.getMittente() %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input class="btn_2" type="submit" value="Rispondi"/>
                                        </div>
                                    </form>
                                </td>
                                <td>
                                    <%
                                        Utente des = null;
                                        if (messaggio != null) {
                                            try {
                                                des = cgp.ottieniUtente(messaggio.getMittente());
                                            } catch (DeserializzazioneException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    %>
                                    <form action="mailto:<%= des == null ? "" : des.getEmail() %>?subject=<%= messaggio == null ? "" : messaggio.getOggetto() %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input style="width: 200px" class="btn_2" type="submit" value="Rispondi con Client esterno" />
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </div>
					
					
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
