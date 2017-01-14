package control.prenotazione;

public abstract class FactoryPrenotazione {

    public abstract ControlloreDisponibilità creaControlloreDisponibilità();
    public abstract ControlloreRegistraPrenotati creaControlloreRegistraPrenotati();
}
