package standAlone.control;

import constants.Constants;
import entity.Faq;
import entity.Utente;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import standAlone.boundary.BoundaryFallimento;
import standAlone.boundary.BoundarySuccesso;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ControlloreRimuoviFaq {

    JTextArea area;

    private String path = Constants.FAQ_PATH;

    public ControlloreRimuoviFaq(JTextArea area){
        this.area = area;
    }

    public void rimuovi(String domanda) throws DeserializzazioneException, SerializzazioneException {
        ControlloreProfiloAmministratore cp =
                new ControlloreProfiloAmministratore();
        Utente utente = cp.ottieniUtente(System.getProperty(Constants.USER_KEY));

        Locale langLocale;
        if (utente != null) {
            langLocale = utente.getLingua();
        } else {
            langLocale = Locale.getDefault();
        }

        ResourceBundle bundle = ResourceBundle
                .getBundle(Constants.PACKAGE_LANGUAGE, langLocale);

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
        ControlloreProfiloAmministratore cp =
                new ControlloreProfiloAmministratore();
        Utente utente = cp.ottieniUtente(System.getProperty(Constants.USER_KEY));

        Locale langLocale;
        if (utente != null) {
            langLocale = utente.getLingua();
        } else {
            langLocale = Locale.getDefault();
        }

        ResourceBundle bundle = ResourceBundle
                .getBundle(Constants.PACKAGE_LANGUAGE, langLocale);

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
