package control.prenotazione;


public class FactoryPrenotazioneAlbergo extends FactoryPrenotazione {

    /*
    Ho scelto di usare il singleton in modo che avvenga una registrazione alla volta
     */

    private FactoryPrenotazioneAlbergo(){
    }

    //Instance necessaria per il singleton
    private static FactoryPrenotazioneAlbergo instance = null;

    //Metodo necessario per il singleton
    public synchronized static final FactoryPrenotazioneAlbergo getFactoryPrenotazioneAlbergo(){
        if(FactoryPrenotazioneAlbergo.instance==null)
            FactoryPrenotazioneAlbergo.instance = new FactoryPrenotazioneAlbergo();
        return instance;
    }

    @Override
    public ControlloreDisponibilità creaControlloreDisponibilità() {
        return new ControlloreDisponibilitàAlbergo();
    }

    @Override
    public ControlloreRegistraPrenotati creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotatiAlbergo();
    }
}
