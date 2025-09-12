package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.*;
import at.bbrz.buecherei.model.enums.Fsk;
import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IDHandlerTest {

    private IDHandler idHandler = new IDHandler();

    @Test
    public void assignIdsWithoutIds() {
        List<Medium> data = createSampleData();
        List<Medium> returnedList = idHandler.assignIds(data);
        assertEquals(returnedList.size(), returnedList.getLast().getId());
    }

    @Test
    public void assignIdsWithGivenIds() {
        Long highestPresentID = 5L;
        int numOfAssignedIDs = 2;
        List<Medium> data = createSampleDataAndGiveTwoMediaIds(highestPresentID);
        List<Medium> returnedList = idHandler.assignIds(data);
        assertEquals(returnedList.size() + highestPresentID - numOfAssignedIDs, returnedList.stream()
                .mapToLong(Medium::getId)
                .max()
                .getAsLong());
    }

    @Test
    public void assignIdsWithAllGivenIds() {
        List<Medium> data = createSampleDataAndGiveAllMediaAscendingIDs();
        List<Medium> returnedList = idHandler.assignIds(data);
        assertEquals(returnedList.size(), returnedList.getLast().getId());
    }

    private List<Medium> createSampleDataAndGiveTwoMediaIds(Long highestPresentID) {
        List<Medium> sampleData = createSampleData();
        Medium medium = sampleData.get(1);
        medium.setId(highestPresentID);
        Medium medium1 = sampleData.get(3);
        medium1.setId(2L);
        return sampleData;
    }

    private List<Medium> createSampleDataAndGiveAllMediaAscendingIDs() {
        List<Medium> data = createSampleData();
        Long idCounter = 1L;
        for (Medium m : data) {
            m.setId(idCounter++);
        }
        return data;
    }


    private List<Medium> createSampleData() {
        List<Medium> data = new ArrayList<>();
        Buch buch = new Buch(
                "B001",
                "Der Herr der Ringe",
                Genere.HORROR,
                Zustand.LEICHT_GEBRAUCHT,
                "978-3608939812",
                1216,
                "Ein episches Fantasy-Abenteuer",
                new Author("J.R.R. Tolkien")
        );

        DVD dvd = new DVD(
                "D001",
                "Inception",
                Genere.SCIFI,
                Zustand.NEU,
                148,
                1,
                "Christopher Nolan",
                Fsk.FSK_12
        );

        Magazin javaMagzin = new Magazin("000003",
                "Java Magazin 01/2025",
                Genere.IT,
                Zustand.NEU,
                "978-35515533333",
                82,
                "Medien Gmbh");

        Schallplatte schallplatte = new Schallplatte("S0001",
                "Four dimensions",
                Genere.CLASSIC,
                Zustand.NEU,
                90,
                1,
                12);

        data.add(buch);
        data.add(dvd);
        data.add(javaMagzin);
        data.add(schallplatte);
        return data;
    }

}