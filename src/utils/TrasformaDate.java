package utils;

import java.util.GregorianCalendar;

/**
 * Created by gabriele on 08/12/16.
 */
public class TrasformaDate {

    public TrasformaDate(){
    }

    public GregorianCalendar trasformaInGregorianCalendar(String data){
        int giorno = Integer.parseInt(data.substring(8, 10));
        int mese = Integer.parseInt(data.substring(5, 7));
        int anno = Integer.parseInt(data.substring(0, 4));

        int meseEffettivo = mese-1;

        return new GregorianCalendar(anno,meseEffettivo,giorno);
    }
}