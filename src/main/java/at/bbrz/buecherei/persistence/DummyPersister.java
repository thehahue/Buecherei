package at.bbrz.buecherei.persistence;

import at.bbrz.buecherei.model.Medium;

import java.util.List;

public class DummyPersister implements PersistData {
    private List<Medium> data;

    @Override
    public void save(List<Medium> data) {
        this.data = data;
        System.out.println("Daten gespeichert!");
    }

    @Override
    public List<Medium> load() {
        System.out.println("Daten geladen!");
        return data;
    }
}
