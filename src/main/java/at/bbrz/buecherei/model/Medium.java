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
        final StringBuffer sb = new StringBuffer("Medium{");
        sb.append("inventarNummer='").append(inventarNummer).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", genre=").append(genre);
        sb.append(", ausgeliehen=").append(ausgeliehen);
        sb.append(", zustand=").append(zustand);
        sb.append('}');
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

