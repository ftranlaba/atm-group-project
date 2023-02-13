package datamodels.builders;

import datamodels.IdInfo;
import datamodels.User;

public class UserBuilder extends IdInfo {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    public UserBuilder(int id) {
        super(id);
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(getId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        return user;
    }
}
