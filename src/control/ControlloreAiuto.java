package control;

import constants.Constants;
import entity.DomandaUtente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by maria
 */
public class ControlloreAiuto {

    private String path = Constants.FAQ_PATH;
    private ArrayList<DomandaUtente> domArray =new ArrayList<>();

    public ControlloreAiuto()  {

    }

    //ritorna la lista delle sole domande, per cui è già stata fornita una risposta
    public ArrayList<String> ritornaDomande(int type) throws DeserializzazioneException {
        File file = new File(path);
        DeserializzaOggetti dobj = new DeserializzaOggetti();


        ArrayList<String> domandeList = new ArrayList<String>();

        if(file.length()==0)
            return null;

        this.domArray =(ArrayList<DomandaUtente>) dobj.deserializza(path);

        for(DomandaUtente domandaUtente : domArray) {
            if (domandaUtente.getSettaRisposta() && domandaUtente.getType()==type) {
                String domanda = domandaUtente.getDomanda();
                domandeList.add(domanda);
            }
        }
        if(domandeList.size()==0)
            return null;
        return domandeList;
    }


    //metodo che ritorna la risposta associata ad una specifica domanda
    public String ritornaRisposta (String domanda) throws DeserializzazioneException {
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        this.domArray =(ArrayList<DomandaUtente>) dobj.deserializza(path);

        String risposta = "";

        for(DomandaUtente domandaUtente : domArray) {
            if (domandaUtente.getDomanda().equals(domanda) && domandaUtente.getSettaRisposta()) {
                risposta = domandaUtente.getRisposta();

            }
        }
        return risposta;


    }

    //metodo che permette di inserire una nuova DomandaUtente nel sistema, settandone di default la risposta come stringa vuota,
    // e marcandola come domanda per cui non è stata ancora fornita una risposta
    public void inserisciDomanda(String domanda, int type) throws DeserializzazioneException, SerializzazioneException {

        File file = new File(path);
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();
        DomandaUtente domandaUtente = new DomandaUtente(domanda, "", false, type);

        if(file.length()==0){
            domArray.add(domandaUtente);
            sobj.serializza(domArray,path);
        }
        else {
            domArray = (ArrayList<DomandaUtente>) dobj.deserializza(path);
            domArray.add(domandaUtente);
            sobj.serializza(domArray, path);
        }
    }

    //metodo di utilità per rimuovere una DomandaUtente
    public void cancellaFaq(String domanda) throws DeserializzazioneException, SerializzazioneException {
        File file = new File(path);
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();

        if(file.length()==0){
            return;
        }
        else {
            domArray = (ArrayList<DomandaUtente>) dobj.deserializza(path);
            for(int i = 0; i< domArray.size(); ++i){

                if (domanda.equals(domArray.get(i).getDomanda())) {
                    domArray.remove(i);
                    break;
                }

            }


            sobj.serializza(domArray, path);
        }


    }




}
