package boundary;


import control.ControlloreInserimentoRecensione;
import control.ControlloreVisualizzaRecensioni;
import entity.Recensione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.util.ArrayList;

public class BoundaryGestioneRecesioni {

    private ControlloreInserimentoRecensione cir;
    private ControlloreVisualizzaRecensioni cvr;


    public int inserisciRecensione(String nomeLocazione,String tipoLocazione, String nomeRecensore, String Stelle, String testoRecensione) throws SerializzazioneException, DeserializzazioneException {
        int numeroStelle = Integer.parseInt(Stelle);
        cir = new ControlloreInserimentoRecensione();

        return cir.inserisci(nomeLocazione,tipoLocazione,nomeRecensore,numeroStelle,testoRecensione);
    }

    public ArrayList<Recensione> visualizzaRecensioni(String nomeLocazione) throws DeserializzazioneException {
        cvr= new ControlloreVisualizzaRecensioni();

        return cvr.ritornaRecensioni(nomeLocazione);
    }

    public int media(String nomeLocazione) throws DeserializzazioneException {
        cvr= new ControlloreVisualizzaRecensioni();
        return cvr.calcolaMedia(nomeLocazione);
    }



}
