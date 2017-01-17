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
import java.util.ResourceBundle;

public class ControlloreRimuoviFaq {

    JTextArea area;

    private String path = Constants.FAQ_PATH;

    private ControlloreLinguaAmministratore cl;

    public ControlloreRimuoviFaq(JTextArea area){
        this.area = area;
        this.cl = new ControlloreLinguaAmministratore();
    }

    public void rimuovi(String domanda) throws DeserializzazioneException, SerializzazioneException {
        ResourceBundle bundle = this.cl.getBundleFromProp();

        if(domanda.equals("")) {
            new BoundaryFallimento(bundle.getString("boundaryFaq_domanda_non_presente"));
            return;
        }
        SerializzaOggetti sobj= new SerializzaOggetti();
        DeserializzaOggetti dobj = new DeserializzaOggetti();
        ArrayList<Faq> domande = (ArrayList<Faq>) dobj.deserializza(path);

        for(int i=0; i<domande.size(); ++i){
            if(domande.get(i).getDomanda().equals(domanda)){
                domande.remove(i);
                sobj.serializza(domande,path);
                new BoundarySuccesso();
                return;
            }
        }

        new BoundaryFallimento(bundle.getString("boundaryFaq_domanda_non_presente"));
    }

    public void visualizzaDomandeSenzaRisposta(){
        ResourceBundle bundle = this.cl.getBundleFromProp();

        ArrayList<Faq> faq;
        DeserializzaOggetti dobj= new DeserializzaOggetti();
        String domande= bundle.getString("boundaryFaq_domande_senza_risposta");
        try{
            faq = (ArrayList<Faq>) dobj.deserializza(path);
            for(Faq f: faq ){
                if(!f.getSettaRisposta()) {
                    domande = domande + f.getDomanda() + '\n';
                }
            }
            area.insert(domande,0);
        }catch (DeserializzazioneException e){
            e.printStackTrace();
        }


    }
}
