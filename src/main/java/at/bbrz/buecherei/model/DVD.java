package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Fsk;
import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class DVD extends SpeicherMedium {
    private String regiseur;
    private Fsk fsk;

    public DVD(InventarNummer inventarNummer, String title, Genere genre, Zustand zustand, int spielDauer, int teile, String regiseur, Fsk fsk) {
        super(inventarNummer, title, genre, zustand, spielDauer, teile, "DVD");
        this.regiseur = regiseur;
        this.fsk = fsk;
    }

    public DVD() {
    }

    @Override
    public String toString() {
        return super.toString() +
                "Typ: " + getType() + "\n" +
                "Regisseur: " + regiseur + '\n' +
                "FSK: " + fsk + '\n';
    }

    public String getRegiseur() {
        return regiseur;
    }

    public Fsk getFsk() {
        return fsk;
    }
}
