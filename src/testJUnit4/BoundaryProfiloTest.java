package testJUnit4;

import boundary.BoundaryLingua;
import boundary.BoundaryLogin;
import boundary.BoundaryProfilo;
import boundary.BoundaryRegistrazione;
import entity.Utente;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;


/**
 * Created by alfredo
 */
public class BoundaryProfiloTest {

    private static BoundaryProfilo bP;
    private static BoundaryRegistrazione bR;
    private static BoundaryLogin bL;
    private static BoundaryLingua bLang;

    private static String username = "martina";
    private static String password = "4321";
    private static String nuovaPassword = "9999";
    private static String passwordConfermaErrata = "11111";
    private static String nome = "Martina";
    private static String cognome = "Vincenzi";
    private static String email = "martina@gmail.com";
    private static Locale locale = Locale.CANADA;
    private static String nascita = null;
    private static String nascitaErrata = "16/123/1994";
    private static String sesso = null;
    private static String sessoErrato = "m";
    private static String immagine = "profiloDefault.png";

    @BeforeClass
    public static void setUp() throws Exception {
        bP = new BoundaryProfilo();
        bR = new BoundaryRegistrazione();
        bL = new BoundaryLogin();
        bLang = new BoundaryLingua();

        bR.registrazione(username, password, password,
                nome, cognome, email, locale, nascita, sesso);
        bL.login(username, password);
    }

    @Test
    public void ritornaUtente() throws Exception {
        Locale lang = bLang.riceviLinguaDefault();
        String assertMsg = "Verifica che il sistema ritorni effettivamente un oggetto di tipo 'Utente'";

        Utente nullo = new Utente("errore", "errore", "errore",
                "errore", "errore", "errore", lang,
                "errore", "errore");
        Utente user = new Utente(username, password, nome, cognome,
                email, immagine, lang, nascita, sesso);

        Assert.assertEquals(assertMsg, nullo.getUsername(), bP.ritornaUtente(null).getUsername());
        Assert.assertEquals(assertMsg, user.getUsername(), bP.ritornaUtente(username).getUsername());
    }

    @Test
    public void modificaUtente() throws Exception {
        String assertMsg = "Verifica che il sistema aggiorni in modo corretto le credenziali dell'utente";

        int[] expected = {0, 1, 2, 3, 4, 5, 6};
        int[] resutl = {
                bP.modificaUtente(username, nome, cognome, email, null, null, "",
                    "", ""),
                bP.modificaUtente(username, nome, cognome, email,
                    null, null, nuovaPassword, "", ""),
                bP.modificaUtente(username, nome, cognome,email,
                    null, null, password, nuovaPassword,
                    passwordConfermaErrata),
                bP.modificaUtente(username, nome, cognome, email,
                    null, null, password,
                    "", ""),
                bP.modificaUtente(null, null,
                    null, null, null, null,
                    null, null, null),
                bP.modificaUtente(username, nome, cognome, email,
                    sessoErrato, nascita, "",
                    "", ""),
                bP.modificaUtente(username, nome, cognome, email,
                    sesso, nascitaErrata, "",
                    "", "")
        };

        Assert.assertArrayEquals(assertMsg, expected, resutl);
    }

}