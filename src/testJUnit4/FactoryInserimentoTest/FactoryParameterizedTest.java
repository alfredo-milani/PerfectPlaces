package testJUnit4.FactoryInserimentoTest;

import control.FactoryInserimentoLocazione;
import entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


/**
 * Created by maria
 */
@RunWith(Parameterized.class)
public class FactoryParameterizedTest {
    private Class expected;
    private String caseValue;

    @Before
    public void chiamaInizializzaFactory(){
        FactoryInserimentoLocazione factory = FactoryInserimentoLocazione.getFactoryInstance();
        factory.changeSettings("locazione","1","Roma", "Viale Cambridge","maria","20", "centrale",true,true,true);
    }


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

    public FactoryParameterizedTest(Class expected, String caseValue){
        this.expected=expected;
        this.caseValue=caseValue;
    }

    @Test
    public void inserimento() throws Exception {
        FactoryInserimentoLocazione factory = FactoryInserimentoLocazione.getFactoryInstance();
        Locazione locazione = factory.createGenericLocation(caseValue);

        Assert.assertTrue(locazione.getClass()==expected);
    }


}
