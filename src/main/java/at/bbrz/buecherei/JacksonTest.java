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

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("apfel.json"), new Apfel("Granny Smith", 1.09f));
    }

    private static class Apfel {
        private String bezeichnung;
        private float preis;

        public Apfel(String bezeichnung, float preis) {
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
}
