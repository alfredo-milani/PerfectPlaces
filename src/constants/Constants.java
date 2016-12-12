package constants;

/**
 * Created by alfredo on 17/09/16.
 */
public interface Constants {
    String ABS_PATH = "C:\\Users\\Alfredo Milani\\IdeaProjects\\PerfectPlaces\\"; /* NON MODIFICARE */
    String ALBERGHI_PATH =    ABS_PATH.concat("data\\location\\alberghi");
    String ADMIN_PATH =       ABS_PATH.concat("data\\amministratori");
    String APPART_PATH =      ABS_PATH.concat("data\\location\\appartamenti");
    String BEB =              ABS_PATH.concat("data\\location\\beb");
    String CASEVACANZA_PATH = ABS_PATH.concat("data\\location\\casevacanza");
    String MSG_PATH =         ABS_PATH.concat("data\\messaggi");
    String OSTELLI_PATH =     ABS_PATH.concat("data\\location\\ostelli");
    String TMPDATE_PATH =     ABS_PATH.concat("data\\tempdate");
    String UTENTI_PATH =      ABS_PATH.concat("data\\utenti");
    String HOST_PORT =        "localhost:1122/";
    String IMGS_PATH_REL_S =  "..\\css\\images\\";
    String PRENOTAZIONE_ALBERGO_PATH =  ABS_PATH.concat("data\\prenotazioni\\prenotazioniAlberghi");
    String PRENOTAZIONE_BEB_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniBeb");
    String PRENOTAZIONE_OSTELLO_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniOstelli");
    String PRENOTAZIONE_APPARTAMENTO_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniAppartamenti");
    String PRENOTAZIONE_CASAVACANZA_PATH = ABS_PATH.concat("data\\prenotazioni\\prenotazioniCaseVacanza");
    String PROVINCE_PATH = ABS_PATH.concat("data\\fileProvinceinASCII");
    String PACKAGE_LANGUAGE = "language.Lang";

    // Lingue
    // Le stringhe che codificano la lingua devono corrispondere
    // a dei valori contenuti nel file Locale.java
    // (per la costruzione di new Locate)
    // e.g. italiano --> it
    //      inglese  --> en
    String LANG_DEFAULT = "it";
    String IT = "it";
    String EN = "en";
}
