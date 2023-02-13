package datamodels;

import java.util.Objects;

public abstract class ForeignKeyInfo extends IdInfo {
    private int idForeignKey;

    public ForeignKeyInfo(int id, int idForeignKey) {
        super(id);
        this.idForeignKey = idForeignKey;
    }

    public ForeignKeyInfo(int idForeignKey) {
        this.idForeignKey = idForeignKey;
    }

    public ForeignKeyInfo() {
    }

    public int getIdForeignKey() {
        return idForeignKey;
    }

    public void setIdForeignKey(int idForeignKey) {
        this.idForeignKey = idForeignKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ForeignKeyInfo that = (ForeignKeyInfo) o;
        return idForeignKey == that.idForeignKey;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idForeignKey);
    }

    @Override
    public String toString() {
        return "ForeignKeyInfo{" +
                "idForeignKey=" + idForeignKey +
                ", id=" + this.getId() +
                '}';
    }
}
