<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@page import="control.ControlloreGestionePosta" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Messaggio" %>
<%@ page import="exception.DeserializzazioneException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="it" xml:lang="it" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css"/>
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen"/>
</head>

<%
    String username = c.getUser();
    boolean loggedin = c.getLogged();
    String oggetto;
    String mittente;
    String data;
    ControlloreGestionePosta cgp = new ControlloreGestionePosta();
    ArrayList<Messaggio> elencoMessaggiUser = null;
    try {
        elencoMessaggiUser = cgp.ricercaMessaggiPerDestinatario(username);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
%>

<body>
<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1>Visualizza i tuoi messaggi</h1>
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
                        <h2><strong> Posta in entrata </strong></h2>
                    </div>

                    <div class="post">
                        <table width="100%">
                            <tr>
                                <td><label>Mittente</label></td>
                                <td><label>Oggetto</label></td>
                                <td><label>Data</label></td>
                                <td><label>Contenuto</label></td>
                            </tr>

                            <%
                                if (loggedin) {
                                    if (elencoMessaggiUser != null && elencoMessaggiUser.size() != 0) {
                                        for (Messaggio anElencoMessaggiUser : elencoMessaggiUser) {
                                            oggetto = anElencoMessaggiUser.getOggetto();
                                            mittente = anElencoMessaggiUser.getMittente();
                                            data = anElencoMessaggiUser.getData();
                            %>

                                            <tr>
                                                <td>
                                                    <% out.println(mittente); %>
                                                </td>
                                                <td>
                                                    <% out.println(oggetto); %>
                                                </td>
                                                <td>
                                                    <% out.println(data); %>
                                                </td>
                                                <td>
                                                    <form action="_it_visualizzaPosta2.jsp?Cod=<%= String.valueOf(anElencoMessaggiUser.getCodice()) %>" enctype="application/x-www-form-urlencoded" method="post">
                                                        <div>
                                                            <input class="btn" type="submit" value="Leggi"/>
                                                        </div>
                                                    </form>
                                                </td>
                                            </tr>

                            <%
                                        }
                                    } else {

                                        %> <tr><td><font size="4px"> <% out.println("Per il momento non ci sono messaggi da mostrare"); %> </font></td></tr> <%

                                    }
                                } else {

                                        %> <tr><td><font size="4px" color="red"> <% out.println("Errore! Effettua di nuovo l'accesso per leggere i tuoi messaggi"); %> </font></td></tr> <%

                                }
                            %>

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
    <div id="footer"></div>
<!-- end #footer -->
</body>
</html>
