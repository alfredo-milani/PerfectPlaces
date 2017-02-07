package control;


import constants.Constants;
import entity.Recensione;
import exception.DeserializzazioneException;
import utils.DeserializzaOggetti;

import java.io.File;
import java.util.ArrayList;

public class ControlloreVisualizzaRecensioni {


    private String percorsoRecensioni = Constants.RECENSIONI_PATH;
    /*
    metodo utilizzato per restuire le recensioni in base al nome della locazione
     */

    public ArrayList<Recensione> ritornaRecensioni(String nomeLocazione) throws DeserializzazioneException {


        File file = new File(percorsoRecensioni);

        if(file.length()!=0){

            ArrayList<Recensione> recensioniPerLaLocazione= new ArrayList<>();

            ArrayList<Recensione> recensioniSalvate = (ArrayList<Recensione>) DeserializzaOggetti.deserializza(percorsoRecensioni);
            for(Recensione rec:  recensioniSalvate){
                if(rec.getNomeLocazione().equals(nomeLocazione)){

                    recensioniPerLaLocazione.add(rec);
                }
            }
            return recensioniPerLaLocazione;
        }
        else return new ArrayList<>();
    }

    /*metodo utilizzato per calcolare la media dei voti per una locazione, nel caso non esistano recensioni
     per la locazione considerata allora viene ritornato il valore 6 che è un valore non presente tra le
     possibili soluzioni(dato che i voti possibili variano da zero a sei
     */
    public int calcolaMedia(String nomeLocazione) throws DeserializzazioneException {
        ArrayList<Recensione>recensioni = ritornaRecensioni(nomeLocazione);
        int numeroRecensioni = recensioni.size();

        if(numeroRecensioni==0)
            return 6;

        int sommaTotale=0;
        for(Recensione recensione: recensioni){
            sommaTotale += recensione.getNumeroStelle();
        }


        return sommaTotale/numeroRecensioni;

    }

}
