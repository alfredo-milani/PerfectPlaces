package testJUnit4;

import control.ControlloreLogin;
import control.ControlloreRegistrazione;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Created by alfredo on 20/12/16.
 */
public class ControlloreLoginTest {

    @Test
    public void loginFail() throws Exception {
        String username = "Marco";
        String password = "1234";

        ControlloreLogin cL = new ControlloreLogin();
        cL.login(username, password);

        Assert.assertEquals(false, cL.getLogged());
    }

    @Test
    public void loginSuccess() throws Exception {
        String username = "martina";
        String password = "4321";
        String nome = "Martina";
        String cognome = "Vincenzi";
        String email = "martina@gmail.com";
        Locale locale = Locale.CANADA;
        String nascita = null;
        String sesso = null;

        ControlloreLogin cL = new ControlloreLogin();
        ControlloreRegistrazione cR = new ControlloreRegistrazione();
        cR.registrazione(username, password, password,
                nome, cognome, email, locale, nascita, sesso);

        cL.login(username, password);
        Assert.assertEquals(true, cL.getLogged());
    }

    @Test
    public void logout() throws Exception {

    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void getPsw() throws Exception {

    }

    @Test
    public void getLogged() throws Exception {
    }
}