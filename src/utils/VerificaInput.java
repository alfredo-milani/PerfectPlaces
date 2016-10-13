package utils;

// Questa classe � utilizzata per effettuare alcuni controlli sugli input dei form nelle pagine JSP.

public class VerificaInput {
	
	// Costruttore

	public VerificaInput(){
		
	}
	
	// Metodo a cui viene passata una stringa con una data in formato gg/mm/aaaa, viene controllato che i parametri non eccedano
	// da giorni, mesi e anni reali (il limite degli anni � dal 2016 al 2056)
	
	public boolean verificaGiorno(String data){
		int giorno;
		int mese;
		int anno;
		
		if(data.length()!=10){
			return false;
		}
		
		try{
			giorno = Integer.parseInt(data.substring(0,2));
			mese = Integer.parseInt(data.substring(3,5));
			anno = Integer.parseInt(data.substring(6,10));
		}catch(Exception e){
			return false;
		}
		if(anno>2056||anno<2016){
			return false;
		}
		if(mese>12||mese<1){
			return false;
		}
		if(mese==4||mese==6||mese==9||mese==11){
			if(giorno<1||giorno>30){
				return false;
			}
		}
		if(mese==2){
			if(giorno<1||giorno>28){
				return false;
			}
		if(giorno<1||giorno>31)
			return false;
		}
		return true;
	}
	
	// Metodo che controlla se il prezzo indicato in un form JSP sia effettivamente un numero intero, che non sia negativo e che non sia 
	// superiore a 1'000'000.
	
	public boolean verificaPrezzo(String prezzo){
		int prezzoInt;
		
		try{
			prezzoInt = Integer.parseInt(prezzo);
		} catch(Exception e){
			return false;
		}
		if(prezzoInt<=0||prezzoInt>1000000){
			return false;
		}
		
		return true;
	}
	
}
