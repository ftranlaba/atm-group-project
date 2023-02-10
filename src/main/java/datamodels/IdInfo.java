package datamodels;

import java.util.Objects;

public abstract class IdInfo {
    private int id;

    public IdInfo(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdInfo idInfo = (IdInfo) o;
        return id == idInfo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "IdInfo{" +
                "id=" + id +
                '}';
    }
}
