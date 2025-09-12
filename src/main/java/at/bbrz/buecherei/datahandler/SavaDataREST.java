package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.Medium;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SavaDataREST implements SaveDataInterface{

    private ObjectMapper objectMapper;
    private HttpClient httpClient;
    private IDHandler idHandler;
    private URI uri;


    public SavaDataREST(URI URI) {
        this.uri = URI;
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
        this.idHandler = new IDHandler();
    }

    @Override
    public void saveData(List<Medium> data) {
        data = idHandler.assignIds(data);
        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data)))
                    .build();
            HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Medium> loadData() {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        try {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(getResponse.body(), new TypeReference<List<Medium>>() {});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
