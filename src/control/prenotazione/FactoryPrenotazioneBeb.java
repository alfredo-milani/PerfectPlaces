package control.prenotazione;


public class FactoryPrenotazioneBeb extends FactoryPrenotazione{

    /*
    Ho scelto di usare il singleton in modo che avvenga una registrazione alla volta
     */

    private FactoryPrenotazioneBeb(){
    }

    /* Instance necessaria per il singleton */
    private static FactoryPrenotazioneBeb instance = null;

    //Metodo necessario per il singleton
    public synchronized static final FactoryPrenotazioneBeb getFactoryPrenotazioneBeb(){
        if(FactoryPrenotazioneBeb.instance==null)
            FactoryPrenotazioneBeb.instance = new FactoryPrenotazioneBeb();
        return instance;
    }

    @Override
    public ControlloreDisponibilità creaControlloreDisponibilità() {
        return new ControlloreDisponibilitàBeb();
    }

    @Override
    public ControlloreRegistraPrenotati creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotatiBeb();
    }
}
