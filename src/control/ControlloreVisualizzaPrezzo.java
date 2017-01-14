package control;

import control.Decorator.*;
import entity.Albergo;
import entity.Locazione;
import entity.Ostello;

public class ControlloreVisualizzaPrezzo {


    PrezzoBase prezzoBase;
    ComponentePrezzo component;
    int prezzo;

    public ControlloreVisualizzaPrezzo(){

    }



    public void applicaPrezzoBase(Locazione locazione,int numeroGiorni, String tipoPensione){



        if(locazione.getClass()== Albergo.class) {
            if (tipoPensione.equals("completa"))
                this.prezzo =(((Integer.parseInt(locazione.getPrezzo().trim())) +40) * numeroGiorni );
            else {
                this.prezzo = (((Integer.parseInt(locazione.getPrezzo().trim())) + 20 )* numeroGiorni);
            }
        }else if(locazione.getClass()== Ostello.class){
            if (tipoPensione.equals("completa"))
                this.prezzo =(((Integer.parseInt(locazione.getPrezzo().trim())) +20) * numeroGiorni );
            else {
                prezzo = (((Integer.parseInt(locazione.getPrezzo().trim())) + 10 )* numeroGiorni);
            }
        }else {
                this.prezzo= numeroGiorni * (Integer.parseInt(locazione.getPrezzo().trim()));
            }
        this.prezzoBase = new PrezzoBase(prezzo);
        this.component=this.prezzoBase;


    }


   public void applicaServizi(int numeroGiorni, String parcheggio, String wifi, String pet){

       int prezzo_aggiuntivo = 0;

       if(parcheggio.equals("true")) {
           prezzo_aggiuntivo = 8*numeroGiorni;
           DecoratorPrezzoParcheggio dparc = new DecoratorPrezzoParcheggio(this.component, prezzo_aggiuntivo);
           this.component=dparc;
       }
       if(wifi.equals("true")) {
           prezzo_aggiuntivo = 4*numeroGiorni;
           DecoratorPrezzoWifi dwif = new DecoratorPrezzoWifi(this.component, prezzo_aggiuntivo);
           this.component=dwif;
       }
       if(pet.equals("true")) {
           prezzo_aggiuntivo = 12*numeroGiorni;
           DecoratorPrezzoPet dpet= new DecoratorPrezzoPet(this.component, prezzo_aggiuntivo);
           this.component=dpet;
       }


   }

   public int ritornaPrezzoConServizi(){
       return this.component.calcolaPrezzo();
   }

   public int ritornaPrezzoBase(){
        return this.prezzoBase.calcolaPrezzo();

   }






}
