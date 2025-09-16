package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public class SpeicherMedium extends Medium {
    private int spielDauer;
    private int teile;

    public SpeicherMedium(InventarNummer inventarNummer, String title, Genere genre, Zustand zustand, int spielDauer, int teile, String type) {
        super(inventarNummer, title, genre, zustand, type);
        this.spielDauer = spielDauer;
        this.teile = teile;
    }

    public SpeicherMedium() {
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nSpieldauer: " + spielDauer +" Minuten\n"+
                "Teile: " + teile+"\n";
    }

    public int getSpielDauer() {
        return spielDauer;
    }

    public int getTeile() {
        return teile;
    }
}
