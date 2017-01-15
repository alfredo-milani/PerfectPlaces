package constants;

/**
 * Created by alfredo on 17/09/16.
 */
public interface Constants {
    //***** Percorsi utili *****//
    String ABS_PATH = "/media/Data/Projects_JetBrains/IdeaProjects/PerfectPlaces/"; /* NON MODIFICARE */
    String ALBERGHI_PATH =    ABS_PATH.concat("data/location/alberghi");
    String ADMIN_PATH =       ABS_PATH.concat("data/amministratori");
    String APPART_PATH =      ABS_PATH.concat("data/location/appartamenti");
    String BEB =              ABS_PATH.concat("data/location/beb");
    String CASEVACANZA_PATH = ABS_PATH.concat("data/location/casevacanza");
    String OSTELLI_PATH =     ABS_PATH.concat("data/location/ostelli");
    String TMPDATE_PATH =     ABS_PATH.concat("data/tempdate");
    String UTENTI_PATH =      ABS_PATH.concat("data/utenti");
    String HOST_PORT =        "localhost:1122/";
    String IMGS_PATH_REL_S =  "../css/images/";
    String PRENOTAZIONE_ALBERGO_PATH =  ABS_PATH.concat("data/prenotazioni/prenotazioniAlberghi");
    String PRENOTAZIONE_BEB_PATH = ABS_PATH.concat("data/prenotazioni/prenotazioniBeb");
    String PRENOTAZIONE_OSTELLO_PATH = ABS_PATH.concat("data/prenotazioni/prenotazioniOstelli");
    String PRENOTAZIONE_APPARTAMENTO_PATH = ABS_PATH.concat("data/prenotazioni/prenotazioniAppartamenti");
    String PRENOTAZIONE_CASAVACANZA_PATH = ABS_PATH.concat("data/prenotazioni/prenotazioniCaseVacanza");
    String PRENOTATI_ALBERGO_PATH = ABS_PATH.concat("data/prenotazioni/clientiPrenotati/prenotatiAlbergo");
    String PRENOTATI_APPARTAMENTO_PATH = ABS_PATH.concat("data/prenotazioni/clientiPrenotati/prenotatiAppartamento");
    String PRENOTATI_BEB_PATH = ABS_PATH.concat("data/prenotazioni/clientiPrenotati/prenotatiBeb");
    String PRENOTATI_CASEVACANZA_PATH = ABS_PATH.concat("data/prenotazioni/clientiPrenotati/prenotatiCaseVacanza");
    String PRENOTATI_OSTELLO_PATH = ABS_PATH.concat("data/prenotazioni/clientiPrenotati/prenotatiOstello");
    String PROVINCE_PATH = ABS_PATH.concat("data/location/fileProvince");
    String FAQ_PATH = ABS_PATH.concat("data/fileFaq");
    String PACKAGE_LANGUAGE = "language.Lang";


    //***** Gestione DataBase *****//
    // Informazioni necessarie per il DataBase
    String DB_DRIVER = "org.postgresql.Driver";
    String DB_URL = "jdbc:postgresql://localhost:5432/PerfectPlacesDB";
    String DB_USER = "postgres";
    String DB_PASSWORD = "3268alfred";

    // Table "utenti" - Entità UTENTE
    String DB_TABLE_UTENTI = "utenti";
    String DB_UTENTI_US = "username";
    String DB_UTENTI_PSW = "password";
    String DB_UTENTI_NOME = "nome";
    String DB_UTENTI_COGNOME = "cognome";
    String DB_UTENTI_EMAIL = "email";
    String DB_UTENTI_IMM = "immagine";
    String DB_UTENTI_LINGUA = "lingua";
    String DB_UTENTI_NASCITA = "nascita";
    String DB_UTENTI_SESSO = "sesso";

    // Table "messaggi" - Entità MESSAGGIO
    String DB_TABLE_MESSAGGI = "messaggi";
    String DB_MESSAGGI_COD = "codice";
    String DB_MESSAGGI_OBJ = "oggetto";
    String DB_MESSAGGI_MIT = "mittente";
    String DB_MESSAGGI_DEST = "destinatario";
    String DB_MESSAGGI_CONT = "contenuto";
    String DB_MESSAGGI_DATA = "data";

    // Query generiche
    String DB_QUERY_SELECT = "SELECT %s FROM %s WHERE %s";
    String DB_QUERY_INSERT = "INSERT INTO %s VALUES %s";
    String DB_QUERY_UPDATE = "UPDATE %s SET %s WHERE %s";
    String DB_QUERY_DELETE = "DELETE FROM %s WHERE %s";



    //***** Lingue *****//
    // Le stringhe che codificano la lingua devono corrispondere
    // a dei valori contenuti nel file Locale.java
    // (per la costruzione di new Locate)
    // e.g. italiano --> it
    //      inglese  --> en
    String IT = "it";
    String EN = "en";
    // Array contenente tutte le lingue disponibili del sistema
    String LANGS[] = {IT, EN};

    String LANG_DEFAULT = IT;



    //***** Stand alone *****//
    String USER_KEY = "usernameAdmin";


    // Una volta inserito il DB in modo completo ricorda di
    // toglie try/catch nei jsp, i throws
    // nelle segnature e gli implements Serializable.
    // Elimina anche variabili locali di appoggio nelle classi java per
    // riferirsi alle costanti contenute in questa pagina.
    // (NOTA::: UC lingua, posta, profilo -> tolta retrocompatibilita
    // con Serializzazione/Deserializzazione su file)

    //***** Vedi se elimina, le classi Utente e Messaggio perchè gestite dal DB  *****//

    /////////************************************************************////////
    //////******     PER NON USARE IL DATABASE METTI IL VALORE A 0    *****//////
    /////////************************************************************////////
    int DB = 1;
}
