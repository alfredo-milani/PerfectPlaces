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

/*  Script JavaScript per cambiare la lingua del sito
 *  0 -> IT, italiano
 *  1 -> EN, english
 */
function changeLang(box) {
    var URL = window.location.href;
    var URLArray = URL.split("_");
    var currentLang = Number(URLArray[1]);
    var lang = Number(box[box.selectedIndex].id);

    if (currentLang == lang)
        return;

    window.open(URLArray[0] + "_" + lang + "_" + URLArray[2], "_self");
}