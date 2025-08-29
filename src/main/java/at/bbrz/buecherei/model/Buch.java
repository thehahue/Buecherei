package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class Buch extends Printmedium {
    private String klappenText;
    private Author author;

    public Buch(String inventarNummer, String title, Genere genre, Zustand zustand, String isbn, int seiten, String klappenText, Author author) {
        super(inventarNummer, title, genre, zustand, isbn, seiten, "Buch");
        this.klappenText = klappenText;
        this.author = author;
    }

    @Override
    public String toString() {
        return super.toString()+ "\n" +
                "Klappentext: " + klappenText + "\n" +
                "Autor: " + author.getName();
    }

    public String getKlappenText() {
        return klappenText;
    }

    public Author getAuthor() {
        return author;
    }
}
