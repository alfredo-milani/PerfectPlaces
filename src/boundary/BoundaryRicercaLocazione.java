package boundary;

import control.AdapterRicercaPerLocazione;
import control.ControlloreRicercaGlobale;
import control.ControlloreRicercaPerLocazione;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.ContaGiorni;
import utils.TrasformaDate;
import utils.VerificaInput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class BoundaryRicercaLocazione {

    private ControlloreRicercaGlobale globale;
    private ControlloreRicercaPerLocazione ctrl;

    public BoundaryRicercaLocazione(){
        globale = new ControlloreRicercaGlobale();
        ctrl = new AdapterRicercaPerLocazione(globale);
    }



    @SuppressWarnings("unchecked")
    public Object  ricerca(int commandInt, int commandavAnzataInt,String provincia,String prezzo,
                                         String parcheggio,String wifi, String pet, String caratteristica,String dataInizio,String dataFine) throws DeserializzazioneException, ClassNotFoundException, IOException, InstantiationException, SerializzazioneException, IllegalAccessException {

        GregorianCalendar gcInizio = TrasformaDate.trasformaInGregorianCalendar(dataInizio);
        GregorianCalendar gcFine = TrasformaDate.trasformaInGregorianCalendar(dataFine);
        ArrayList<GregorianCalendar> date = ContaGiorni.restituisciArrayDate(gcInizio,gcFine);
        int numeroGiorni = date.size();

        ArrayList<Locazione> elencoLocazioni = new ArrayList<>();
        Albergo albergo =new Albergo();
        Appartamento appartamento = new Appartamento();
        Beb beb = new Beb();
        CasaVacanza casa = new CasaVacanza();
        Ostello ostello = new Ostello();

        if(commandavAnzataInt==10) {
                if(commandInt==100){
                    elencoLocazioni = globale.ricercaGlobale(provincia,prezzo,numeroGiorni);
                }
                if (commandInt == 0) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca(albergo, provincia, prezzo,numeroGiorni);
                } else if (commandInt == 1) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca(appartamento, provincia, prezzo,numeroGiorni);
                } else if (commandInt == 2) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca(beb, provincia, prezzo,numeroGiorni);
                } else if (commandInt == 3) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca(casa, provincia, prezzo,numeroGiorni);

                } else if (commandInt == 4) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricerca(ostello, provincia, prezzo,numeroGiorni);
                }
        }else{
                if(commandavAnzataInt==0) {
                    elencoLocazioni= (ArrayList<Locazione>) ctrl.ricercaAvanzata(albergo,provincia,prezzo,numeroGiorni, parcheggio, wifi, pet,caratteristica);
                }
                else if(commandavAnzataInt==1) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata(appartamento,provincia,prezzo,numeroGiorni, parcheggio, wifi, pet,caratteristica);
                }
                else if(commandavAnzataInt==2) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata(beb,provincia,prezzo,numeroGiorni, parcheggio, wifi, pet,"caratt");
                }
                else if(commandavAnzataInt==3) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata(casa,provincia,prezzo,numeroGiorni, parcheggio, wifi, pet, caratteristica);
                }
                else if(commandavAnzataInt==4) {
                    elencoLocazioni = (ArrayList<Locazione>) ctrl.ricercaAvanzata(ostello, provincia, prezzo,numeroGiorni, parcheggio, wifi, pet, caratteristica);
                }

            }

            return elencoLocazioni;
    }

    public boolean controlloDati(String provincia, String prezzo, String dataInizio, String dataFine) throws IOException {
        VerificaInput vi = new VerificaInput();
        return (vi.verificaProvincia(provincia)&&vi.verificaDate(dataInizio,dataFine)&&vi.verificaPrezzo(prezzo));

    }

}