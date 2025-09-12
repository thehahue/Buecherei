package at.bbrz.buecherei.util;

import at.bbrz.buecherei.model.InventarNummer;
import at.bbrz.buecherei.model.Medium;

import java.util.Comparator;
import java.util.List;

public class InventarNummerFactory {
    private List<Medium> medienListe;

    public InventarNummerFactory(List<Medium> medienListe) {
        this.medienListe = medienListe;
    }

    public InventarNummer findNextInventarNummer() {
        if (medienListe.isEmpty()) {
            return new InventarNummer(1);
        }

        return new InventarNummer(medienListe.stream()
                .max(Comparator.comparingInt(m -> m.getInventarNummer().getInventarNr()))
                .get()
                .getInventarNummer()
                .getInventarNr()+1);
    }
}
