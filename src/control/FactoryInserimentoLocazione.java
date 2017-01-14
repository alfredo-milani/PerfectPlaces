package control;

import entity.*;



/**
 * Created by maria on 15/12/16.
 */
public class FactoryInserimentoLocazione {

    private static FactoryInserimentoLocazione instance = null;

    private String nomeLocazione;
    private String postiTotali;
    private String provincia;
    private String indirizzo;
    private String userLocatore;
    private String prezzo;
    private String descrizione;
    private boolean parcheggio;
    private boolean wifi;
    private boolean pet;

    protected FactoryInserimentoLocazione(){

    }

    public void changeSettings(String nomeLocazione,String postiTotali,String provincia, String indirizzo, String userLocatore, String prezzo,
                               String descrizione, boolean parcheggio, boolean wifi, boolean pet) {
        this.nomeLocazione = nomeLocazione;
        this.postiTotali=postiTotali;
        this.provincia= provincia;
        this.indirizzo = indirizzo;
        this.userLocatore = userLocatore;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.parcheggio = parcheggio;
        this.wifi = wifi;
        this.pet = pet;
    }

    public synchronized static final FactoryInserimentoLocazione getFactoryInstance(){
        if (FactoryInserimentoLocazione.instance == null)
            FactoryInserimentoLocazione.instance = new FactoryInserimentoLocazione();
        return instance;
    }

    public Locazione createGenericLocation(String type) throws Exception {

        switch (type){
            case "0": return new Albergo(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "", "", "", "");
            case "1": return new Appartamento(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "", "", false, "");
            case "2": return new Beb(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "");
            case "3": return new CasaVacanza(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "", "", false, "");
            case "4": return new Ostello(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet, "");
            default: throw new Exception("Invalid type : " + type);
        }


    }

}
