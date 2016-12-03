package entity;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by gabriele on 12/11/16.
 */
public class CamerePrenotate implements Serializable {
    private String nomeLocazione;
    private GregorianCalendar data;
    private int contatore;


    public CamerePrenotate (String nomeLocazione, GregorianCalendar data){
        this.nomeLocazione = nomeLocazione;
        this.data = data;
        this.contatore=0;
    }


    public String getNomeLocazion() {
        return nomeLocazione;
    }

    public void setNomeLocazione(String nomeLocazione) {
        this.nomeLocazione = nomeLocazione;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar date) {
        this.data = date;
    }

    public int getContatore() {
        return contatore;
    }

    public void setContatore(int count) {
        this.contatore = count;
    }
}
