package at.bbrz.buecherei;

import at.bbrz.buecherei.model.Author;
import at.bbrz.buecherei.model.Buch;
import at.bbrz.buecherei.model.InventarNummer;
import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JacksonTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Buch buch = new Buch(
                new InventarNummer(1),
                "Der Herr der Ringe",
                Genere.HORROR,
                Zustand.LEICHT_GEBRAUCHT,
                "978-3608939812",
                1216,
                "Ein episches Fantasy-Abenteuer",
                new Author("J.R.R. Tolkien")
        );

        String value = mapper.writeValueAsString(buch);

        System.out.println(value);

        value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(buch);

        System.out.println(value);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("test.json"), buch);

        List<Buch> buecher = new ArrayList<>();
        buecher.add(buch);
        buecher.add(buch);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("testBuecher.json"), buecher);

        Apfel apfel = new Apfel(1,"Granny Smith", 1.09f);
        Banane banane = new Banane(2,17, true, "Chiquita");
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("apfel.json"), apfel);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("banane.json"), banane);

        Obstsalat obstsalat = new Obstsalat();
        obstsalat.addObst(apfel);
        obstsalat.addObst(banane);

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("obstsalat.json"), obstsalat);

    }

    private static class Obst {
        private int menge;

        public Obst(int menge) {
            this.menge = menge;
        }

        public int getMenge() {
            return menge;
        }
    }

    private static class Apfel extends Obst {
        private String bezeichnung;
        private float preis;

        public Apfel(int menge, String bezeichnung, float preis) {
            super(menge);
            this.bezeichnung = bezeichnung;
            this.preis = preis;
        }

        public String getBezeichnung() {
            return bezeichnung;
        }

        public float getPreis() {
            return preis;
        }

        public String getAlles() {
            return bezeichnung+" kostet "+preis;
        }
    }

    private static class Banane extends Obst {
        private int kruemmungsGrad;
        private boolean reif;
        private String sorte;

        public Banane(int menge, int kruemmungsGrad, boolean reif, String sorte) {
            super(menge);
            this.kruemmungsGrad = kruemmungsGrad;
            this.reif = reif;
            this.sorte = sorte;
        }

        public int getKruemmungsGrad() {
            return kruemmungsGrad;
        }

        public boolean isReif() {
            return reif;
        }

        public String getSorte() {
            return sorte;
        }

        public String getText() {
            return "Und sie ist "+(isReif() ? "Reif":"Unreif");
        }
    }

    private static class Obstsalat {
        private List<Obst> obstsalat = new ArrayList<>();

        public void addObst(Obst obst) {
            obstsalat.add(obst);
        }

        public List<Obst> getObstsalat() {
            return obstsalat;
        }
    }
}
