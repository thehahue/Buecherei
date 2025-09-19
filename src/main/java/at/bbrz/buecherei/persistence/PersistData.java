package at.bbrz.buecherei.persistence;

import at.bbrz.buecherei.model.Medium;

import java.util.List;

public interface PersistData {
    void save(List<Medium> data);
    List<Medium> load();
}
