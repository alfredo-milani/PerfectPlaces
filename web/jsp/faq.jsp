<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 13/11/16
  Time: 20.21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Frequently asked questions</title>
</head>
<style>@import url('../css/style.css');
.bf{
    font-family: 'Lato', sans-serif;
    font-weight:300;
    margin: 0;
    padding: 0;
    background: #fff none;
    font-size: 13px;
    color: #737373;
}
</style>

<link href='http://fonts.googleapis.com/css?family=Lato:400,100,300,700' rel='stylesheet' type='text/css'>

<body class="bf" >
<div class="faq" id="header" >
    <h1>FAQ</h1>
    <p><a href="index.jsp">Torna alla home</a></p>

</div>


<div class="content">
    <div class="box">


        <h2><a href="#risposta1">Domanda 1: Cos'è Perfect Places?</a></h2>
        <h2><a href="#risposta2">Domanda 2: Perfect Places è gratuito o ci sono dei costi aggiuntivi per alcune delle funzioni?</a></h2>
        <h2><a href="#risposta3">Domanda 3: Come faccio ad aggiungere la mia locazione su Perfect Places?</a></h2>
        <h2><a href="#risposta4">Domanda 4: Posso gestire più locazioni da un unico account?</a></h2>
        <h2><a href="#risposta5">Domanda 5: Come posso comunicare con il proprietario della locazione che ho prenotato?</a></h2>
        <h2><a href="#risposta6">Domanda 6: Come posso ricercare le locazioni disponibili del tipo desiderato?</a></h2>

    </div>

    <div class="box">

        <h2 id="risposta1">Risposta 1</h2>
        <p>Perfect Places è una web application che offre a locatori e viaggiatori un portale in cui  inserire locazioni di vario genere per i primi e prenotarle per i secondi.</p>

        <h2 id="risposta2">Risposta 2</h2>
        <p>Perfect Places è completamente gratuito e lo sarà sempre.</p>

        <h2 id="risposta3">Risposta 3</h2>
        <p>Dopo aver effettuato il login, ti basterà recarti nell'area Proprietario, quindi cliccare su "Inserici Locazione" e compilare il modulo relativo al tipo di locazione che si desidera inserire;il sistema registrerà dunque la nuova locazione.</p>

        <h2 id="risposta4">Risposta 4</h2>
        <p>Sì, è possibile gestire più strutture insieme con un solo account. Se sei già registrato, ti basterà accedere alla tua area Proprietario (per inserire una nuova locazione, vedi Risposta 3). Se è la prima volta che visiti il nostro sito, registrati per creare un nuovo account gratuitamente.</p>

        <h2 id="risposta5">Risposta 5</h2>
        <p>Dopo aver effettuato il login, recati nella sezione Posta: a questo punto il nostro portale ti offre sia la possibilità di inviare un messaggio tramite il servizio di mail interno al sito stesso o sfruttare un client esterno.</p>
        <h2 id="risposta6">Risposta 6</h2>
        <p>Dopo aver effettuato il login, ti basterà recarti nell'area Viaggiatore, quindi cliccare sull'immagine della locazione desiderata e compilare il modulo relativo per le preferenze; il sitema mostrerà le locazioni che rispondono ai criteri di ricerca inseriti.</p>

    </div>

    <div class="btn_3" id="button">
        <a href="#header">Torna al Top</a>
    </div>

</div><!--content-->
</body >
</html>
