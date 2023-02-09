package datamodels;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "\n[First Name = '" + firstName + '\'' +
                ", Last Name = '" + lastName + '\'' +
                ", Address = '" + address + '\'' +
                ", Phone Number = '" + phoneNumber + '\'' +
                ", ID = " + userId + "]";
    }
}
