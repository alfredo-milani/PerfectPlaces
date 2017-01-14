package standAlone.control;

import constants.Constants;
import entity.Faq;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standAlone.boundary.BoundaryFallimento;
import standAlone.boundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by maria on 11/01/17.
 */
public class ControlloreRispondiFaq {

    private String path = Constants.FAQ_PATH;
    private ArrayList<Faq> faqArray;

    JTextArea area;

    public ControlloreRispondiFaq(){

    }

    public void rispondiComeSuperUtente(String domanda,String risposta) throws DeserializzazioneException, SerializzazioneException {
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();
        faqArray=(ArrayList<Faq>) dobj.deserializza(path);

        if(risposta.equals("")) {
            new BoundaryFallimento("Spiacente, non è possibile inserire risposte vuote");
            return;
        }

        for(Faq faq: faqArray){
            if(faq.getDomanda().equals(domanda) && !faq.getSettaRisposta()){
                faq.setRisposta(risposta);
                faq.setSettaRisposta(true);
                break;

            }

        }

        sobj.serializza(faqArray,path);
        new BoundarySuccesso();

    }


    public String[] visualizzaDomandeSenzaRisposta(){

        ArrayList<Faq> faqArray;
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        ArrayList<String> domande = new ArrayList<String>();
        String dom[];

        dom=null;
        try {
            faqArray = (ArrayList<Faq>)dobj.deserializza(path);

            for (Faq faq : faqArray ){
                if(!faq.getSettaRisposta())
                    domande.add(faq.getDomanda());
            }
            dom = new String[domande.size()];
            for(int i=0; i< domande.size() ; ++i) {
                dom[i] = domande.get(i);
            }

        } catch (DeserializzazioneException e) {
            e.printStackTrace();
        }
        return dom;
    }
}
