<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
    <script type="text/javascript" src="../js/functions.js" ></script>
</head>
<body>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<div id="menu-wrapper">
    <div id="menu">
    </div>
    <!-- end #menu -->
</div>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1><a href="#">Area Viaggiatore</a></h1>

            </div>
        </div>
    </div>
    <!-- end #header -->
    <div id="page">
        <div id="page-bgtop">
            <ul class="topnav" id=myTopnav">
                <li><a href="utente.jsp">HOME</a></li>
                <li><a href="areaViaggiatore.jsp">Area Viaggiatore</a></li>
                <li><a href="areaProprietario.jsp">Area Proprietario</a></li>
                <li><a href="profiloUtente.jsp">Visualizza profilo</a></li>
                <li><a href="posta.jsp">Posta</a></li>
                <li><a href="areaFaq.jsp">FAQ</a></li>
                <li><a href="logout.jsp">Esci</a></li>
            </ul>
            <div>
                <%
                    if (!c.getLogged()) {
                %>

                <p style="font-size: 30px; color: red"> Errore! Sessione scaduta. Accedi di nuovo per continuare. </p>

                <%
                    }
                %>

            </div>
        </div>
        <div id="page-bgbtm">
            <div id="content">
                <div class="post">
                    <h2><strong>Ricerca una locazione o gestisci le tue prenotazioni</strong></h2>
                 </div>
                    <table width="100%">
                        <tr>
                            <td style="padding-left: 220px">
                                <br /><br />
                                <p  class="title">
                                    <strong>
                                        <p style="font-size: 30px">Ricerca Locazione</p>
                                        <a href="ricercaLocazione.jsp" >
                                            <img  class="imgInizio" src="../css/images/iconaLente.png"   width="250" height="250"  alt="ricerca locazione">
                                        </a>
                            </td>
                            <td >
                                <br /><br />
                                <p class="title">
                                    <strong>
                                        <p style="font-size: 30px">Visualizza/Rimuovi Prenotazioni</p>
                                        <a href="visualizzaPrenotazioniPerViaggiatore.jsp" >
                                            <img class="imgInizio" src="../css/images/visualizzaPrenotazioni.jpg"   width="250" height="250"  alt="visualizza prenotazioni">
                                        </a>
                            </td>
                            <td style="clear: both"></td>
                        </tr>
                        <tr>
                        </tr>
                    </table>
                <div style="clear: both;">&nbsp;</div>
                <div class="post">
                    <p></p>
                </div>
            </div>
            <div style="clear: both;">&nbsp;</div>

        </div>

    </div>

    <div id="footer">
</div>

</div>
</body>
</html>
