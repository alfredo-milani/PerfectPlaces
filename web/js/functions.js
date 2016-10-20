/**
 * Created by alfredo on 16/10/16.
 */

// Script JavaScript per far vedere solo i parametri di ricerca del tipo di locazione cliccato
function visualizza(id, id1, id2, id3, id4) {
    document.getElementById(id1).style.display = 'none';
    document.getElementById(id2).style.display = 'none';
    document.getElementById(id3).style.display = 'none';
    document.getElementById(id4).style.display = 'none';

    if (document.getElementById) {
        if (document.getElementById(id).style.display == 'none')
            document.getElementById(id).style.display = 'block';
        else
            document.getElementById(id).style.display = 'none';
    }
}

/*  Script JavaScript per cambiare la lingua della pagina corrente
 *  it -> IT, italiano
 *  en -> EN, english
 */
function changeLang(box, us, psw, psw2, nome, cognome, email) {
    var URL = window.location.href;
    var URLArray = URL.split("_");
    var currentLang = String(document.documentElement.lang);
    var lang = String(box[box.selectedIndex].id);

    if (currentLang == lang)
        return;

    // Per mantenere l'utente loggato durante il cambio di lingua
    var params = ["username", "password"];
    var paramsToSend = "";
    for (var i = 1; i < arguments.length; ++i) {
        if (arguments[i] != null) {
            paramsToSend += params[i - 1] + "=" + arguments[i];
            if (arguments[i + 1] != null)
                paramsToSend += "&";
        }
    }

    var finalURL = URLArray[0] + "_" + lang + "_" + URLArray[2];
    var http = new XMLHttpRequest();
    http.open("POST", finalURL, true);

    // Invia informazioni di header con la richiesta
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // Per sapere quando c'è un cambiamento di stato
    http.onreadystatechange = function() {
        if(http.readyState == 4 && http.status == 200) {
            // Per sostituire URL
            document.open();
            document.write(http.responseText);
            window.history.pushState({"html":http.html, "pageTitle":http.pageTitle}, "URL", finalURL);
            document.close();
        }
    };

    http.send(paramsToSend);
}