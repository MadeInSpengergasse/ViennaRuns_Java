package viennaruns.domain;

import viennaruns.foundation.Ensurer;

import java.time.LocalDateTime;

public abstract class BaseModel<DOMAIN_TYPE extends BaseModel, PK_TYPE extends Number> implements Comparable<DOMAIN_TYPE> {

    private PK_TYPE id;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime deletedAt;

    protected BaseModel() {
        this.version = 0;
    }

    protected BaseModel(final PK_TYPE id, final Integer version) {
        this.id = Ensurer.IsNotNull(id, "id");
        this.version = Ensurer.IsNotNull(version, "version");
    }

    public final PK_TYPE getId() {
        return id;
    }

    public final void setId(PK_TYPE id) {
        this.id = Ensurer.IsNotNull(id, "id");
    }

    public final Integer getVersion() {
        return version;
    }

    public final void setVersion(Integer version) {
        this.version = Ensurer.IsNotNull(version, "version");
    }

    public final Boolean isNew() {
        return id == null;
    }

    public final String toString() {
        return String.format("%s{id:'%s', version:'%d'}", getClass().getSimpleName(),
                id, version);
    }

    protected int _compareTo(BaseModel o) { //for intern usage
        if (o.getId().equals(this.getId()) && o.getVersion().equals(this.getVersion())) return 0;
        else return -1;
    }
}
