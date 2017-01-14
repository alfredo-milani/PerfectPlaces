package entity;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Prenotazione implements Serializable{
    private String nomeLocazione;
    private String proprietario;
    private String cliente;
    private GregorianCalendar dataInizio;
    private GregorianCalendar dataFine;
    private String tipo;
    private String prezzo;
    private String numeroPersone;


    public Prenotazione(String nomeLocazione, String proprietario, String cliente, GregorianCalendar dataInizio, GregorianCalendar dataFine, String tipo, String prezzo, String numeroPersone){
        this.nomeLocazione= nomeLocazione;
        this.proprietario = proprietario;
        this.cliente = cliente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipo=tipo;
        this.prezzo=prezzo;
        this.numeroPersone=numeroPersone;
    }

    public String getNomeLocazione() {
        return nomeLocazione;
    }

    public void setNomeLocazione(String nomeLocazione) {
        this.nomeLocazione = nomeLocazione;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getCliente() {
        return cliente;
    }

    public GregorianCalendar getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(GregorianCalendar dataInizio) {
        this.dataInizio = dataInizio;
    }

    public GregorianCalendar getDataFine() {
        return dataFine;
    }

    public void setDataFine(GregorianCalendar dataFine) {
        this.dataFine = dataFine;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(String numeroPersone) {
        this.numeroPersone = numeroPersone;
    }
}
