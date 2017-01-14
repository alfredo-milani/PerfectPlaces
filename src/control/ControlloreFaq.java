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

    public ControlloreFaq() throws DeserializzazioneException {
     //    DeserializzaOggetti dobj = new DeserializzaOggetti();
     //      this.faqArray=(ArrayList<Faq>) dobj.deserializza(path);
    }

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



    public static void main(String[] args) throws DeserializzazioneException, SerializzazioneException {
        ControlloreFaq cf = new ControlloreFaq();
        /*cf.inserisciDomanda("Posso gestire più locazioni da un unico account?",1);

        cf.rispondiComeSuperUtente("Posso gestire più locazioni da un unico account?","E' possibile gestire un numero qualsiasi di locazioni utilizzando lo stesso account utente, l'apposita area Proprietario è pensata proprio per soddisfare a tutte le esigenze dell'utente in qualita di Locatore, ivi compresa la gestione di tutte le locazioni in suo possesso, inserite correttamente nel sistema, da un unico profilo. Questo permette anche al cliente di avere un quadro esaustivo riguardante l'affidabilità del locatore, basato su esperienze di trattative precendenti proprie o di altri utenti");


        String ris = cf.ritornaRisposta("Posso gestire più locazioni da un unico account?");*/
        ArrayList<String> dom = cf.ritornaDomande(0);
        //System.out.println("Risposta: "+ris);
        System.out.println(dom.size());

    }
}
