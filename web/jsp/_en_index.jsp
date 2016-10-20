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

<html lang="en" xml:lang="en" xmlns="http://www.w3.org/1999/xhtml">

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
                <label for="select"> Language: </label>
            </td>
            <td>
                <select id="select" class="btn" onchange="changeLang(this)">
                    <option id="en" value="english">English</option>
                    <option id="it" value="italian">Italian</option>
                </select>
            </td>
        </tr>
    </table>
</div>

<div id="page">
    <table id="table_main">
        <tr>
            <td>
                <p id="p_1"> The easiest way to find your ideal accommodation </p>
            </td>
        </tr>
        <tr>
            <td>
                <!-- inserire nel bottone la referenza alla pagina degli aiuti -->
                <input type="button" id="btn_help" value="See how to..." >
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

                        <h4 class="main"> <font color="red"> The user is not present in the system or the entered password is wrong! </font> </h4>
                        <h2 class="main"> <strong> LOGIN </strong> </h2>

                        <%  } %>

                        <h2 class="main"><strong>LOGIN</strong></h2>
                        <form action="../jsp/_en_utente.jsp" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="username"> Username: </label>
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
                            <input class="btn_2" type="submit" value="Login" /><br /><br />
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

                                        <h4 class="main"> <font color="green"> Registered successfully </font> </h4>

                        <%
                                break;

                            case 1:
                        %>

                                        <h4 class="main"> <font color="red"> You can not leave empty form fields! </font> </h4>

                        <%
                                break;

                            case 2:
                        %>

                                        <h4 class="main"> <font color="red"> The confirmation password must be the same as inserted! </font> </h4>

                        <%
                                break;

                            case 3:
                        %>

                                        <h4 class="main"> <font color="red"> The username entered is already used! </font> </h4>

                        <%
                                        break;
                                }
                            }
                        %>

                        <h2 class="main"><strong>SIGN UP</strong></h2>
                        <form action="../jsp/_en_index.jsp?Reg=1" method="post">
                            <table>
                                <tr>
                                    <td>
                                        <label for="username_2"> Username: </label>
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
                                        <label for="conf_psw"> Confirm Password: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="password" name="password2" id="conf_psw"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="nome"> First name: </label>
                                    </td>
                                    <td>
                                        <input class="btn" type="text" name="nome" id="nome"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label for="cognome"> Last name: </label>
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
                            <input class="btn_2" type="submit" value="Sign Up" />
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
