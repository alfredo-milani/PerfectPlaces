package testJUnit4.gestioneRecensioniTest;


import constants.Constants;
import control.ControlloreRecensione;
import entity.Recensione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.util.ArrayList;

public class VisualizzazzioneTest {

    /*
     test effettuati sapendo che in questo momento nel sistema
     -è presente una sola recensione per l'Albergo Sole
     */

    private ArrayList<Recensione> recSole = new ArrayList<>();

    @Before
    public void precondizioni(){
        ArrayList<Recensione> recensioni = new ArrayList<>();
        try {
            recensioni= (ArrayList<Recensione>) DeserializzaOggetti.deserializza(Constants.RECENSIONI_PATH);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }
        int contatore=0;
        for(int i=0; i<recensioni.size();++i){
            if(recensioni.get(i).getNomeLocazione().equalsIgnoreCase("Albergo Sole"))
                ++contatore;
        }
        if(contatore!=0) {
            for(int i=0; i<recensioni.size();++i){
                if (recensioni.get(i).getNomeLocazione().equalsIgnoreCase("Albergo Sole")){
                    recSole.add(recensioni.get(i));
                    recensioni.remove(i);
                    --contatore;
                }
                if (contatore==1)
                    break;
            }
        }
        if(contatore==0){
            Recensione recensione = new Recensione("Albergo Sole", "aldo",4,"Albergo eccellente dal locale al personale");
            recensioni.add(recensione);


        }
        try {
            SerializzaOggetti.serializza(recensioni,Constants.RECENSIONI_PATH);
        } catch (SerializzazioneException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testVisualizza() throws DeserializzazioneException {
        ControlloreRecensione cvr = new ControlloreRecensione();
        ArrayList<Recensione> recensioni = cvr.ritornaRecensioni("Albergo Sole");

        Assert.assertNotNull(recensioni);

    }


    @Test
    public void testNomeLocazione() throws DeserializzazioneException {
        ControlloreRecensione cvr = new ControlloreRecensione();
        ArrayList<Recensione> recensioni = cvr.ritornaRecensioni("Albergo Sole");

        int numeroRecensioni = recensioni.size();
        boolean controllo = numeroRecensioni==1;

        Assert.assertTrue(controllo);

    }

    @After
    public void postcondizioni(){
        ArrayList<Recensione> recensioni = new ArrayList<>();
        try {
            recensioni= (ArrayList<Recensione>) DeserializzaOggetti.deserializza(Constants.RECENSIONI_PATH);
        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }
        for(int i=0; i<recensioni.size();++i){
            if (recensioni.get(i).getNomeLocazione().equalsIgnoreCase("Albergo Sole")) {
                recensioni.remove(i);
                break;
            }
        }

        recensioni.addAll(recSole);
        try {
            SerializzaOggetti.serializza(recensioni,Constants.RECENSIONI_PATH);
        } catch (SerializzazioneException e) {
            e.printStackTrace();
        }
    }




}
