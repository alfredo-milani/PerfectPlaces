package boundary;

import control.ControlloreVisualizzaPrezzo;
import entity.Albergo;
import entity.Beb;
import entity.Locazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.ContaGiorni;
import utils.TrasformaDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BoundaryVisualizzaPrezzo {

    private ControlloreVisualizzaPrezzo cvp;

    public BoundaryVisualizzaPrezzo(){
        cvp = new ControlloreVisualizzaPrezzo();
    }

    public int ritornaPrezzoBase(Locazione locazione,String tipoPensione,String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException {
        int numeroGiorni = calcolaNumeroGiorni(dataInizio,dataFine);
        cvp.applicaPrezzoBase(locazione,numeroGiorni ,tipoPensione);
        return cvp.ritornaPrezzoBase();
    }
    public int ritornaPrezzoConServizi(String dataInizio, String dataFine, String parcheggio, String wifi,String pet) throws DeserializzazioneException, SerializzazioneException, IOException {

        int numeroGiorni = calcolaNumeroGiorni(dataInizio,dataFine);
        cvp.applicaServizi( numeroGiorni,parcheggio,wifi,pet );
        return cvp.ritornaPrezzoConServizi();
    }

    private int calcolaNumeroGiorni(String dataInizio, String dataFine) throws DeserializzazioneException, SerializzazioneException, IOException {

        GregorianCalendar gcInizio = TrasformaDate.trasformaInGregorianCalendar(dataInizio);
        GregorianCalendar gcFine = TrasformaDate.trasformaInGregorianCalendar(dataFine);
        ArrayList<GregorianCalendar> date = ContaGiorni.restituisciArrayDate(gcInizio,gcFine);
        return date.size();

    }




}
