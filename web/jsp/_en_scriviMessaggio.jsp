<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="exception.SerializzazioneException" %><%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<jsp:useBean id="p" scope="session" class="control.ControlloreGestionePosta"/>

<%
    String param = request.getParameter("Msg");
    String dest = request.getParameter("Dest");
    String mittente = c.getUser();
    String destinatario = dest == null ? "" : dest;
    String oggetto = "";
    String contenuto = "";
    int controllo = -1;
    if (param != null && param.equals("1")) {
        oggetto = request.getParameter("oggetto");
        destinatario = request.getParameter("destinatario");
        contenuto = request.getParameter("contenuto");
        try {
            controllo = p.scriviMessaggio(oggetto, mittente, destinatario, contenuto);
        } catch (DeserializzazioneException | SerializzazioneException e) {
            e.printStackTrace();
        }
    }
%>

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
                <h1>Write your message</h1>
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
                        <h2>
                            <strong> Message: </strong>

                            <%
                                switch (controllo) {
                                    case 0:
                            %>

                            <font color="green"> Message sent correctly </font>

                            <%
                                    break;

                                case 1:
                            %>

                            <font color="red"> Error! Enter at least one recipient </font>

                            <%
                                    break;

                                case 2:
                            %>

                            <font color="red"> Error! Enter the subject of your message </font>

                            <%
                                    break;

                                case 3:
                            %>

                            <font color="red"> Error! Enter the message content </font>

                            <%
                                    break;

                                case 4:
                            %>

                            <font color="red"> Error! You entered an incorrect recipient </font>

                            <%
                                    break;

                                case 5:
                            %>

                            <font color="red"> Session expired. Redo access. </font>

                            <%
                                    break;

                                case 6:
                            %>

                            <font color="red"> Warning! Sender and recipient are the same! </font>

                            <%
                                        break;
                                }
                            %>

                        </h2>
                    </div>

                    <div class="post">
                        <form action="_en_scriviMessaggio.jsp?Msg=1" enctype="application/x-www-form-urlencoded" method="post">
                            <table width="100%">
                                <tr>
                                    <td>
                                        <label>
                                            Sender: <font color="#1268b3"> <% out.println(mittente); %> </font>
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="destinatario">Recipient:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input id="destinatario" type="text" name="destinatario" class="btn"

                                                <%
                                                    if (destinatario != null) {
                                                %>

                                               value="<%= destinatario %>"

                                                <% } %>

                                        />
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="oggetto">Object:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input id="oggetto" type="text" name="oggetto" class="btn"

                                                <%
                                                    if (param != null && oggetto != null) {
                                                %>
                                               value="<%= oggetto %>"

                                                <% } %>

                                        />
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <label for="contenuto">Content:</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <textarea id="contenuto" name="contenuto" class="btn" rows="10" cols="100%"><%
                                            if (param != null && contenuto != null) {
                                                out.println(contenuto);
                                            }
                                        %></textarea>
                                    </td>
                                </tr>

                                <tr>
                                    <center>
                                        <td style="text-align: center">
                                            <input class="btn_2" type="submit" value="Send message" />
                                        </td>
                                    </center>
                                </tr>
                            </table>
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

<div id="footer"></div>
<!-- end #footer -->

</body>
</html>
