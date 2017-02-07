package control;

import control.Decorator.*;
import entity.Albergo;
import entity.Locazione;
import entity.Ostello;

/**
 * Created by maria
 */

public class ControlloreVisualizzaPrezzo {


    private PrezzoBase prezzoBase;
    private ComponentePrezzo component; //oggetto necessario per applicare le varie decorazioni

    public ControlloreVisualizzaPrezzo(){

    }


    //Metodo che calcola il prezzo base e lo assegna al component, nel caso di ostelli e alberghi
    //al prezzo pernottamento si aggiunge il costo del tipo di pensione e poi si moltiplica il tutto per il numero di giorni
    public void applicaPrezzoBase(Locazione locazione,int numeroGiorni, String tipoPensione){

        int prezzo;

        //Polymorphism
        prezzo= locazione.calcolaPrezzoBase(locazione,numeroGiorni,tipoPensione);

        this.prezzoBase = new PrezzoBase(prezzo);
        this.component=this.prezzoBase;


    }

    // metodo che applica opportunamente le decorazioni richieste
   public void applicaServizi(int numeroGiorni, boolean parcheggio, boolean wifi, boolean pet){

       int prezzo_aggiuntivo = 0;

       if(parcheggio) {
           prezzo_aggiuntivo = 8*numeroGiorni;
           DecoratorPrezzoParcheggio dparc = new DecoratorPrezzoParcheggio(this.component, prezzo_aggiuntivo);
           this.component=dparc;
       }
       if(wifi) {
           prezzo_aggiuntivo = 4*numeroGiorni;
           DecoratorPrezzoWifi dwif = new DecoratorPrezzoWifi(this.component, prezzo_aggiuntivo);
           this.component=dwif;
       }
       if(pet) {
           prezzo_aggiuntivo = 12*numeroGiorni;
           DecoratorPrezzoPet dpet= new DecoratorPrezzoPet(this.component, prezzo_aggiuntivo);
           this.component=dpet;
       }


   }

   //metodo che mostra effettivamente l'applicazione di uno o più decorazioni, stampando il prezzo risultante
   public int ritornaPrezzoConServizi(){
       return this.component.calcolaPrezzo();
   }

   //metodo che ritorna il prezzo base
   public int ritornaPrezzoBase(){
        return this.prezzoBase.calcolaPrezzo();

   }






}
