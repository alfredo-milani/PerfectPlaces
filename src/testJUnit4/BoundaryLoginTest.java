package testJUnit4;

import boundary.BoundaryLogin;
import boundary.BoundaryRegistrazione;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;

/**
 * Created by alfredo on 20/12/16.
 */
public class BoundaryLoginTest {

    private static BoundaryRegistrazione bR;
    private static BoundaryLogin bL;

    @BeforeClass
    public static void setUp() {
        bR = new BoundaryRegistrazione();
        bL = new BoundaryLogin();
    }

    @Test
    public void loginFail() throws Exception {
        String assertMsg = "Verifica che il sistema non permetta l'accesso ad utenti non registrati";

        String username = "Marco";
        String password = "1234";

        bL.login(username, password);

        Assert.assertEquals(assertMsg,false, bL.controlloAccesso());
    }

    @Test
    public void loginSuccess() throws Exception {
        String assertMsg = "Verifica che il sistema permetta l'accesso ai soli utenti registrati";

        String username = "martina";
        String password = "4321";
        String nome = "Martina";
        String cognome = "Vincenzi";
        String email = "martina@gmail.com";
        Locale locale = Locale.CANADA;
        String nascita = null;
        String sesso = null;

        bR.registrazione(username, password, password,
                nome, cognome, email, locale, nascita, sesso);
        bL.login(username, password);

        Assert.assertEquals(assertMsg,true, bL.controlloAccesso());
    }

    @Test
    public void logout() throws Exception {
        String assertMsg = "Verifica che il sistema disconnetta in modo corretto l'utente";

        if (!bL.controlloAccesso())
            loginSuccess();

        bL.logout();

        Assert.assertEquals(assertMsg, false, bL.controlloAccesso());
    }
}