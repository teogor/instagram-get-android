package com.dolphpire.api.action.signup.account;

public class CreateAccountAction {

    private CreateAccount mCreateAccount;

    public CreateAccountAction() {
        this.mCreateAccount = new CreateAccount();
    }

    public CreateAccountAction setName(String name) {
        this.mCreateAccount.setName(name);
        return this;
    }

    public CreateAccountAction setPassword(String password) {
        this.mCreateAccount.setPassword(password);
        return this;
    }

    public CreateAccountAction setImage(String image) {
        this.mCreateAccount.setImage(image);
        return this;
    }

    public CreateAccountAction setGender(String gender) {
        this.mCreateAccount.setGender(gender);
        return this;
    }

    public CreateAccountAction setCountry(String country) {
        this.mCreateAccount.setCountry(country);
        return this;
    }

    public CreateAccountAction setDateOfBirthday(String dateOfBirthday) {
        this.mCreateAccount.setDateOfBirthday(dateOfBirthday);
        return this;
    }

    public CreateAccount set() {
        return mCreateAccount;
    }

}
