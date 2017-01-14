<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Perfect Places</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
    <link href="../css/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<jsp:useBean id="c" scope="session" class="control.ControlloreLogin"/>
<%@page import="java.util.ArrayList" %>
<%@ page import="exception.SerializzazioneException" %>
<%@ page import="exception.DeserializzazioneException" %>
<%@ page import="boundary.BoundaryVisualizzaPrezzo" %>
<%@ page import="entity.*" %>
<%

    BoundaryVisualizzaPrezzo bvp = new BoundaryVisualizzaPrezzo();



    String dataInizio = request.getParameter("dataInizio");
    String dataFine = request.getParameter("dataFine");
    String id = request.getParameter("id");
    String tipoPensione = request.getParameter("tipoPensione");
    String SerParcheggio = request.getParameter("SerParcheggio");
    String SerWifi= request.getParameter("SerWifi");
    String SerPet= request.getParameter("SerPet");

    int prezzoBase=0;
    int prezzoConServizi=0;


    id = id.substring(0,1);
    /*System.out.println("id  " + id);
    System.out.println("servizio parcheggio "+SerParcheggio);
    System.out.println("servizio wifi "+SerWifi);
    System.out.println("servizio pet "+SerPet);*/





    ArrayList<Locazione> elencoLocazioni= (ArrayList<Locazione>) request.getSession().getAttribute("loc");
    try {
       prezzoBase= bvp.ritornaPrezzoBase(elencoLocazioni.get(Integer.parseInt(id)),tipoPensione,dataInizio,dataFine);
    } catch (DeserializzazioneException | SerializzazioneException e) {
        e.printStackTrace();
    }

    try {
        prezzoConServizi=bvp.ritornaPrezzoConServizi(dataInizio,dataFine,SerParcheggio,SerWifi,SerPet);
    } catch (DeserializzazioneException | SerializzazioneException e) {
        e.printStackTrace();
    }

    System.out.println("prezzo base "+prezzoBase);
    System.out.println("prezzo con servizi "+prezzoConServizi);

    String nomeLocazione = elencoLocazioni.get(Integer.parseInt(id)).getNomeLocazione();



    int giornoInizio = Integer.parseInt(dataInizio.substring(0, 2));
    int meseInizio = Integer.parseInt(dataInizio.substring(3, 5));
    int annoInizio = Integer.parseInt(dataInizio.substring(6, 10));
    String dal = giornoInizio + "/" + meseInizio +"/"+ annoInizio;
    int giornoFine = Integer.parseInt(dataFine.substring(0, 2));
    int meseFine = Integer.parseInt(dataFine.substring(3, 5));
    int annoFine = Integer.parseInt(dataFine.substring(6, 10));
    String al = giornoFine + "/" + meseFine +"/"+ annoFine;

%>

<body>

<div id="menu-wrapper">
    <div id="menu">

    </div>
    <!-- end #menu -->
</div>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header">
            <div id="logo">
                <h1><a href="#">Rimozione Locazioni</a></h1>

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

            <div id="page-bgbtm">
                <div id="content">


                    <div class="post">
                        <h2><strong>Riepilogo dei servizi richiesti</strong></h2>
                    </div>
                    <div class="post">
                        <%
                            if(elencoLocazioni.get(Integer.parseInt(id)).getClass()==Albergo.class){
                        %>
                        <img src="../css/images/albergo.png"  style="width:50px;height:50px; float: left">
                        <span class="span">Nome albergo: <%out.println(nomeLocazione);%></span>
                        <%}%>
                        <%
                            if(elencoLocazioni.get(Integer.parseInt(id)).getClass()== Appartamento.class){
                        %>
                        <img src="../css/images/appartamento.png"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome appartamento: <%out.println(nomeLocazione);%></span>
                        <%}%>
                        <%
                            if(elencoLocazioni.get(Integer.parseInt(id)).getClass()== Beb.class){
                        %>
                        <img src="../css/images/beb.jpeg"  style="width:50px;height:50px;float: left;">
                        <span class="span">Nome b&b: <%out.println(nomeLocazione);%></span>

                        <%}%>
                        <%
                            if(elencoLocazioni.get(Integer.parseInt(id)).getClass()== CasaVacanza.class){
                        %>
                        <img src="../css/images/casaVacanza.png"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome casa vacanza: <%out.println(nomeLocazione);%></span>

                        <%}%>
                        <%
                            if(elencoLocazioni.get(Integer.parseInt(id)).getClass()== Ostello.class){
                        %>
                        <img src="../css/images/ostello.jpeg"  style="width:50px;height:50px;float: left">
                        <span class="span">Nome ostello: <%out.println(nomeLocazione);%></span>
                        <%}%>
                    <br><br><br>
                        <table style="width: 100%;">
                            <tr>
                                <%

                                    if(elencoLocazioni.get(Integer.parseInt(id)).getClass()==Albergo.class||
                                            elencoLocazioni.get(Integer.parseInt(id)).getClass()==Ostello.class){%>
                                            <td> <b>Pensione richiesta: </b>
                                            <%if(tipoPensione.equals("completa")) {%>
                                                     pensione completa

                                             <%}%>
                                             <%if(tipoPensione.equals("mezza pensione")) { %>
                                                     mezza pensione
                                             <%}%>

                                    <%}%>
                                            </td>
                            </tr>
                            <tr>
                                <td><b>Servizi richiesti: </b>
                                <%
                                    if(SerParcheggio.equals("true")){
                                %>
                                    - Parcheggio
                                <%}%>
                                    -
                                <%
                                    if(SerWifi.equals("true")){
                                %>
                                    - Wifi
                                <%}%>
                                    -
                                <%
                                    if(SerPet.equals("true")){
                                %>
                                 - Animali ammessi
                                <%}%>
                                    -

                                </td>
                            </tr>
                            <tr>
                                <td><b>Prezzo senza aggiunta servizi: </b><%out.println(prezzoBase);%></td>
                                <td><b>Prezzo con aggiunta servizi: </b><%out.println(prezzoConServizi);%></td>
                            </tr>
                            <tr>
                                <td><b>Giorni richiesti: </b> <b>dal</b>  <%out.println(dal);%> <b>al</b>  <%out.println(al);%></td>
                            </tr>
                            <tr>

                                <td>
                                    <form method="get" action="prenotaLocazione.jsp" enctype="text/plain">
                                            <input type="hidden" name="id" value="<%out.println(id);%>">
                                            <input type="hidden" name="dataInizio" value="<%out.println(dataInizio);%>">
                                            <input type="hidden" name="dataFine" value="<%out.println(dataFine);%>">
                                        <%if(elencoLocazioni.get(Integer.parseInt(id)).getClass()==Albergo.class || elencoLocazioni.get(Integer.parseInt(id)).getClass()==Beb.class ||elencoLocazioni.get(Integer.parseInt(id)).getClass()==Ostello.class ){
                                        %>
                                        <div style="float:left; width: 70px"><p style="font-size: 15px"><b>Prenota per: </b></p> </div>

                                        <div style="float: left;width: 200px">
                                            <select style="width: 200px" name="prenota per">
                                                <option value="1">1 persona</option>
                                                <option value="2">2 persone</option>
                                                <option value="3">3 persone</option>
                                                <option value="4">4 persone</option>
                                                <option value="5">5 persone</option>
                                            </select>

                                        </div>
                                </td>
                            </tr>
                            <tr>
                                 <td>


                                        <br>
                                        <%}else {%>
                                        <div style="float: left;width: 200px">
                                                <input type="hidden" name="prenota per" value="1">
                                            </div>
                                        <%}%>
                                        <div style="float: left">
                                            <input type="submit" style="width: 300px; margin-left: 460px" class="btnBlue" value="CONFERMA PRENOTAZIONE">
                                        </div>
                                        <div style="clear: both"></div>
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                   <h3> <a style="color: #4b7091" href="ricercaLocazione.jsp">oppure torna alla ricerca</a></h3>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div style="clear: both;">&nbsp;</div>
                </div>
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
