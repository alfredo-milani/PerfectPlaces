package testJUnit4;

import boundary.BoundaryLingua;
import boundary.BoundaryRegistrazione;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

/**
 * Created by alfredo on 20/12/16.
 */
public class BoundaryLinguaTest {

    private static BoundaryRegistrazione bR;
    private static BoundaryLingua bL;

    private static String username = "martina";
    private static String password = "4321";
    private static String nome = "Martina";
    private static String cognome = "Vincenzi";
    private static String email = "martina@gmail.com";
    private static Locale locale = Locale.CANADA;
    private static String nascita = null;
    private static String sesso = null;

    @BeforeClass
    public static void setUp() throws Exception {
        bR = new BoundaryRegistrazione();
        bL = new BoundaryLingua();

        bR.registrazione(username, password, password,
                nome, cognome, email, locale, nascita, sesso);
    }

    @Test
    public void riceviLingua() throws Exception {
        String assertMsg = "Verifica che il sistema ritorni correttamente la preferenza sulla lingua dall'oggetto 'Utente'";

        Assert.assertNotNull(assertMsg,
                bL.riceviLingua(username));
    }

    @Test
    public void aggiornaPreferenze() throws Exception {
        String assertMsg = "Verifica che il sistema aggiorni correttamente la lingua preferita dall'utente";

        Assert.assertEquals(assertMsg,
                bL.riceviLocaleDaString("fr"),
                bL.aggiornaPreferenze(username, "fr"));
    }

}