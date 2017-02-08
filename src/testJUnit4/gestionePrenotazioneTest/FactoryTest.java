package testJUnit4.gestionePrenotazioneTest;

import control.factoryPrenotazione.*;
import org.junit.Assert;
import org.junit.Test;

public class FactoryTest {



    @Test
    public void testUnoFactoryPiùPosti(){
        FactoryPrenotazione fp = FactoryPrenotazionePiùPosti.getFactoryPrenotazionePiùPosti();
        boolean controllo;

        ControlloreDisponibilità cp = fp.creaControlloreDisponibilità();
        if(cp.getClass() == ControlloreDisponibilitàPiùPosti.class)
            controllo = true;
        else controllo = false;

        Assert.assertTrue(controllo);

    }

    @Test
    public void testDueFactoryPiùPosti(){
        FactoryPrenotazione fp = FactoryPrenotazionePiùPosti.getFactoryPrenotazionePiùPosti();
        boolean controllo;

        ControlloreRegistraPrenotazione crp = fp.creaControlloreRegistraPrenotati();
        if(crp.getClass() == ControlloreRegistraPrenotazioniPiùPosti.class)
            controllo = true;
        else controllo = false;

        Assert.assertTrue(controllo);

    }

    @Test
    public void testUnoFactorySingoloPosto(){
        FactoryPrenotazione fp = FactoryPrenotazioneSingoloPosto.getFactoryPrenotazioneSingoloPosto();
        boolean controllo;

        ControlloreDisponibilità cp = fp.creaControlloreDisponibilità();
        if(cp.getClass() == ControlloreDisponibilitàSingoloPosto.class)
            controllo = true;
        else controllo = false;

        Assert.assertTrue(controllo);

    }

    @Test
    public void testDueFactorySingoloPosto(){
        FactoryPrenotazione fp  = FactoryPrenotazioneSingoloPosto.getFactoryPrenotazioneSingoloPosto();
        boolean controllo;

        ControlloreRegistraPrenotazione crp = fp.creaControlloreRegistraPrenotati();
        if(crp.getClass() == ControlloreRegistraPrenotazioniSingoloPosto.class)
            controllo = true;
        else controllo = false;

        Assert.assertTrue(controllo);

    }
}
