package datamodels;

public abstract class ForeignKeyInfo extends IdInfo{

    protected int idForeignKey;

    public ForeignKeyInfo(int id, int idForeignKey) {
        super(id);
        this.idForeignKey = idForeignKey;
    }

    @Override
    public String toString() {
        return "ForeignKeyInfo{" +
                "idForeignKey=" + idForeignKey +
                ", id=" + id +
                '}';
    }
}
