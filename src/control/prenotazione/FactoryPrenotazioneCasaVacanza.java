package control.prenotazione;


public class FactoryPrenotazioneCasaVacanza extends FactoryPrenotazione {

    /*
    Ho scelto di usare il singleton in modo che avvenga una registrazione alla volta
     */

    private FactoryPrenotazioneCasaVacanza(){

    }

    //Instance necessaria per il singleton
    private static FactoryPrenotazioneCasaVacanza instance = null;

    //Metodo necessario per il singleton
    public synchronized static final FactoryPrenotazioneCasaVacanza getFactoryPrenotazioneCasaVacanza(){
        if(FactoryPrenotazioneCasaVacanza.instance==null)
            FactoryPrenotazioneCasaVacanza.instance = new FactoryPrenotazioneCasaVacanza();
        return instance;
    }

    @Override
    public ControlloreDisponibilità creaControlloreDisponibilità() {
        return new ControlloreDisponibilitàCasaVacanza();
    }

    @Override
    public ControlloreRegistraPrenotati creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotatiCasaVacanza();
    }
}
