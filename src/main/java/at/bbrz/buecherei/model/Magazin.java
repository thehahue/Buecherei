package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class Magazin extends Printmedium {
    private String redaktion;

    public Magazin(InventarNummer inventarNummer, String title, Genere genre, Zustand zustand, String isbn, int seiten, String redaktion) {
        super(inventarNummer, title, genre, zustand, isbn, seiten, "Magazin");
        this.redaktion = redaktion;
    }

    public Magazin() {
    }

    public String getRedaktion() {
        return redaktion;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Redaktion: " + redaktion + "\n";
    }
}
