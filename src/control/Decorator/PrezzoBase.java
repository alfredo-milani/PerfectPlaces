package control.Decorator;

/**
 * Created by maria on 13/01/17.
 */
//ConcreteComponent
public class PrezzoBase extends ComponentePrezzo {

    int prezzo;

    public PrezzoBase(int prezzo){
        this.setPrezzo (prezzo);
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }


    @Override
    public int calcolaPrezzo() {
        return (this.prezzo);
    }
}
