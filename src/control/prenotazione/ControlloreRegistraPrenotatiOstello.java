package control.prenotazione;


import constants.Constants;
import entity.Prenotazione;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ControlloreRegistraPrenotatiOstello extends ControlloreRegistraPrenotati{

    private static String percorsoPrenotatiOstello = Constants.PRENOTATI_OSTELLO_PATH;

    @Override@SuppressWarnings("unchecked")
    public void registra(String nomeLocazione, String proprietario, String cliente, GregorianCalendar dataInizio, GregorianCalendar dataFine, String tipo,String prezzo,String numeroPersone) throws SerializzazioneException, DeserializzazioneException{
        ArrayList<Prenotazione> listaPrenotazione =  new ArrayList<>();
        SerializzaOggetti sobj  = new SerializzaOggetti();

        File file =  new File(percorsoPrenotatiOstello);
        if(file.length()==0){
            Prenotazione clientePrenotazione = new Prenotazione(nomeLocazione,proprietario,cliente,dataInizio,dataFine,tipo,prezzo,numeroPersone);
            listaPrenotazione.add(clientePrenotazione);
            sobj.serializza(listaPrenotazione,percorsoPrenotatiOstello);

        }else{

            DeserializzaOggetti dobj = new DeserializzaOggetti();
            listaPrenotazione = (ArrayList<Prenotazione>) dobj.deserializza(percorsoPrenotatiOstello);
            int precedenteRegistrazione=0;

            for(Prenotazione prenotazione : listaPrenotazione){
                if(prenotazione.getNomeLocazione().equals(nomeLocazione)&& prenotazione.getCliente().equals(cliente)&&
                        prenotazione.getDataInizio().equals(dataInizio)&& prenotazione.getDataFine().equals(dataFine)){
                    precedenteRegistrazione+=1;
                }
            }
            if(precedenteRegistrazione==0) {
                Prenotazione clientePrenotazione = new Prenotazione(nomeLocazione, proprietario, cliente, dataInizio, dataFine,tipo,prezzo,numeroPersone);
                clientePrenotazione.setTipo(tipo);
                listaPrenotazione.add(clientePrenotazione);
                sobj.serializza(listaPrenotazione, percorsoPrenotatiOstello);
            }
        }
    }
}