package datamodels;

public abstract class TypeInfo extends ForeignKeyInfo {
    private String type;

    public TypeInfo(int id, int idForeignKey, String type) {
        super(id, idForeignKey);
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
