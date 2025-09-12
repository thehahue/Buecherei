package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class Schallplatte extends SpeicherMedium{
    private int anzahlLieder;

    public Schallplatte(String inventarNummer, String title, Genere genre, Zustand zustand, int spielDauer, int teile, int anzahlLieder) {
        super(inventarNummer, title, genre, zustand, spielDauer, teile, "Schallplatte");
        this.anzahlLieder = anzahlLieder;
    }

    public Schallplatte() {
    }

    @Override
    public String toString() {
        return super.toString()+
                "Typ: " + getType() + "\n"+
                "Anzahl Lieder: " + anzahlLieder;
    }
}
