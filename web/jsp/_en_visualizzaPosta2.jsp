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

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">

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
                <h1>Messages</h1>
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
                        <h2><strong>Selected message:</strong>

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

                            <font color="red"> Error! Redo access to view messages </font>

                            <% } %>

                        </h2>
                    </div>

                    <div class="post">
                        <table width="100%">
                            <tr>
                                <td><label>Sender:</label></td>

                                <%
                                    if (c.getLogged() && strCodice != null && messaggio != null) {
                                %>

                                <td><%out.println(messaggio.getMittente());%></td>

                                <% } %>

                            </tr>

                            <tr>
                                <td><label>Object:</label></td>

                                <%
                                    if (c.getLogged() && strCodice != null && messaggio != null) {
                                %>

                                <td><%out.println(messaggio.getOggetto());%></td>

                                <% } %>

                            </tr>
                        </table>

                        <br />
                        <label>Content:</label><br /><br />

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
                                    <form action="_en_scriviMessaggio.jsp?Dest=<%= messaggio == null ? "" : messaggio.getMittente() %>" enctype="application/x-www-form-urlencoded" method="post">
                                        <div>
                                            <input class="btn_2" type="submit" value="Reply"/>
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
                                            <input style="width: 200px" class="btn_2" type="submit" value="Reply with external Client" />
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
