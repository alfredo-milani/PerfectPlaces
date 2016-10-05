package control;

import java.util.ArrayList;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import util.DeserializzaOggetti;
import util.SerializzaOggetti;

// Questa classe serve a rimuovere una locazione dal sistema da parte del proprietario. 

public class ControlloreRimuoviLocazione {
	
	// Percorsi
	
	private String percorsoAlbergo = Constants.ALBERGHI_PATH;
	private String percorsoAppartamento = Constants.APPART_PATH;
	private String percorsoBeb = Constants.BEB;
	private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
	private String percorsoOstello = Constants.OSTELLI_PATH;
	
	// Costruttore
	
	public ControlloreRimuoviLocazione(){
		
	}
	
	// Viene passato un oggetto di tipo locazione da eliminare. Viene effettuata una ricerca a seconda del tipo di locazione all'interno
	// dei rispettivi file e viene eliminato. 
	
	@SuppressWarnings("unchecked")
	public void rimuoviLocazione(Locazione elemento) throws DeserializzazioneException, SerializzazioneException{
		String tipo = elemento.getTipo();
		String nomeLocazione = elemento.getNomeLocazione();
		String indirizzo = elemento.getIndirizzo();
		String userLocatore = elemento.getUserLocatore();
		String prezzo = elemento.getPrezzo();
		String descrizione = elemento.getDescrizione();
		boolean parcheggio = elemento.isParcheggio();
		boolean wifi = elemento.isWifi();
		boolean pet = elemento.isPet();
		ArrayList<Albergo> albergoList = new ArrayList<Albergo>();
		ArrayList<Appartamento> appartamentoList = new ArrayList<Appartamento>();
		ArrayList<Beb> bebList = new ArrayList<Beb>();
		ArrayList<CasaVacanza> casavacanzeList = new ArrayList<CasaVacanza>();
		ArrayList<Ostello> ostelloList = new ArrayList<Ostello>();
		
	
		DeserializzaOggetti dobj = new DeserializzaOggetti();
		SerializzaOggetti sobj = new SerializzaOggetti();
		
		if(tipo.equals("albergo")){
			albergoList = (ArrayList<Albergo>) dobj.deserializza(percorsoAlbergo);
			for(int i=0;i<albergoList.size();i++){
				if(albergoList.get(i).getNomeLocazione().equals(nomeLocazione)&&
						albergoList.get(i).getIndirizzo().equals(indirizzo)&&
						albergoList.get(i).getUserLocatore().equals(userLocatore)&&
						albergoList.get(i).getPrezzo().equals(prezzo)&&
						albergoList.get(i).getDescrizione().equals(descrizione)&&
						albergoList.get(i).isParcheggio()==parcheggio&&
						albergoList.get(i).isWifi()==wifi&&
						albergoList.get(i).isPet()==pet){
							
							albergoList.remove(i);
							sobj.serializza(albergoList, percorsoAlbergo);
							return ;
						}
			}
		}
		
		if(tipo.equals("appartamento")){
			appartamentoList = (ArrayList<Appartamento>) dobj.deserializza(percorsoAppartamento);
			for(int i=0;i<appartamentoList.size();i++){
				if(albergoList.get(i).getNomeLocazione().equals(nomeLocazione)&&
						appartamentoList.get(i).getIndirizzo().equals(indirizzo)&&
						appartamentoList.get(i).getUserLocatore().equals(userLocatore)&&
						appartamentoList.get(i).getPrezzo().equals(prezzo)&&
						appartamentoList.get(i).getDescrizione().equals(descrizione)&&
						appartamentoList.get(i).isParcheggio()==parcheggio&&
						appartamentoList.get(i).isWifi()==wifi&&
						appartamentoList.get(i).isPet()==pet){
							
						appartamentoList.remove(i);
							sobj.serializza(appartamentoList, percorsoAppartamento);
							return ;
						}
			}
		}
		if(tipo.equals("beb")){
			bebList = (ArrayList<Beb>) dobj.deserializza(percorsoBeb);
			for(int i=0;i<bebList.size();i++){
				if(bebList.get(i).getNomeLocazione().equals(nomeLocazione)&&
						bebList.get(i).getIndirizzo().equals(indirizzo)&&
						bebList.get(i).getUserLocatore().equals(userLocatore)&&
						bebList.get(i).getPrezzo().equals(prezzo)&&
						bebList.get(i).getDescrizione().equals(descrizione)&&
						bebList.get(i).isParcheggio()==parcheggio&&
						bebList.get(i).isWifi()==wifi&&
						bebList.get(i).isPet()==pet){
							
						bebList.remove(i);
							sobj.serializza(bebList, percorsoBeb);
							return ;
						}
			}
		}
		if(tipo.equals("casavacanza")){
			casavacanzeList = (ArrayList<CasaVacanza>) dobj.deserializza(percorsoCasaVacanza);
			for(int i=0;i<casavacanzeList.size();i++){
				if(casavacanzeList.get(i).getNomeLocazione().equals(nomeLocazione)&&
						casavacanzeList.get(i).getIndirizzo().equals(indirizzo)&&
						casavacanzeList.get(i).getUserLocatore().equals(userLocatore)&&
						casavacanzeList.get(i).getPrezzo().equals(prezzo)&&
						casavacanzeList.get(i).getDescrizione().equals(descrizione)&&
						casavacanzeList.get(i).isParcheggio()==parcheggio&&
						casavacanzeList.get(i).isWifi()==wifi&&
						casavacanzeList.get(i).isPet()==pet){
							
						casavacanzeList.remove(i);
							sobj.serializza(casavacanzeList, percorsoCasaVacanza);
							return ;
						}
			}
		}
		if(tipo.equals("ostello")){
			ostelloList = (ArrayList<Ostello>) dobj.deserializza(percorsoOstello);
			for(int i=0;i<ostelloList.size();i++){
				if(ostelloList.get(i).getNomeLocazione().equals(nomeLocazione)&&
						ostelloList.get(i).getIndirizzo().equals(indirizzo)&&
						ostelloList.get(i).getUserLocatore().equals(userLocatore)&&
						ostelloList.get(i).getPrezzo().equals(prezzo)&&
						ostelloList.get(i).getDescrizione().equals(descrizione)&&
						ostelloList.get(i).isParcheggio()==parcheggio&&
						ostelloList.get(i).isWifi()==wifi&&
						ostelloList.get(i).isPet()==pet){
							
						ostelloList.remove(i);
							sobj.serializza(ostelloList, percorsoOstello);
							return ;
						}
			}
		}
	}
}
