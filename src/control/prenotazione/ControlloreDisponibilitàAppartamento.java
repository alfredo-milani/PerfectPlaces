package control.prenotazione;

import constants.Constants;
import entity.PostiDisponibili;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.ContaGiorni;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;



public class ControlloreDisponibilitàAppartamento extends ControlloreDisponibilità {

    private static String percorsoPrenotazioniAppartamenti = Constants.PRENOTAZIONE_APPARTAMENTO_PATH;

    @Override@SuppressWarnings("unchecked")
    public boolean controllo(Locazione loc, GregorianCalendar gcInizio, GregorianCalendar gcFine,String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {

        ArrayList<GregorianCalendar> datePrenotazione= ContaGiorni.restituisciArrayDate(gcInizio, gcFine);
        ArrayList<PostiDisponibili> prenotateList = new ArrayList<>();
        ArrayList<PostiDisponibili> temp = new ArrayList<>();

        File file = new File(percorsoPrenotazioniAppartamenti);

        SerializzaOggetti sobj = new SerializzaOggetti();
        if (file.length() == 0) {
            for (GregorianCalendar data_prenotazione : datePrenotazione) {
                PostiDisponibili cp = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                prenotateList.add(cp);

            }
            sobj.serializza(prenotateList, percorsoPrenotazioniAppartamenti);
            return true;
        } else {
            DeserializzaOggetti dobj = new DeserializzaOggetti();
            prenotateList = (ArrayList<PostiDisponibili>) dobj.deserializza(percorsoPrenotazioniAppartamenti);
            for (GregorianCalendar data_prenotazione : datePrenotazione) {
                for (PostiDisponibili camera_prenotata : prenotateList) {
                    if (camera_prenotata.getNomeLocazion().equals(loc.getNomeLocazione()) && data_prenotazione.equals(camera_prenotata.getData()))
                        return false;
                }
                PostiDisponibili nuovaData = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                temp.add(nuovaData);
            }
            prenotateList.addAll(temp);
            sobj.serializza(prenotateList, percorsoPrenotazioniAppartamenti);
            return true;

        }
    }
}
