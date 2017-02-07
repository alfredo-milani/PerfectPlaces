package control.factoryPrenotazione;

public abstract class FactoryPrenotazione {

    public abstract ControlloreDisponibilità creaControlloreDisponibilità();
    public abstract ControlloreRegistraPrenotazione creaControlloreRegistraPrenotati();
}
