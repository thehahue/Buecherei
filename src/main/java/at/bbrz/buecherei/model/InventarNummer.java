package at.bbrz.buecherei.model;

public class InventarNummer {
    private int inventarNr;

    public InventarNummer(int inventarNr) {
        this.inventarNr = inventarNr;
    }

    public int getInventarNr() {
        return inventarNr;
    }

    public String getFormatedInventarNummer() {
        return "I"+String.format("%05d", inventarNr);
    }
}
