package control.Decorator;

/**
 * Created by maria on 13/01/17.
 */
public abstract class DecoratorPrezzo extends ComponentePrezzo {

    private ComponentePrezzo component;

    public DecoratorPrezzo(ComponentePrezzo component){
        this.component = component;
    }

    @Override
    public int calcolaPrezzo() {
        int resultsFromRedirection = this.component.calcolaPrezzo();
        return resultsFromRedirection;
    }
}
