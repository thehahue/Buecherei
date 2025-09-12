package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.*;
import at.bbrz.buecherei.model.enums.*;
import org.junit.jupiter.api.*;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SaveDataFileTest {

    private File tempFile;
    private SaveDataFile saveDataFile;

    @BeforeEach
    void setup() throws Exception {
        tempFile = Files.createTempFile("test-savedData", ".json").toFile();
        tempFile.deleteOnExit();
        saveDataFile = new SaveDataFile(tempFile);
    }

    private List<Medium> createSampleData() {
        List<Medium> data = new ArrayList<>();

        Buch buch = new Buch("B001", "Der Herr der Ringe",
                Genere.HORROR, Zustand.LEICHT_GEBRAUCHT,
                "978-3608939812", 1216,
                "Ein episches Fantasy-Abenteuer",
                new Author("J.R.R. Tolkien"));

        DVD dvd = new DVD("D001", "Inception",
                Genere.SCIFI, Zustand.NEU,
                148, 1,
                "Christopher Nolan", Fsk.FSK_12);

        Magazin javaMagazin = new Magazin("000003",
                "Java Magazin 01/2025",
                Genere.IT, Zustand.NEU,
                "978-35515533333", 82,
                "Medien Gmbh");

        Schallplatte schallplatte = new Schallplatte("S0001",
                "Four dimensions", Genere.CLASSIC,
                Zustand.NEU, 90, 1, 12);

        data.add(buch);
        data.add(dvd);
        data.add(javaMagazin);
        data.add(schallplatte);

        return data;
    }

    @Test
    void testSaveAndLoadData() {
        List<Medium> original = createSampleData();

        saveDataFile.saveData(original);

        List<Medium> loaded = saveDataFile.loadData();

        assertNotNull(loaded);
        assertEquals(original.size(), loaded.size(), "Size should match");

        assertEquals(original.get(0).getTitle(), loaded.get(0).getTitle());
    }

    @Test
    void testLoadEmptyFileReturnsEmptyList() throws Exception {
        Files.writeString(tempFile.toPath(), "");

        List<Medium> result = saveDataFile.loadData();
        assertTrue(result.isEmpty(), "Expected empty list for empty file");
    }
}
