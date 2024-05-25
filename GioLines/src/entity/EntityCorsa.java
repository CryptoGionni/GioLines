package entity;

import java.util.Date;
public class EntityCorsa {

    private int id;
    private Date dataPartenza;    
    private Date orarioPartenza;
    private Date orarioArrivo;
    private float prezzoBiglietto;

    public EntityCorsa(int id, Date dataPartenza, Date orarioPartenza, Date orarioArrivo, float prezzoBiglietto) {
		super();
		this.id = id;
		this.dataPartenza = dataPartenza;
		this.orarioPartenza = orarioPartenza;
		this.orarioArrivo = orarioArrivo;
		this.prezzoBiglietto = prezzoBiglietto;
	}

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Date getDataPartenza() {
        return dataPartenza;
    }


    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }


    public Date getOrarioPartenza() {
        return orarioPartenza;
    }


    public void setOrarioPartenza(Date orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }


    public Date getOrarioArrivo() {
        return orarioArrivo;
    }


    public void setOrarioArrivo(Date orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }


    public float getPrezzoBiglietto() {
        return prezzoBiglietto;
    }


    public void setPrezzoBiglietto(float prezzoBiglietto) {
        this.prezzoBiglietto = prezzoBiglietto;
    }

}
