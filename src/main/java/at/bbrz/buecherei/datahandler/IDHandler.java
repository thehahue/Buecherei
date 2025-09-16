package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.Medium;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class IDHandler {

    public IDHandler() {
    }

    public List<Medium> assignIds(List<Medium> data) {
        Long nextFreeID = getcurrentHighestID(data) + 1;
        for (Medium m : data) {
            Long id = m.getId();
            if (id == null) {
                m.setId(nextFreeID++);
            }
        }
        return data;
    }

    private Long getcurrentHighestID(List<Medium> data) {
        return data.stream()
                .map(Medium::getId)
                .filter(Objects::nonNull)
                .max(Long::compareTo)
                .orElse(0L);
    }

}
