package standAlone.control;

import constants.Constants;
import entity.Faq;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standAlone.boundary.BoundaryFallimento;
import standAlone.boundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by maria on 11/01/17.
 */
public class ControlloreRispondiFaq {

    private String path = Constants.FAQ_PATH;
    private ArrayList<Faq> faqArray;

    private ControlloreLinguaAmministratore cl;

    public ControlloreRispondiFaq(){
        this.cl = new ControlloreLinguaAmministratore();
    }

    public void rispondiComeSuperUtente(String domanda,String risposta) throws DeserializzazioneException, SerializzazioneException {
        ResourceBundle bundle = this.cl.getBundleFromProp();

        DeserializzaOggetti dobj = new DeserializzaOggetti();
        SerializzaOggetti sobj = new SerializzaOggetti();
        faqArray=(ArrayList<Faq>) dobj.deserializza(path);

        if(risposta.equals("")) {
            new BoundaryFallimento(bundle.getString("boundaryFaq_risposte_vuote"));
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
        File file = new File(path);
        if(file.length()!=0) {
            try {


                faqArray = (ArrayList<Faq>) dobj.deserializza(path);

                for (Faq faq : faqArray) {
                    if (!faq.getSettaRisposta())
                        domande.add(faq.getDomanda());
                }
                dom = new String[domande.size()];
                for (int i = 0; i < domande.size(); ++i) {
                    dom[i] = domande.get(i);
                }

            } catch (DeserializzazioneException e) {
                e.printStackTrace();
            }
        }
        return dom;
    }
}
