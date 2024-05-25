package entity;

import java.util.Date;

public class EntityBiglietto {
    
    private int numeroBiglietto;
    private int idImpiegato;
    private Date oraEmissione;
    private Date dataEmissione;
    private boolean presenzaBagaglio;
    
    public EntityBiglietto(int numeroBiglietto, int idImpiegato, Date oraEmissione, Date dataEmissione,
            boolean presenzaBagaglio) {
        this.numeroBiglietto = numeroBiglietto;
        this.idImpiegato = idImpiegato;
        this.oraEmissione = oraEmissione;
        this.dataEmissione = dataEmissione;
        this.presenzaBagaglio = presenzaBagaglio;
    }

    public int getNumeroBiglietto() {
        return numeroBiglietto;
    }

    public void setNumeroBiglietto(int numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }

    public int getIdImpiegato() {
        return idImpiegato;
    }

    public void setIdImpiegato(int idImpiegato) {
        this.idImpiegato = idImpiegato;
    }

    public Date getOraEmissione() {
        return oraEmissione;
    }

    public void setOraEmissione(Date oraEmissione) {
        this.oraEmissione = oraEmissione;
    }

    public Date getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(Date dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public boolean isPresenzaBagaglio() {
        return presenzaBagaglio;
    }

    public void setPresenzaBagaglio(boolean presenzaBagaglio) {
        this.presenzaBagaglio = presenzaBagaglio;
    }

    
}
