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
 *  0 -> IT, italiano
 *  1 -> EN, english
 */
function changeLang(box, us, psw) {
    var URL = window.location.href;
    var URLArray = URL.split("_");
    var currentLang = Number(URLArray[1]);
    var lang = Number(box[box.selectedIndex].id);

    if (currentLang == lang)
        return;

    // Per mantenere l'utente loggato durante il cambio di lingua
    if (us == null && psw == null) {
        window.open(URLArray[0] + "_" + lang + "_" + URLArray[2], "_self");
    } else {
        var http = new XMLHttpRequest();
        var params = "username=" + us + "&password=" + psw;
        http.open("POST", URLArray[0] + "_" + lang + "_" + URLArray[2], true);

        // Invia informazioni di header con la richiesta
        http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        // Per sapere quando c'è un cambiamento di stato
        http.onreadystatechange = function() {
            if(http.readyState == 4 && http.status == 200) {
                document.open();
                document.write(http.responseText);
                document.close();
            }
        };

        http.send(params);
    }
}