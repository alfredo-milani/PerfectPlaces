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
<%@ page import="exception.SerializzazioneException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">

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
    String eliminazione = null;
    ControlloreGestionePosta cgp = new ControlloreGestionePosta();
    ArrayList<Messaggio> elencoMessaggiUser = null;

    String codeMsg = request.getParameter("Cod");
    // Per maggiore robustezza
    String codeDel = request.getParameter("Del");
    if (codeMsg != null && codeDel != null) {
        if (c.getLogged() && Integer.parseInt(codeDel) == 1) {
            int intMsg = Integer.parseInt(codeMsg);
            try {
                eliminazione = cgp.eliminaMessaggio(intMsg);
            } catch (SerializzazioneException | DeserializzazioneException e) {
                e.printStackTrace();
            }
        }
    }
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
                <h1>View your posts</h1>
                <h2> You are logged in as: <% out.println(c.getUser()); %> </h2>
            </div>
        </div>
    </div>
    <!-- end #header -->
    <div id="page">
        <div id="page-bgtop">
            <!-- Menu -->
            <ul class="topnav" id=myTopnav">
                <li><a href="_en_utente.jsp">HOME</a></li>
                <li><a href="areaViaggiatore.jsp">Traveller Area</a></li>
                <li><a href="areaProprietario.jsp">Owner Area</a></li>
                <li><a href="_en_profiloUtente.jsp">View Profile</a></li>
                <li><a href="_en_posta.jsp">Mail</a></li>
                <li><a href="_en_logout.jsp">Logout</a></li>
            </ul>

            <div id="page-bgbtm">
                <div id="content">

                    <div class="post">
                        <h2><strong> Inbox </strong></h2>

                        <%  if (eliminazione != null) {
                            if (Integer.parseInt(eliminazione) == 0) { %>

                        <h5>
                            <font color="red"> Unable to delete the message </font>
                        </h5>

                        <% } else if (Integer.parseInt(eliminazione) == 1){ %>

                        <h4>
                            <font color="green"> Message deleted </font>
                        </h4>

                        <% }
                        }
                        %>

                    </div>

                    <div class="post">
                        <table width="100%">
                            <tr>
                                <td><label>Sender</label></td>
                                <td><label>Object</label></td>
                                <td><label>Date</label></td>
                                <td><label>Content</label></td>
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
                                    <form action="_en_visualizzaPosta2.jsp?Cod=<%= String.valueOf(anElencoMessaggiUser.getCodice()) %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input class="btn" type="submit" value="Read"/>
                                        </div>
                                    </form>
                                </td>

                                <td>
                                    <form action="_it_visualizzaPosta.jsp?Cod=<%= String.valueOf(anElencoMessaggiUser.getCodice()) %>&Del=1" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input class="btnDel" type="submit" value="Cancella"/>
                                        </div>
                                    </form>
                                </td>
                            </tr>

                            <%
                                }
                            } else {

                            %> <tr><td><font size="4px"> <% out.println("For the moment there are no posts to display"); %> </font></td></tr> <%

                            }
                        } else {

                        %> <tr><td><font size="4px" color="red"> <% out.println("Error! Redo access to read your posts"); %> </font></td></tr> <%

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
