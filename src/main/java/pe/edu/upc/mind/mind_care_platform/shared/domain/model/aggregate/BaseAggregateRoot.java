package pe.edu.upc.mind.mind_care_platform.shared.domain.model.aggregate;

import java.io.Serializable;

public abstract class BaseAggregateRoot<ID> implements Serializable {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}