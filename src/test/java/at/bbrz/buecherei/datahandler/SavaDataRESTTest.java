package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class SavaDataRESTTest {

    @Test
    void testLoadData() throws Exception {
        // Arrange
        String jsonResponse = """
                [ {
                  "title" : "Der Herr der Ringe",
                  "genre" : "HORROR",
                  "ausgeliehen" : false,
                  "zustand" : "LEICHT_GEBRAUCHT",
                  "type" : "Buch",
                  "isbn" : "978-3608939812",
                  "seiten" : 1216,
                  "klappenText" : "Ein episches Fantasy-Abenteuer",
                  "author" : {
                    "name" : "J.R.R. Tolkien"
                  },
                  "inventarNr" : "B001"
                }, {
                  "title" : "Inception",
                  "genre" : "SCIFI",
                  "ausgeliehen" : false,
                  "zustand" : "NEU",
                  "type" : "DVD",
                  "spielDauer" : 148,
                  "teile" : 1,
                  "regiseur" : "Christopher Nolan",
                  "fsk" : "FSK_12",
                  "inventarNr" : "D001"
                }, {
                  "title" : "Java Magazin 01/2025",
                  "genre" : "IT",
                  "ausgeliehen" : false,
                  "zustand" : "NEU",
                  "type" : "Magazin",
                  "isbn" : "978-35515533333",
                  "seiten" : 82,
                  "redaktion" : "Medien Gmbh",
                  "inventarNr" : "000003"
                }, {
                  "title" : "Four dimensions",
                  "genre" : "CLASSIC",
                  "ausgeliehen" : false,
                  "zustand" : "NEU",
                  "type" : "Schallplatte",
                  "spielDauer" : 90,
                  "teile" : 1,
                  "inventarNr" : "S0001"
                } ]
                """;

        @SuppressWarnings("unchecked")
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockResponse.body()).thenReturn(jsonResponse);

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        Mockito.when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        SavaDataREST rest = new SavaDataREST(new URI("http://fake-server/api/media"), mockClient);

        // Act
        List<Medium> result = rest.loadData();

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("Der Herr der Ringe", result.get(0).getTitle());
    }

    @Test
    void testSaveData() throws Exception {
        // Arrange
        @SuppressWarnings("unchecked")
        HttpResponse<String> mockResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(mockResponse.body()).thenReturn("");

        HttpClient mockClient = Mockito.mock(HttpClient.class);
        Mockito.when(mockClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        SavaDataREST rest = new SavaDataREST(new URI("http://fake-server/api/media"), mockClient);

        // Act (just ensure no exception)
        rest.saveData(List.of(new Buch("B001", "Der Herr der Ringe", null, null,
                "978-3608939812", 1216, "desc", new Author("Tolkien"))));

        // Assert
        Mockito.verify(mockClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}
