<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js"></script>
</head>

<body>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin" />
<jsp:useBean id="cgp" scope="session" class="control.ControlloreGestioneProfilo" />
<jsp:useBean id="crl" scope="session" class="control.ControlloreVisualizzaLocazioni" />
<%@page import="entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="constants.Constants" %>
<%@ page import="exception.DeserializzazioneException" %>
<%
    String username = c.getUser();
    Utente u = null;
    try {
        u = cgp.ottieniUtente(username);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
    String indirizzoImmagine = Constants.IMGS_PATH_REL_S + u.getImmagine();
    ArrayList<Locazione> locazioni = null;
    try {
        locazioni = crl.visualizzaLocazioni(username);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
%>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1>Personal Profile</h1>
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
                        <h2 class="title">YOUR PROFILE</h2>

                        <table width="100%">
                            <tr>
                                <td>
                                    <h2 class="title"><strong>Your data</strong></h2>
                                </td>
                                <td>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <p><img src="<%= indirizzoImmagine%>" width="150" height="150"
                                            alt="Immagine Profilo"/></p>
                                </td>
                                <td>
                                    <h3 class="blackclass">
                                        <strong>Username:</strong><%out.println(" " + u.getUsername());%> <br/>
                                        <strong>First Name:</strong><%out.println(" " + u.getNome());%> <br/>
                                        <strong>Last Name:</strong><%out.println(" " + u.getCognome());%> <br/>
                                        <strong>E-Mail:</strong><%out.println(" " + u.getEmail());%> <br/>
                                    </h3>
                                </td>
                            </tr>
                        </table>

                        <table>
                            <tr>
                                <td>
                                    <h1 class="title"><strong>Location:</strong></h1>
                                    <h3 class="blackclass">

                                        <%
                                            if (locazioni.size() == 0) {
                                                out.println("\n" +
                                                        "For now you have no location.");
                                            } else {
                                                int i;
                                                for (i = 0; i < locazioni.size() - 1; ++i) {
                                                    Locazione loc = locazioni.get(i);
                                                    out.println(loc.getNomeLocazione() + " / ");
                                                }
                                                out.println(locazioni.get(i).getNomeLocazione());
                                            }
                                        %>

                                    </h3>
                                    <br/>
                                    <br/>
                                    <h2>
                                        <strong><a href="_en_modificaProfiloUtente.jsp">Edit Profile</a></strong>
                                    </h2>
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