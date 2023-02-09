package datamodels;

public abstract class TypeInfo extends ForeignKeyInfo{
    protected String type;

    public TypeInfo(int id, int idForeignKey, String type) {
        super(id, idForeignKey);
        this.type = type;
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
                ", idForeignKey=" + idForeignKey +
                ", id=" + id +
                '}';
    }
}
