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


public class ControlloreDisponibilitàBeb extends ControlloreDisponibilità{

    private static String percorsoPrenotazioniBeb = Constants.PRENOTAZIONE_BEB_PATH;


    @Override@SuppressWarnings("unchecked")
    public boolean controllo(Locazione loc, GregorianCalendar gcInizio, GregorianCalendar gcFine,String numeroPersone) throws DeserializzazioneException, SerializzazioneException, IOException {
        Integer totali = Integer.parseInt(loc.getPostiTotali().trim());
        Integer postiRichiesti = Integer.parseInt(numeroPersone.trim());
        ArrayList<GregorianCalendar> datePrenotazione= ContaGiorni.restituisciArrayDate(gcInizio, gcFine);

        ArrayList<PostiDisponibili> prenotateList = new ArrayList<>();

        ArrayList<PostiDisponibili> temp = new ArrayList<>();

        File file = new File(percorsoPrenotazioniBeb);

        SerializzaOggetti sobj = new SerializzaOggetti();
        if (file.length() == 0) {
            for (GregorianCalendar data_prenotazione : datePrenotazione) {
                PostiDisponibili cp = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                int contatore_aggiornato = cp.getContatore() + postiRichiesti;
                cp.setContatore(contatore_aggiornato);
                if(totali<cp.getContatore())
                    return false;
                else {
                    prenotateList.add(cp);
                }

            }
            sobj.serializza(prenotateList, percorsoPrenotazioniBeb);
            return true;
        } else {

            DeserializzaOggetti dobj = new DeserializzaOggetti();
            prenotateList = (ArrayList<PostiDisponibili>) dobj.deserializza(percorsoPrenotazioniBeb);
            for (GregorianCalendar data_prenotazione : datePrenotazione) {

                boolean condizione = false;
                for (PostiDisponibili camera_prenotata : prenotateList) {
                    if (camera_prenotata.getNomeLocazion().equals(loc.getNomeLocazione()) && data_prenotazione.equals(camera_prenotata.getData())) {
                        if (totali <= camera_prenotata.getContatore())
                            return false;
                        if (!(totali == camera_prenotata.getContatore())) {
                            int contatore_aggiornato = camera_prenotata.getContatore() + postiRichiesti;
                            camera_prenotata.setContatore(contatore_aggiornato);
                            condizione = true;
                            break;
                        }
                    }
                }
                if (condizione)
                    continue;
                PostiDisponibili nuovaData = new PostiDisponibili(loc.getNomeLocazione(), data_prenotazione);
                int contatore_aggiornato = nuovaData.getContatore() + postiRichiesti;
                nuovaData.setContatore(contatore_aggiornato);
                if(totali<nuovaData.getContatore()){
                    return false;
                }else {
                    temp.add(nuovaData);
                }
            }
            prenotateList.addAll(temp);
            sobj.serializza(prenotateList, percorsoPrenotazioniBeb);
            return true;

        }
    }
}
