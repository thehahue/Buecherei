package at.bbrz.buecherei.persistence;

import at.bbrz.buecherei.model.Medium;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFilePersister implements PersistData {
    private File file;
    private ObjectMapper objectMapper;
    private JFrame frame;

    public JsonFilePersister(JFrame frame) {
        this.frame = frame;
        objectMapper = new ObjectMapper();
        file = new File("mediums.json");
    }

    @Override
    public void save(List<Medium> data) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (JsonProcessingException e) {
            JOptionPane.showMessageDialog(frame, "Json konnte nicht erstellt werden.",
                    "Hinweis", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Medien konnten nicht gespeichert werden.",
                    "Hinweis", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public List<Medium> load() {
        return List.of();
    }
}
