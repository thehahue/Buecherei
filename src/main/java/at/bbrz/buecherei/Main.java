package at.bbrz.buecherei;

import at.bbrz.buecherei.model.*;
import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Buch harryPotter1 = new Buch(new InventarNummer(1),
                "Harry Potter und der Stein der Weisen",
                Genere.FANTASY,
                Zustand.NEU,
                "978-3551551672",
                336,
                "Das Haus der Dursleys hatte vier Schlafzimmer: eines für Onkel Vernon und Tante Petunia, eines für Besucher (meist Onkel Vernons Schwester Marge), eines, in dem Dudley schlief, und eines, in dem Dudley all seine Spielsachen und die Dinge aufbewahrte, die nicht mehr in sein erstes Schlafzimmer passten.\" Für Harry Potter, den Cousin Dudleys, bleibt da nicht mehr viel übrig und so bekommt er seinen Platz im alten Schrank unter der Treppe. Harry hat sich schon damit abgefunden, dass er bei Onkel Vernon und Tante Petunia ein ungeliebter Gast ist, doch da seine Eltern schon in seiner frühesten Kindheit bei einem Autounfall gestorben waren, ist er gezwungen, sich mit seinem Schicksal abzufinden. Bis eines Tages, kurz nach seinem elften Geburtstag ein Brief eintrifft: Harry ist in der Hogwarts-Schule für Hexerei und Zauberei aufgenommen. Nicht vergessen soll er bitte seinen Zauberstab und es bleibt ihm freigestellt, ob er eine Eule, eine Katze oder eine Kröte mitbringt. Das Schuljahr beginnt wie jedes Jahr am 1. September und die Fahrt geht mit dem Zug um elf Uhr auf Gleis neundreiviertel los -- das finden wirklich nur die Eingeweihten.",
                new Author("J.K. Rowling")
                );

        System.out.println(harryPotter1);
        ausgabePrintMedium(harryPotter1);

        System.out.println("-------------------------------------------------");

        Buch harryPotter2 = new Buch(new InventarNummer(2),
                "Harry Potter und die Kammer des Schreckens",
                Genere.FANTASY,
                Zustand.LEICHT_GEBRAUCHT,
                "978-3551551689",
                352,
                "Für Harry Potter sind die Sommerferien bei seiner Pflegefamilie, den Dursleys, viel zu lang. Noch nicht mal an seinen Geburtstag haben sie gedacht. Und dass ihre Einladung zum Abendessen völlig chaotisch für ihre Gäste verlief, war wirklich nicht Harrys Schuld. Er hat nicht ein bisschen gezaubert, ehrlich, denn zaubern in den Ferien ist den Schülern der berühmten Schule von Hogwarts streng verboten.\n" +
                        "Harry ist froh, als er von seinem Freund Ron für den Rest der Ferien eingeladen wird. Der Ferienmonat bei Rons Eltern ist herrlich. Mit ihnen zusammen reist Harry zum ersten Mal mittels Flohpulver. Keine schlechte Erfahrung. Doch richtig kritisch wird es, als er zusammen mit Ron am ersten Schultag das Gleis neundreiviertel nicht finden kann. Da müssen die beiden kurz den Wagen von Rons Vater ausleihen und sich seines Zaubers bedienen.",
                new Author("J.K. Rowling")
        );

        System.out.println(harryPotter2);
        ausgabePrintMedium(harryPotter2);

        System.out.println("-------------------------------------------------");

        Magazin javaMagzin = new Magazin(new InventarNummer(3),
                "Java Magazin 01/2025",
                Genere.IT,
                Zustand.NEU,
                "978-35515533333",
                82,
                "Medien Gmbh");

        System.out.println(javaMagzin);
        ausgabePrintMedium(javaMagzin);

        List<Medium> magazins = new ArrayList<>();
        magazins.add(javaMagzin);
        magazins.add(harryPotter1);
        magazins.add(harryPotter2);


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String magazinJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(javaMagzin);
            System.out.println(magazinJsonString);

            String buchString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(harryPotter1);
            System.out.println(buchString);

            String magazinesJsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(magazins);

            System.out.println(magazinesJsonString);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

/*    private static void ausgabeBuch(Buch buch) {
        System.out.println("Name: " + buch.getTitle());
        System.out.println("Anzahl der Seiten: " + buch.getSeiten());
        System.out.println("Genere: " + buch.getGenre());
        System.out.println("Zustand: " +  buch.getZustand());
    }

    private static void ausgabeMagazin(Magazin magazin) {
        System.out.println("Name: " + magazin.getTitle());
        System.out.println("Anzahl der Seiten: " + magazin.getSeiten());
        System.out.println("Genere: " + magazin.getGenre());
        System.out.println("Zustand: " +  magazin.getZustand());
    }

 */

    private static void ausgabePrintMedium(Printmedium printmedium) {
        System.out.println("Name: " + printmedium.getTitle());
        System.out.println("Anzahl der Seiten: " + printmedium.getSeiten());
        System.out.println("Genere: " + printmedium.getGenre());
        System.out.println("Zustand: " +  printmedium.getZustand());
        System.out.println("Wer bin ich: " + printmedium.werBinIch());
    }

}
