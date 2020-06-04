package com.dolphpire.api.action.user.update.visibility;

public class UpdateVisibilityAction {

    private UpdateVisibility mUpdateVisibility;

    public UpdateVisibilityAction() {
        this.mUpdateVisibility = new UpdateVisibility();
    }

    public UpdateVisibilityAction showBirthday(boolean showBirthday) {
        this.mUpdateVisibility.showBirthday(showBirthday);
        return this;
    }

    public UpdateVisibilityAction showBusinessAddress(boolean showBusinessAddress) {
        this.mUpdateVisibility.showBusinessAddress(showBusinessAddress);
        return this;
    }

    public UpdateVisibilityAction showBusinessEmail(boolean showBusinessEmail) {
        this.mUpdateVisibility.showBusinessEmail(showBusinessEmail);
        return this;
    }

    public UpdateVisibilityAction showBusinessPhone(boolean showBusinessPhone) {
        this.mUpdateVisibility.showBusinessPhone(showBusinessPhone);
        return this;
    }

    public UpdateVisibilityAction showBusinessHQ(boolean showBusinessHQ) {
        this.mUpdateVisibility.showBusinessHQ(showBusinessHQ);
        return this;
    }

    public UpdateVisibilityAction showCountry(boolean showCountry) {
        this.mUpdateVisibility.showCountry(showCountry);
        return this;
    }

    public UpdateVisibilityAction showEmail(boolean showEmail) {
        this.mUpdateVisibility.showEmail(showEmail);
        return this;
    }

    public UpdateVisibilityAction showFoundedDate(boolean showFoundedDate) {
        this.mUpdateVisibility.showFoundedDate(showFoundedDate);
        return this;
    }

    public UpdateVisibilityAction showGender(boolean showGender) {
        this.mUpdateVisibility.showGender(showGender);
        return this;
    }

    public UpdateVisibilityAction showJoinedDate(boolean showJoinedDate) {
        this.mUpdateVisibility.showJoinedDate(showJoinedDate);
        return this;
    }

    public UpdateVisibilityAction showPhone(boolean showPhone) {
        this.mUpdateVisibility.showPhone(showPhone);
        return this;
    }

    public UpdateVisibilityAction showUserOnline(boolean showUserOnline) {
        this.mUpdateVisibility.showUserOnline(showUserOnline);
        return this;
    }

    public UpdateVisibilityAction showWebsite(boolean showWebsite) {
        this.mUpdateVisibility.showWebsite(showWebsite);
        return this;
    }

    public UpdateVisibility set() {
        return mUpdateVisibility;
    }

}
