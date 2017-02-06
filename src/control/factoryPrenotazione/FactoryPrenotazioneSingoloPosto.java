package control.factoryPrenotazione;


public class FactoryPrenotazioneSingoloPosto extends FactoryPrenotazione {


    private FactoryPrenotazioneSingoloPosto(){

    }

    //Instance necessaria per il singleton
    private static FactoryPrenotazioneSingoloPosto instance = null;

    //Metodo necessario per il singleton
    public synchronized static final FactoryPrenotazioneSingoloPosto getFactoryPrenotazioneSingoloPosto(){
        if(FactoryPrenotazioneSingoloPosto.instance==null)
            FactoryPrenotazioneSingoloPosto.instance = new FactoryPrenotazioneSingoloPosto();
        return instance;
    }

    @Override
    public ControlloreDisponibilità creaControlloreDisponibilità() {
        return new ControlloreDisponibilitàSingoloPosto();
    }

    @Override
    public ControlloreRegistraPrenotazione creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotazioniSingoloPosto();
    }
}
