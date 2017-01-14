package control.prenotazione;


public class FactoryPrenotazioneAppartamento extends FactoryPrenotazione {


    /*
    Ho scelto di usare il singleton in modo che avvenga una registrazione alla volta
     */

    private FactoryPrenotazioneAppartamento(){

    }

    //Instance necessaria per il singleton
    private static FactoryPrenotazioneAppartamento instance = null;

    //Metodo necessario per il singleton
    public synchronized static final FactoryPrenotazioneAppartamento getFactoryPrenotazioneAppatamento(){
        if(FactoryPrenotazioneAppartamento.instance==null)
            FactoryPrenotazioneAppartamento.instance = new FactoryPrenotazioneAppartamento();
        return instance;
    }

    @Override
    public ControlloreDisponibilità creaControlloreDisponibilità() {
        return new ControlloreDisponibilitàAppartamento();
    }

    @Override
    public ControlloreRegistraPrenotati creaControlloreRegistraPrenotati() {
        return new ControlloreRegistraPrenotatiAppartamento();
    }
}
