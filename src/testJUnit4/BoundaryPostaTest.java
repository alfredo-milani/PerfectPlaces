package testJUnit4;

import boundary.BoundaryPosta;
import boundary.BoundaryRegistrazione;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;


/**
 * Created by alfredo
 */
public class BoundaryPostaTest {

    private static BoundaryPosta bP;
    private static BoundaryRegistrazione bR;

    @BeforeClass
    public static void setUp() throws Exception {
        bP = new BoundaryPosta();
        bR = new BoundaryRegistrazione();
    }

    @Test
    public void ritornaMessaggi() throws Exception {
        String assertMsg = "Verifica che il sistema ricerchi effettivamente il messaggio all'interno del DB";

        inviaMessaggio();

        Assert.assertNotNull(assertMsg, bP.ritornaMessaggi("martina"));
    }

    @Test
    public void inviaMessaggio() throws Exception {
        String assertMsg = "Verifica che il sistema invii correttamente un messaggio ad un altro utente";

        String username = "martina";
        String password = "4321";
        String nome = "Martina";
        String cognome = "Vincenzi";
        String email = "martina@gmail.com";
        Locale locale = Locale.CANADA;
        String nascita = null;
        String sesso = null;

        String username2 = "marco";
        String password2 = "4321";
        String nome2 = "Marco";
        String cognome2 = "Vincenzi";
        String email2 = "marcogmail.com";
        Locale locale2 = Locale.CANADA;
        String nascita2 = null;
        String sesso2 = null;

        String oggetto = "oggetto";
        String contenuto = "contenuto";
        String destinatario = "destinatario";

        bR.registrazione(username, password, password,
                nome, cognome, email, locale, nascita, sesso);
        bR.registrazione(username2, password2, password2,
                nome2, cognome2, email2, locale2, nascita2, sesso2);

        int[] expected = {0, 1, 2, 3, 4, 5, 6};
        int[] result = {
                bP.inviaMessaggio(oggetto, username,
                        username2, contenuto),
                bP.inviaMessaggio(oggetto, username,
                        null,  contenuto),
                bP.inviaMessaggio(null, username,
                        username2, contenuto),
                bP.inviaMessaggio(oggetto, username,
                        username2, null),
                bP.inviaMessaggio(oggetto, username,
                        destinatario, contenuto),
                bP.inviaMessaggio(oggetto, null,
                        username, contenuto),
                bP.inviaMessaggio(oggetto, username,
                        username, contenuto)
        };

        Assert.assertArrayEquals(assertMsg, expected, result);
    }

    @Test
    public void cancellaMessaggio() throws Exception {
        String assertMsg = "Verifica che il sistema cancelli in modo corretto un messaggio dal DB";

        // Invoco il metodo 'inviaMessaggio()' in modo
        // da essere sicuro dell'esistenza di almeno un
        // messaggio nel DB. Questo messaggio avrà come codice
        // di identificazione l'intero 1
        inviaMessaggio();

        Assert.assertEquals(assertMsg, 1, bP.cancellaMessaggio(1));
    }

}