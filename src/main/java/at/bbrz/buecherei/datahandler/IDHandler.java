package at.bbrz.buecherei.datahandler;

import at.bbrz.buecherei.model.Medium;

import java.util.List;

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
        Long currentHighestId = 0L;
        for (Medium m : data) {
            Long id = m.getId();
            if (id == null) continue;
            if (id > currentHighestId) currentHighestId = id;
        }
        return currentHighestId;
    }

}
