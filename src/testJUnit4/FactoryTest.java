package testJUnit4;

import entity.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by maria
 */
@RunWith(Parameterized.class)
public class FactoryTest {
    private String expected;
    private String caseValue;

    @Parameterized.Parameters
    public static Collection <Object []> getFactoryTestParameters(){
        return Arrays.asList(new Object[][] {

                {Albergo.class, "0"},  // expected, caseValue -->creazione albergo
                {Appartamento.class, "1"} , // expected, caseValue -->creazione appartamento
                {Beb.class, "2"} , // expected, caseValue -->creazione b&b
                {CasaVacanza.class, "3"} , // expected, caseValue -->creazione casa vacanza
                {Ostello.class, "4"} , // expected, caseValue -->creazione ostello

        });

    }


}
