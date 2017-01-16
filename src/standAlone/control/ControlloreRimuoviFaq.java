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

public class ControlloreRimuoviFaq {

    JTextArea area;

    private String path = Constants.FAQ_PATH;

    public ControlloreRimuoviFaq(JTextArea area){
        this.area = area;
    }

    public void rimuovi(String domanda) throws DeserializzazioneException, SerializzazioneException {

        if(domanda.equals("")) {
            new BoundaryFallimento("Spiacente, domanda non presente");
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

        new BoundaryFallimento("Spiacente, domanda non presente");
    }

    public void visualizzaDomandeSenzaRisposta(){
        ArrayList<Faq> faq;
        DeserializzaOggetti dobj= new DeserializzaOggetti();
        String domande= "DOMANDE SENZA RISPOSTA: \n";
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
