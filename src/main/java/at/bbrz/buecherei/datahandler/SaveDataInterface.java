package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.Medium;

import java.util.List;

public interface SaveDataInterface {
    void saveData(List<Medium> data);
    List<Medium> loadData();
}
