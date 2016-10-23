<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="constants.Constants" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <!--<title>Perfect Places</title>-->
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js"></script>
</head>

<body>
<%
    String un = request.getParameter("username");
    String pw = request.getParameter("password");
    try {
        c.login(un, pw);
    } catch (DeserializzazioneException e) {
        e.printStackTrace();
    }
%>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">

                <%
                    if (c.getLogged()) {
                %>

                    <h1> Welcome! </h1>
                    <h2> You are logged in as: <% out.println(c.getUser()); %> </h2>

                <%  } else {
                        String redirectURL = "http://" + Constants.HOST_PORT + "jsp/_en_index.jsp?errLog=1";
                        response.sendRedirect(redirectURL);
                }
                %>

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

                    <div class="language">
                        <table>
                            <tr>
                                <td>
                                    <label for="select"> Language: </label>
                                </td>
                                <td>
                                    <select id="select" class="btn" onchange="changeLang(this, '<%= c.getUser()%>', '<%= c.getPsw() %>')">
                                        <option id="en" value="english">English</option>
                                        <option id="it" value="italian">Italian</option>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div class="post">
                        <h2 class="title">CHOOSE WHAT TO DO!</h2>
                        <table>
                            <tr>
                                <td>
                                    <br /><br />
                                    <h1 class="title">
                                        <strong>
                                            <a href="areaViaggiatore.jsp?username=<%=request.getParameter("username")%>\">TRAVELLER AREA</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="areaProprietario.jsp?username=<%=request.getParameter("username")%>\">OWNER AREA</a>
                                        </strong>
                                    </h1>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <br /><br /><br /><br />
                                    You can access the two areas of the system:<br /><br />
                                    Travelling area: Where you can easily find the most suitable locations to you and manage your bookings!<br /><br />
                                    Owner Area: Where you can post new ads and manage your locations!
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
