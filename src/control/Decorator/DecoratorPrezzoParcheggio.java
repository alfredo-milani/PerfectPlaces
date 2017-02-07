package control.Decorator;

/**
 * Created by maria
 */
//ConcreteDecorator 1
public class DecoratorPrezzoParcheggio extends DecoratorPrezzo {

    private int prezzoParcheggio;

    public DecoratorPrezzoParcheggio(ComponentePrezzo component, int prezzoParcheggio){
        super(component);
        this.prezzoParcheggio = prezzoParcheggio;

    }

    protected int applyParcheggio(int input){
        int output = input + this.prezzoParcheggio;
        return output;
    }

    @Override
    public int calcolaPrezzo() {
        int preliminaryPrice = super.calcolaPrezzo();
        preliminaryPrice = this.applyParcheggio(preliminaryPrice);
        return preliminaryPrice;
    }

}
