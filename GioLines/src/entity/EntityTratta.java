package entity;

public class EntityTratta {

    private int idCorsa;
    private String cittàPartenza;
    private String cittàArrivo;
    
    public EntityTratta( int idCorsa, String cittàPartenza, String cittàArrivo) {
        this.idCorsa = idCorsa;
        this.cittàPartenza = cittàPartenza;
        this.cittàArrivo = cittàArrivo;
    }

    public int getIdCorsa() {
        return idCorsa;
    }

    public void setIdCorsa(int idCorsa) {
        this.idCorsa = idCorsa;
    }

    public String getCittàPartenza() {
        return cittàPartenza;
    }

    public void setCittàPartenza(String cittàPartenza) {
        this.cittàPartenza = cittàPartenza;
    }

    public String getCittàArrivo() {
        return cittàArrivo;
    }

    public void setCittàArrivo(String cittàArrivo) {
        this.cittàArrivo = cittàArrivo;
    }

    
}
