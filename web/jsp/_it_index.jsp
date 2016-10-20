<%--
  Created by IntelliJ IDEA.
  User: alfredo
  Date: 20/10/16
  Time: 15.41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="b" class="control.ControlloreRegistrazione" scope="session"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html lang="it" xml:lang="it" xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title> Perfect Places </title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js"></script>
</head>

<body>
<div id="logo">
    <img src="../css/images/logo.png" alt="Logo"/>
</div>

<div class="language">
    <table>
        <tr>
            <td>
                <label for="select"> Lingua: </label>
            </td>
            <td>
                <select id="select" class="btn" onchange="changeLang(this)">
                    <option id="it" value="italian">Italiano</option>
                    <option id="en" value="english">Inglese</option>
                </select>
            </td>
        </tr>
    </table>
</div>

<div id="page">
    <table id="table_main">
        <tr>
            <td>
                <p id="p_1"> Il modo più semplice per trovare il tuo alloggio ideale </p>
            </td>
        </tr>
        <tr>
            <td>
                <!-- inserire nel bottone la referenza alla pagina degli aiuti -->
                <input type="button" id="btn_help" value="Vedi come..." >
            </td>
        </tr>
    </table>

    <div id="page-bgtop">
        <div id="page-bgbtm">
            <!-- end #content -->
            <table width="100%" border="0">
                <tr valign="top">

                    <td align="center" id="login">

                        <%
                            String paramLog = request.getParameter("errLog");
                            if (paramLog != null && paramLog.equals("1")) {
                        %>

                            <h4 class="main"> <font color="red"> L'utente non &egrave; presente nel sistema o la password inserita &egrave; errata! </font> </h4>

                        <% } %>

                        <h2 class="main"> <strong> ACCEDI </strong> </h2>
                        <form action="../jsp/_it_utente.jsp" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="username"> Nome utente: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="username" id="username"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="password"> Password: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="password" name="password" id="password"/>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <input class="btn_2" type="submit" value="Accedi" /><br /><br />
                            <br />
                        </form>
                    </td>

                    <td align="center">

                        <%
                            String paramReg = request.getParameter("Reg");
                            if (paramReg != null && paramReg.equals("1")) {
                                String un = request.getParameter("username_2");
                                String pw = request.getParameter("password");
                                String pw2 = request.getParameter("password2");
                                String nome = request.getParameter("nome");
                                String cognome = request.getParameter("cognome");
                                String email = request.getParameter("email");

                                int caseReg = b.registrazione(un, pw, pw2, nome, cognome, email);
                                switch (caseReg) {
                                    case 0:
                        %>

                                        <h4 class="main"> <font color="green"> Registrazione effettuata con successo </font> </h4>

                        <%
                                        break;

                                    case 1:
                        %>

                                        <h4 class="main"> <font color="red"> Non puoi lasciare campi del form vuoti! </font> </h4>

                        <%
                                        break;

                                    case 2:
                        %>

                                        <h4 class="main"> <font color="red"> Le password di conferma deve essere uguale a quella inserita! </font> </h4>

                        <%
                                        break;

                                    case 3:
                        %>

                                        <h4 class="main"> <font color="red"> Lo Username inserito e' gia' stato utilizzato! </font> </h4>

                        <%
                                        break;
                                }
                            }
                        %>

                        <h2 class="main"><strong>REGISTRAZIONE</strong></h2>
                        <form action="../jsp/_it_index.jsp?Reg=1" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="username_2"> Nome utente: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="username_2" id="username_2" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="password_2"> Password: </label>
                                    </td>
                                    <td><input class="btn" type="password" name="password" id="password_2"/></td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="conf_psw"> Conferma Password: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="password" name="password2" id="conf_psw"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="nome"> Nome: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="nome" id="nome"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="cognome"> Cognome: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="cognome" id="cognome"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="email"> Email: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="email" id="email"/>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            <input class="btn_2" type="submit" value="Registrati" />
                            <br />
                        </form>
                    </td>
                </tr>
            </table>
            <!-- end #bar -->
            <!-- to keep space -->
            <div style="clear: both;">&nbsp;</div>
        </div>
    </div>
</div>
<!-- end #page -->

<!-- MJ: qui nel footer potresti mettere le FAQ e gli aiuti (NOTA: per gli aiuti ho me anche il bottone con
    la scritta: "Vedi come..." -->
<div id="footer"></div>
<!-- end #footer -->
</body>
</html>
