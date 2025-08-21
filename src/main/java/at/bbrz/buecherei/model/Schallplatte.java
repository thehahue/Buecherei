package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class Schallplatte extends SpeicherMedium{
    private int anzahlLieder;

    public Schallplatte(String inventarNummer, String title, Genere genre, Zustand zustand, int spielDauer, int teile, int anzahlLieder) {
        super(inventarNummer, title, genre, zustand, spielDauer, teile);
        this.anzahlLieder = anzahlLieder;
    }

    @Override
    public String toString() {
        return "Schallplatte{" +
                "anzahlLieder=" + anzahlLieder +
                "} " + super.toString();
    }

    public int getAnzahlLieder() {
        return anzahlLieder;
    }
}
