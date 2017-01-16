package control.prenotazione;


public class FactoryPrenotazionePiùPosti extends FactoryPrenotazione {

    /*
    Ho scelto di usare il singleton in modo che avvenga una registrazione alla volta
     */

    private FactoryPrenotazionePiùPosti(){
    }

    //Instance necessaria per il singleton
    private static FactoryPrenotazionePiùPosti instance = null;

    //Metodo necessario per il singleton
    public synchronized static final FactoryPrenotazionePiùPosti getFactoryPrenotazionePiùPosti(){
        if(FactoryPrenotazionePiùPosti.instance==null)
            FactoryPrenotazionePiùPosti.instance = new FactoryPrenotazionePiùPosti();
        return instance;
    }

    @Override
    public ControlloreDisponibilità creaControlloreDisponibilità() {
        return new ControlloreDisponibilitàPiùPosti();
    }

    @Override
    public ControlloreRegistraPrenotati creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotazioniPiùPosti();
    }
}
