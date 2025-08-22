package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public abstract class Printmedium extends Medium {
    private String isbn;
    private int seiten;

    public Printmedium(String inventarNummer, String title, Genere genre, Zustand zustand, String isbn, int seiten, String type) {
        super(inventarNummer, title, genre, zustand, type);
        this.isbn = isbn;
        this.seiten = seiten;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+
                "ISBN: " + isbn + "\n" +
                "Seiten: " + seiten + "\n"+
                "Type: " + getType();
    }

    public String getIsbn() {
        return isbn;
    }

    public int getSeiten() {
        return seiten;
    }

    public String werBinIch() {
        return "Printmedium";
    }
}
