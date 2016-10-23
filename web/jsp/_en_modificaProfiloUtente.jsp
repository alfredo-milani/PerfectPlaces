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
<%@page import="entity.*"%>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %>

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
                <h1>Edit Profile</h1>
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

                        <h2 class="title"> CHANGE YOUR PROFILE

                            <%
                                String username = c.getUser();
                                Utente u = null;

                                String param = request.getParameter("Mpu");
                                if (!c.getLogged()) {
                            %>

                            <font color="red"> Error! Session expired. Sign back in to continue. </font>

                            <%
                            } else {
                                try {
                                    u = cgp.ottieniUtente(username);
                                } catch (DeserializzazioneException e) {
                                    e.printStackTrace();
                                }
                                if (param != null) {
                                    String nome = request.getParameter("nome");
                                    String cognome = request.getParameter("cognome");
                                    String email = request.getParameter("email");
                                    String vecchiaPassword = request.getParameter("vecchiaPassword");
                                    String nuovaPassword = request.getParameter("nuovaPassword");
                                    String confermaNuovaPassword = request.getParameter("confermaNuovaPassword");
                                    int controllo = 0;

                                    try {
                                        controllo = cgp.modificaProfilo(username, nome, cognome, email, vecchiaPassword, nuovaPassword, confermaNuovaPassword);
                                    } catch (DeserializzazioneException | SerializzazioneException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        u = cgp.ottieniUtente(username);
                                    } catch (DeserializzazioneException e) {
                                        e.printStackTrace();
                                    }

                                    switch (controllo) {
                                        case 0:
                            %>

                            <font color="green"> Edited profile correctly </font>

                            <%
                                    break;

                                case 1:
                            %>

                            <font color="red"> Incorrect old password </font>

                            <%
                                    break;

                                case 2:
                            %>

                            <font color="red"> The new password and confirmation of the new password are not equal </font>

                            <%
                                    break;

                                case 3:
                            %>

                            <font color="red"> The field 'New Password' turns out to be empty </font>

                            <%
                                    break;

                                case 4:
                            %>

                            <font color="red"> Session has timed out. Please sign back in to continue </font>

                            <%
                                                break;
                                        }
                                    }
                                }
                            %>

                        </h2>
                        <form action="_en_modificaProfiloUtente.jsp?Mpu=1" enctype="application/x-www-form-urlencoded" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="nome">First Name:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nome" type="text" name="nome" size="30" value="<%out.println(u == null ? "" : u.getNome());%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="cognome">Last Name:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="cognome" type="text" name="cognome" size="30" value="<%out.println(u == null ? "" : u.getCognome());%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="email">Email:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="email" type="text" name="email" size="30" value="<%out.println(u == null ? "" : u.getEmail());%>"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="vecchia_psw">Old Password:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="vecchia_psw" type="password" name="vecchiaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="nuova_psw">New Password:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="nuova_psw" type="password" name="nuovaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="conferma_new_psw">Confirm New Password:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn" id="conferma_new_psw" type="password" name="confermaNuovaPassword" size="30" value=""/>
                                    </td>
                                </tr>

                            </table>
                            <center>
                                <br/><br/><br/>
                                <input class="btn_2" type="submit" value="Save changes"/>
                            </center>
                        </form>
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