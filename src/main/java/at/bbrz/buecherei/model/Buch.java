package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class Buch extends Printmedium {
    private String klappenText;
    private Author author;

    public Buch(String inventarNummer, String title, Genere genre, Zustand zustand, String isbn, int seiten, String klappenText, Author author) {
        super(inventarNummer, title, genre, zustand, isbn, seiten);
        this.klappenText = klappenText;
        this.author = author;
    }

    public String werBinIch() {
        return "Buch";
    }

    @Override
    public String toString() {
        return "Buch{\n" +
                "klappenText='" + klappenText + "\'\n" +
                "author=" + author + "\n"+
                "} \n" + super.toString();
    }
}
