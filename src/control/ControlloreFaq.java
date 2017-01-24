package control;

import constants.Constants;
import entity.Faq;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by maria on 21/12/16.
 */
public class ControlloreFaq {

    private String path = Constants.FAQ_PATH;
    private ArrayList<Faq> faqArray=new ArrayList<>();

    public ControlloreFaq()  {

    }

    //ritorna la lista delle sole domande, per cui è già stata fornita una risposta
    public ArrayList<String> ritornaDomande(int type) throws DeserializzazioneException {
        File file = new File(path);
        DeserializzaOggetti dobj = new DeserializzaOggetti();


        ArrayList<String> domandeList = new ArrayList<String>();

        if(file.length()==0)
            return null;

        this.faqArray=(ArrayList<Faq>) dobj.deserializza(path);

        for(Faq faq: faqArray) {
            if (faq.getSettaRisposta() && faq.getType()==type) {
                String domanda = faq.getDomanda();
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
        this.faqArray=(ArrayList<Faq>) dobj.deserializza(path);

        String risposta = "";

        for(Faq faq: faqArray) {
            if (faq.getDomanda().equals(domanda) && faq.getSettaRisposta()) {
                risposta = faq.getRisposta();

            }
        }
        return risposta;


    }

    //metodo che permette di inserire una nuova Faq nel sistema, settandone di default la risposta come stringa vuota,
    // e marcandola come domanda per cui non è stata ancora fornita una risposta
    public void inserisciDomanda(String domanda, int type) throws DeserializzazioneException, SerializzazioneException {

        File file = new File(path);
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();
        Faq faq = new Faq(domanda, "", false, type);

        if(file.length()==0){
            faqArray.add(faq);
            sobj.serializza(faqArray,path);
        }
        else {
            faqArray = (ArrayList<Faq>) dobj.deserializza(path);
            faqArray.add(faq);
            sobj.serializza(faqArray, path);
        }
    }

    //metodo di utilità per rimuovere una Faq
    public void cancellaFaq(String domanda) throws DeserializzazioneException, SerializzazioneException {
        File file = new File(path);
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();

        if(file.length()==0){
            return;
        }
        else {
            faqArray = (ArrayList<Faq>) dobj.deserializza(path);
            for(int i=0; i< faqArray.size(); ++i){

                if (domanda.equals(faqArray.get(i).getDomanda())) {
                    faqArray.remove(i);
                    break;
                }

            }


            sobj.serializza(faqArray, path);
        }


    }




}
