package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.Medium;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaveDataFile implements SaveDataInterface {

    private File file;
    private ObjectMapper objectMapper;
    private IDHandler idHandler;

    public SaveDataFile() {
        this.objectMapper = new ObjectMapper();
        this.file = new File("savedData.json");
        this.idHandler = new IDHandler();
    }


    @Override
    public void saveData(List<Medium> data) {
        data = idHandler.assignIds(data);
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Medium> loadData() {
        if (!file.exists() || file.length() == 0) {
            return List.of();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Medium>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
