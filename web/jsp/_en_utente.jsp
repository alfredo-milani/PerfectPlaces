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
<jsp:useBean id="c" scope="page" class="control.ControlloreLogin"/>

<%
    String un = request.getParameter("username");
    String pw = request.getParameter("password");
    c.login(un, pw);
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
                <%  } else { %>
                <h1> Attention! </h1>
                <%  } %>
            </div>
        </div>
    </div>

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
    <!-- end #header -->

    <div id="page">
        <div id="page-bgtop">
            <div id="page-bgbtm">
                <div id="content">

                    <!--  Parte utente loggato -->
                    <% if(c.getLogged()) { %>
                    <div class="post">
                        <h2 class="title">CHOOSE WHAT TO DO!</h2>
                        <table>
                            <tr>
                                <td>
                                    <br /><br />
                                    <h1 class="title">
                                        <strong>
                                            <a href="areaViaggiatore.jsp?username=<%=request.getParameter("username")%>">TRAVELLER AREA</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="areaProprietario.jsp?username=<%=request.getParameter("username")%>">OWNER AREA</a>
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
                    <% } %>

                    <!--  Parte utente non loggato -->
                    <% if (!c.getLogged()) {  %>
                    <div class="post">
                        <h2 class="title">Error!</h2>

                        <h1>The user is not present in the system or the entered password is wrong!</h1>

                        <br />

                        <a href="../html/_en_index.html">Back to Home Page</a>
                    </div>
                    <% } %>

                    <div style="clear: both;">&nbsp;</div>
                </div>
                <!-- end #content -->

                <!-- Menu -->
                <div id="sidebar">
                    <% if (c.getLogged()) {  %>

                    <ul>
                        <li>
                            <center>
                                <h2><strong><a href="areaViaggiatore.jsp">Traveller area</a></strong></h2>
                            </center>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <center>
                                <h2><strong><a href="areaProprietario.jsp">Owner area</a></strong></h2>
                            </center>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <center>
                                <h2><strong><a href="profiloUtente.jsp">View profile</a></strong></h2>
                            </center>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <center>
                                <h2><strong><a href="posta.jsp">Mail</a></strong></h2>
                            </center>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <center>
                                <h2><strong><a href="_en_logout.jsp">Logout</a></strong></h2>
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
