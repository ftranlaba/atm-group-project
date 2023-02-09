package datamodels;

import java.sql.Timestamp;

public abstract class TimeInfo extends ForeignKeyInfo{
    protected Timestamp time;

    public TimeInfo(int id, int idForeignKey, Timestamp time) {
        super(id, idForeignKey);
        this.time = time;
    }

    @Override
    public String toString() {
        return "TimeInfo{" +
                "time=" + time +
                ", idForeignKey=" + idForeignKey +
                ", id=" + id +
                '}';
    }
}
