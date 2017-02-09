package testJUnit4.gestioneRecensioniTest;


import control.ControlloreVisualizzaRecensioni;
import entity.Recensione;
import exception.DeserializzazioneException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class VisualizzazzioneTest {

    /*
     test effettuati sapendo che in questo momento nel sistema
     -è presente una sola recensione per l'Albergo Sole
     */


    @Test
    public void testVisualizza() throws DeserializzazioneException {
        ControlloreVisualizzaRecensioni cvr = new ControlloreVisualizzaRecensioni();
        ArrayList<Recensione> recensioni = cvr.ritornaRecensioni("Albergo Sole");

        Assert.assertNotNull(recensioni);

    }


    @Test
    public void testNomeLocazione() throws DeserializzazioneException {
        ControlloreVisualizzaRecensioni cvr = new ControlloreVisualizzaRecensioni();
        ArrayList<Recensione> recensioni = cvr.ritornaRecensioni("Albergo Sole");

        int numeroRecensioni = recensioni.size();
        boolean controllo = numeroRecensioni==1;

        Assert.assertTrue(controllo);

    }


    @Test
    public void testBello() throws DeserializzazioneException {
        ControlloreVisualizzaRecensioni cvr = new ControlloreVisualizzaRecensioni();
        ArrayList<Recensione> recensioni = cvr.ritornaRecensioni("Albergo Bello");
        System.out.println(recensioni.get(1).getTestoRecensione());

        int numeroRecensioni = recensioni.size();
        boolean controllo = numeroRecensioni==3;

        Assert.assertTrue(controllo);

    }


}
