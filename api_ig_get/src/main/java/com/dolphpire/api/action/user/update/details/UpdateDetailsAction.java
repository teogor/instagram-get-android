package com.dolphpire.api.action.user.update.details;

public class UpdateDetailsAction {

    private UpdateDetails mUpdateDetails;

    public UpdateDetailsAction() {
        this.mUpdateDetails = new UpdateDetails();
    }

    public UpdateDetailsAction setName(String name) {
        this.mUpdateDetails.setName(name.equals("") ? "null" : name);
        return this;
    }

    public UpdateDetailsAction setCountry(String country) {
        this.mUpdateDetails.setCountry(country.equals("") ? "null" : country);
        return this;
    }

    public UpdateDetailsAction setGender(String gender) {
        this.mUpdateDetails.setGender(gender.equals("") ? "null" : gender);
        return this;
    }

    public UpdateDetailsAction setBirthday(String birthday) {
        this.mUpdateDetails.setBirthday(birthday.equals("") ? "null" : birthday);
        return this;
    }

    public UpdateDetailsAction setWebsite(String website) {
        this.mUpdateDetails.setWebsite(website.equals("") ? "null" : website);
        return this;
    }

    public UpdateDetailsAction setStatus(String status) {
        this.mUpdateDetails.setStatus(status.equals("") ? "null" : status);
        return this;
    }

    public UpdateDetailsAction setBio(String bio) {
        this.mUpdateDetails.setBio(bio.equals("") ? "null" : bio);
        return this;
    }

    public UpdateDetailsAction setBusinessEmail(String businessEmail) {
        this.mUpdateDetails.setBusinessEmail(businessEmail.equals("") ? "null" : businessEmail);
        return this;
    }

    public UpdateDetailsAction setBusinessPhone(String businessPhone) {
        this.mUpdateDetails.setBusinessPhone(businessPhone.equals("") ? "null" : businessPhone);
        return this;
    }

    public UpdateDetailsAction setBusinessAddress(String businessAddress) {
        this.mUpdateDetails.setBusinessAddress(businessAddress.equals("") ? "null" : businessAddress);
        return this;
    }

    public UpdateDetailsAction setBusinessPostalCode(String businessPostalCode) {
        this.mUpdateDetails.setBusinessPostalCode(businessPostalCode.equals("") ? "null" : businessPostalCode);
        return this;
    }

    public UpdateDetailsAction setBusinessLocation(String businessLocation) {
        this.mUpdateDetails.setBusinessLocation(businessLocation.equals("") ? "null" : businessLocation);
        return this;
    }

    public UpdateDetailsAction setBusinessFounded(String businessFounded) {
        this.mUpdateDetails.setBusinessFounded(businessFounded.equals("") ? "null" : businessFounded);
        return this;
    }

    public UpdateDetails set() {
        return mUpdateDetails;
    }

}
