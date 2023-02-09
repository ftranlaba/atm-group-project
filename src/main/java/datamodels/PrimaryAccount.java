package datamodels;

public class PrimaryAccount extends IdInfo {
    private int idUser;
    private int pin;

    public PrimaryAccount(int id, int idUser, int pin) {
        super(id);
        this.idUser = idUser;
        this.pin = pin;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getPin() {
        return this.pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "PrimaryAccount{" +
                "idUser=" + idUser +
                ", pin=" + pin +
                ", id=" + id +
                '}';
    }
}
