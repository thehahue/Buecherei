package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Fsk;
import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class DVD extends SpeicherMedium {
    private String regiseur;
    private Fsk fsk;

    public DVD(String inventarNummer, String title, Genere genre, Zustand zustand, int spielDauer, int teile, String regiseur, Fsk fsk) {
        super(inventarNummer, title, genre, zustand, spielDauer, teile, "DVD");
        this.regiseur = regiseur;
        this.fsk = fsk;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "regiseur='" + regiseur + '\'' +
                ", fsk=" + fsk +
                "} " + super.toString();
    }

    public String getRegiseur() {
        return regiseur;
    }

    public Fsk getFsk() {
        return fsk;
    }
}
