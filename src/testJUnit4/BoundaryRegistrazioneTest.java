package testJUnit4;

import boundary.BoundaryRegistrazione;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Locale;
import java.util.Random;

/**
 * Created by alfredo on 20/12/16.
 */
public class BoundaryRegistrazioneTest {

    private static BoundaryRegistrazione bR;

    @BeforeClass
    public static void setUp() throws Exception {
        bR = new BoundaryRegistrazione();
    }

    @Test
    public void registrazione() throws Exception {
        String assertMsg = "Verifica correttezza comportamento controllore registrazione";

        String username = generateRandomUsername("gino");
        String password = "1234";
        String password2 = "12345";
        String nome = "Gino";
        String cognome = "Lepre";
        String email = "gino@gmail.com";
        Locale locale = Locale.CANADA;
        String nascita = null;
        String sesso = null;

        int[] expected = {0, 1, 2, 3};
        int[] result = {
                bR.registrazione(username, password,
                        password, nome, cognome, email, locale,
                        nascita, sesso),
                bR.registrazione(generateRandomUsername("gino"),
                        password, password, nome, null,
                        email, locale, nascita, sesso),
                bR.registrazione(generateRandomUsername("gino"),
                        password, password2, nome, cognome,
                        email, locale, nascita, sesso),
                bR.registrazione(username, password, password2,
                        nome, cognome, email, locale,
                        nascita, sesso)
        };

        Assert.assertArrayEquals(assertMsg, expected, result);
    }

    private String generateRandomUsername(String s) {
        Random random = new Random();

        return s + String.valueOf(random.nextInt());
    }

}