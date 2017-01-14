package control;

import constants.Constants;
import entity.*;
import exception.DeserializzazioneException;
import exception.SerializzazioneException;
import utils.DeserializzaOggetti;
import utils.SerializzaOggetti;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by maria on 16/12/16.
 */
public class ControlloreInserimentoLocazione {

    private ArrayList<Appartamento> appartamentoList = new ArrayList<Appartamento>();
    private ArrayList<Beb> bebList = new ArrayList<Beb>();
    private ArrayList<Albergo> albergoList = new ArrayList<Albergo>();
    private ArrayList<CasaVacanza> vacanzaList = new ArrayList<CasaVacanza>();
    private ArrayList<Ostello> ostelloList = new ArrayList<Ostello>();
    private String percorsoAlbergo = Constants.ALBERGHI_PATH;
    private String percorsoAppartamento = Constants.APPART_PATH;
    private String percorsoBeb = Constants.BEB;
    private String percorsoCasaVacanza = Constants.CASEVACANZA_PATH;
    private String percorsoOstello = Constants.OSTELLI_PATH;

    private String command;
    private String path;


    public ControlloreInserimentoLocazione(String command) {
        this.command = command;
    }

    public Locazione inserisciLocazione(String nomeLocazione, String postiTotali, String provincia, String indirizzo, String userLocatore, String prezzo,
                                        String descrizione, boolean parcheggio, boolean wifi, boolean pet) throws Exception {



        FactoryInserimentoLocazione factory = FactoryInserimentoLocazione.getFactoryInstance();
        factory.changeSettings(nomeLocazione, postiTotali, provincia, indirizzo, userLocatore, prezzo, descrizione, parcheggio, wifi, pet);
        return factory.createGenericLocation(command);


    }

    public void selezionaPercorso(){
        switch (command){
            case "0": {
                path = percorsoAlbergo;
                break;
            }
            case "1": {
                path = percorsoAppartamento;
                break;
            }
            case "2": {
                path = percorsoBeb;
                break;
            }
            case "3": {
                path = percorsoCasaVacanza;
                break;
            }
            case "4": {
                path=percorsoOstello;
                break;
            }
        }
    }

    public void serializzaLocazione(Locazione locazione) throws SerializzazioneException, DeserializzazioneException {

        File file = new File(path);
        SerializzaOggetti sobj = new SerializzaOggetti();
        DeserializzaOggetti dobj = new DeserializzaOggetti();


        switch (command) {
            case "0": {

                Albergo albergo=(Albergo)locazione;
                if (file.length() == 0) {
                    albergoList.clear();
                    albergoList.add(albergo);
                    sobj.serializza(albergoList, path);
                    break;
                }

                albergoList = (ArrayList<Albergo>) dobj.deserializza(path);
                albergoList.add(albergo);
                sobj.serializza(albergoList, percorsoAlbergo);
                break;
            }
            case "1": {

                Appartamento appartamento=(Appartamento) locazione;
                if (file.length() == 0) {
                    appartamentoList.clear();
                    appartamentoList.add(appartamento);
                    sobj.serializza(appartamentoList, path);
                    break;
                }

                appartamentoList = (ArrayList<Appartamento>) dobj.deserializza(path);
                appartamentoList.add(appartamento);
                sobj.serializza(appartamentoList, path);
                break;
            }
            case "2": {

                Beb beb=(Beb) locazione;
                if (file.length() == 0) {
                    bebList.clear();
                    bebList.add(beb);
                    sobj.serializza(bebList, path);
                    break;
                }

                bebList = (ArrayList<Beb>) dobj.deserializza(path);
                bebList.add(beb);
                sobj.serializza(bebList, path);
                break;
            }
            case "3": {

                CasaVacanza casaVacanza=(CasaVacanza) locazione;
                if (file.length() == 0) {
                    vacanzaList.clear();
                    vacanzaList.add(casaVacanza);
                    sobj.serializza(vacanzaList, path);
                    break;
                }

                vacanzaList = (ArrayList<CasaVacanza>) dobj.deserializza(path);
                vacanzaList.add(casaVacanza);
                sobj.serializza(vacanzaList, path);
                break;
            }
            case "4": {

                Ostello ostello=(Ostello) locazione;
                if (file.length() == 0) {
                    ostelloList.clear();
                    ostelloList.add(ostello);
                    sobj.serializza(ostelloList, path);
                    break;
                }

                ostelloList = (ArrayList<Ostello>) dobj.deserializza(path);
                ostelloList.add(ostello);
                sobj.serializza(ostelloList, path);
                break;
            }

        }

    }
}
