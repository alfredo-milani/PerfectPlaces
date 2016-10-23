<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>

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
                <h1> Mail </h1>
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
                        <h2><strong> Choose what to do: </strong>

                            <%
                                if (!c.getLogged()) {
                            %>

                            <font size="4px" color="red"> Error! Session expired. Sign back in to continue. </font>

                            <%
                                }
                            %>

                        </h2>
                    </div>

                    <center>
                        <div class="post">
                            <table>
                                <tr>
                                    <td><h1><center>Use external client</center></h1></td>
                                    <td><h1><center>Write message</center></h1></td>
                                    <td><h1><center>Read Mail</center></h1></td>
                                </tr>

                                <tr>
                                    <td>
                                        <h2 class="title">
                                            <center>
                                                <!--
                                                     Per evitare di trasmettere in chiaro gli indirizzi e-mail degli utenti
                                                       si preferisce aprire direttamente il client esterno e comporre ed inviare
                                                       il messaggio direttamente da questo
                                                -->
                                                <a href="mailto:">
                                                    <img src="../css/images/email_client.png" width="250" height="250" alt="Img apri clien email"/>
                                                </a>
                                            </center>
                                        </h2>
                                    </td>
                                    <td>
                                        <h2 class="title">
                                            <center>
                                                <a href="_en_scriviMessaggio.jsp">
                                                    <img src="../css/images/write_email.png" width="250" height="250" alt="Img scrivi msg"/>
                                                </a>
                                            </center>
                                        </h2>
                                    </td>
                                    <td>
                                        <h2 class="title">
                                            <center>
                                                <a href="_en_visualizzaPosta.jsp">
                                                    <img src="../css/images/read_email.png" width="250" height="250" alt="Img leggi msg"/>
                                                </a>
                                            </center>
                                        </h2>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </center>

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
