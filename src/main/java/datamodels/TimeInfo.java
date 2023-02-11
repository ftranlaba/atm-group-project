package datamodels;

import java.sql.Timestamp;
import java.util.Objects;

public abstract class TimeInfo extends ForeignKeyInfo{
    private Timestamp time;

    public TimeInfo(int id, int idForeignKey, Timestamp time) {
        super(id, idForeignKey);
        this.time = time;
    }

    public TimeInfo(int idForeignKey, Timestamp time) {
        super(idForeignKey);
        this.time = time;
    }

    public TimeInfo() {

    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeInfo timeInfo = (TimeInfo) o;
        return time.equals(timeInfo.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), time);
    }

    @Override
    public String toString() {
        return "TimeInfo{" +
                "time=" + time +
                ", idForeignKey=" + this.getIdForeignKey() +
                ", id=" + this.getId() +
                '}';
    }
}
