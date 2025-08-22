package at.bbrz.buecherei.model;

import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

public abstract class Medium {
    private String inventarNummer;
    private String title;
    private Genere genre;
    private boolean ausgeliehen;
    private Zustand zustand;
    private String type;

    protected Medium(String inventarNummer, String title, Genere genre, Zustand zustand, String type) {
        this.inventarNummer = inventarNummer;
        this.title = title;
        this.genre = genre;
        this.zustand = zustand;
        this.ausgeliehen = false;
        this.type = type;
    }

    public void ausleihen() {
        this.ausgeliehen = true;
    }

    public void rueckgabe() {
        this.ausgeliehen = false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("Inventar-Nr: ").append(inventarNummer).append("\n");
        sb.append("Titel: ").append(title).append("\n");
        sb.append("Genre: ").append(genre).append("\n");
        sb.append("Zustand: ").append(zustand).append("\n");
        sb.append("Ausgeliehen: ").append(ausgeliehen ? "Ja" : "Nein").append("\n");
        return sb.toString();
    }

    public String getInventarNummer() {
        return inventarNummer;
    }

    public String getTitle() {
        return title;
    }

    public Genere getGenre() {
        return genre;
    }

    public boolean isAusgeliehen() {
        return ausgeliehen;
    }

    public Zustand getZustand() {
        return zustand;
    }

    public String getType() {
        return type;
    }
}

