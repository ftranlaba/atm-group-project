package datamodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class TypeInfo extends ForeignKeyInfo {
    @JsonProperty
    private String type;

    public TypeInfo(int id, int idForeignKey, String type) {
        super(id, idForeignKey);
        this.type = type;
    }

    public TypeInfo(String type) {
        this.type = type;
    }

    public TypeInfo(int idForeignKey, String type) {
        super(idForeignKey);
        this.type = type;
    }

    public TypeInfo() {

    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeInfo{" +
                "type='" + type + '\'' +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
