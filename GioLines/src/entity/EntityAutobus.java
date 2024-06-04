package entity;

public class EntityAutobus {
    
    private int sediliOccupati;
    private int bagagliOccupati;
    private int idCorsa;
    private int sediliMax;
    private int bagagliMax;
    private float dimensioneBagaglioH;
    private float dimensioneBagaglioL;
    private float dimensioneBagaglioD;
    
    public EntityAutobus(int idCorsa, int sediliOccupati, int bagagliOccupati, int sediliMax, int bagagliMax,
        float dimensioneBagaglioH, float dimensioneBagaglioL, float dimensioneBagaglioD) {
        this.sediliOccupati = sediliOccupati;
        this.bagagliOccupati = bagagliOccupati;
        this.idCorsa = idCorsa;
        this.sediliMax = sediliMax;
        this.bagagliMax = bagagliMax;
        this.dimensioneBagaglioH = dimensioneBagaglioH;
        this.dimensioneBagaglioL = dimensioneBagaglioL;
        this.dimensioneBagaglioD = dimensioneBagaglioD;
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

    public float getDimensioneBagaglioH() {
        return dimensioneBagaglioH;
    }

    public void setDimensioneBagaglioH(float dimensioneBagaglioH) {
        this.dimensioneBagaglioH = dimensioneBagaglioH;
    }

    public float getDimensioneBagaglioL() {
        return dimensioneBagaglioL;
    }

    public void setDimensioneBagaglioL(float dimensioneBagaglioL) {
        this.dimensioneBagaglioL = dimensioneBagaglioL;
    }

    public float getDimensioneBagaglioD() {
        return dimensioneBagaglioD;
    }

    public void setDimensioneBagaglioD(float dimensioneBagaglioD) {
        this.dimensioneBagaglioD = dimensioneBagaglioD;
    }

    
    

}
