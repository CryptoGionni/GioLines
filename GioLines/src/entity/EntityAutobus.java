package entity;

public class EntityAutobus {
    
    private int id;
    private int sediliOccupati;
    private int bagagliOccupati;
    private int idCorsa;
    private int sediliMax;
    private int bagagliMax;
    private float dimensioneBagaglio;
    
    public EntityAutobus(int id, int sediliOccupati, int bagagliOccupati, int idCorsa, int sediliMax, int bagagliMax,
            float dimensioneBagaglio) {
        this.id = id;
        this.sediliOccupati = sediliOccupati;
        this.bagagliOccupati = bagagliOccupati;
        this.idCorsa = idCorsa;
        this.sediliMax = sediliMax;
        this.bagagliMax = bagagliMax;
        this.dimensioneBagaglio = dimensioneBagaglio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSediliOccupati() {
        return sediliOccupati;
    }

    public void setSediliOccupati(int sediliOccupati) {
        this.sediliOccupati = sediliOccupati;
    }

    public int getBagagliOccupati() {
        return bagagliOccupati;
    }

    public void setBagagliOccupati(int bagagliOccupati) {
        this.bagagliOccupati = bagagliOccupati;
    }

    public int getIdCorsa() {
        return idCorsa;
    }

    public void setIdCorsa(int idCorsa) {
        this.idCorsa = idCorsa;
    }

    public int getSediliMax() {
        return sediliMax;
    }

    public void setSediliMax(int sediliMax) {
        this.sediliMax = sediliMax;
    }

    public int getBagagliMax() {
        return bagagliMax;
    }

    public void setBagagliMax(int bagagliMax) {
        this.bagagliMax = bagagliMax;
    }

    public float getDimensioneBagaglio() {
        return dimensioneBagaglio;
    }

    public void setDimensioneBagaglio(float dimensioneBagaglio) {
        this.dimensioneBagaglio = dimensioneBagaglio;
    }

    

}
