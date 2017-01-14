package control.prenotazione;


public class FactoryPrenotazioneOstello  extends FactoryPrenotazione{

    /*
    Ho scelto di usare il singleton in modo che avvenga una registrazione alla volta
     */

    private FactoryPrenotazioneOstello(){

    }

    //Instance necessaria per il singleton
    private static FactoryPrenotazioneOstello instance = null;

    //Metodo necessario per il singleton
    public synchronized static final FactoryPrenotazioneOstello getFactoryPrenotazioneOstello(){
        if(FactoryPrenotazioneOstello.instance==null)
            FactoryPrenotazioneOstello.instance = new FactoryPrenotazioneOstello();
        return instance;
    }

    @Override
    public ControlloreDisponibilità creaControlloreDisponibilità() {
        return new ControlloreDisponibilitàOstello();
    }

    @Override
    public ControlloreRegistraPrenotati creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotatiOstello();
    }
}
