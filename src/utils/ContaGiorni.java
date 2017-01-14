package utils;

import constants.Constants;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ContaGiorni {


    @SuppressWarnings("unchecked")
    public static  ArrayList<GregorianCalendar> restituisciArrayDate(GregorianCalendar dataInizio, GregorianCalendar dataFine) throws IOException, SerializzazioneException, DeserializzazioneException {
        String percorsoTemp = Constants.TMPDATE_PATH;
        ArrayList<GregorianCalendar> elencoDate = new ArrayList<>();
        File f = new File(percorsoTemp);

        if(f.delete()){

            FileWriter w = new FileWriter(percorsoTemp);
            BufferedWriter b = new BufferedWriter(w);
            b.write("");
            b.flush();
            b.close();

            SerializzaOggetti sobj = new SerializzaOggetti();
            sobj.serializza(elencoDate, percorsoTemp);

            DeserializzaOggetti dobj = new DeserializzaOggetti();

            while (!dataInizio.equals(dataFine)) {

                elencoDate = (ArrayList<GregorianCalendar>) dobj.deserializza(percorsoTemp);
                elencoDate.add(dataInizio);
                sobj.serializza(elencoDate, percorsoTemp);
                dataInizio.add(Calendar.DAY_OF_MONTH, 1);
            }
            elencoDate = (ArrayList<GregorianCalendar>) dobj.deserializza(percorsoTemp);
            elencoDate.add(dataInizio);
            sobj.serializza(elencoDate, percorsoTemp);
        }
        return elencoDate;
    }

}
