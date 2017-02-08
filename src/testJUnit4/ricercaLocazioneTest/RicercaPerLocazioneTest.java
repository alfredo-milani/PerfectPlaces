package testJUnit4.ricercaLocazioneTest;


import control.AdapterRicercaPerLocazione;
import control.ControlloreRicercaGlobale;
import control.ControlloreRicercaPerLocazione;
import entity.Albergo;
import entity.Appartamento;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class RicercaPerLocazioneTest {


    @Test
    public void testRicercaPerLocazioniAlb() throws IllegalAccessException, InterruptedException, SerializzazioneException, InstantiationException, IOException, DeserializzazioneException, ClassNotFoundException {
        ControlloreRicercaPerLocazione crl = new AdapterRicercaPerLocazione(new ControlloreRicercaGlobale());
        ArrayList<Locazione> locazioni = (ArrayList<Locazione>) crl.ricerca("Albergo","Frosinone","800",3);
        int numeroLocazioni = locazioni.size(), contatore=0;


        for(Locazione locazione : locazioni){
            if(locazione.getClass()== Albergo.class)
                ++contatore;

        }
        Assert.assertEquals(numeroLocazioni,contatore);

    }


    @Test
    public void testRicercaPerLocazioniAppartamento() throws IllegalAccessException, InterruptedException, SerializzazioneException, InstantiationException, IOException, DeserializzazioneException, ClassNotFoundException {
        ControlloreRicercaPerLocazione crl = new AdapterRicercaPerLocazione(new ControlloreRicercaGlobale());
        ArrayList<Locazione> locazioni = (ArrayList<Locazione>) crl.ricerca("Appartamento","Frosinone","800",3);
        int numeroLocazioni = locazioni.size(), contatore=0;


        for(Locazione locazione : locazioni){
            if(locazione.getClass()== Appartamento.class)
                ++contatore;

        }
        Assert.assertEquals(numeroLocazioni,contatore);

    }


    @Test
    public void testRicercaAvanzataAlbergo() throws IllegalAccessException, InterruptedException, SerializzazioneException, InstantiationException, IOException, DeserializzazioneException, ClassNotFoundException {
        ControlloreRicercaPerLocazione crl = new AdapterRicercaPerLocazione(new ControlloreRicercaGlobale());
        ArrayList<Albergo> albrghi = (ArrayList<Albergo>) crl.ricercaAvanzata("Albergo","Frosinone","800",3,"true","true","true","completa");

        int numeroLocazioni = albrghi.size(), contatore=0;


        for(Albergo albergo: albrghi){
            if(albergo.isParcheggio() &&
                    albergo.isPet()  &&
                    albergo.isWifi() &&
                    albergo.getTipoPensione().equals("completa"))
                ++contatore;

        }
        Assert.assertEquals(numeroLocazioni,contatore);

    }


    @Test
    public void testRicercaAvanzataAppartamento() throws IllegalAccessException, InterruptedException, SerializzazioneException, InstantiationException, IOException, DeserializzazioneException, ClassNotFoundException {
        ControlloreRicercaPerLocazione crl = new AdapterRicercaPerLocazione(new ControlloreRicercaGlobale());
        ArrayList<Appartamento> appartamenti = (ArrayList<Appartamento>) crl.ricercaAvanzata("Appartamento","Frosinone","800",3,"true","true","true","2");

        int numeroLocazioni = appartamenti.size(), contatore=0;


        for(Appartamento appartamento : appartamenti){
            if(appartamento.isParcheggio() &&
                    appartamento.isPet()  &&
                    appartamento.isWifi() &&
                    Integer.parseInt(appartamento.getNumeroStanze())>2)
                ++contatore;

        }
        Assert.assertEquals(numeroLocazioni,contatore);
    }


}
