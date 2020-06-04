package com.dolphpire.api.action.user.update.cover;

public class UpdateCoverAction {

    private UpdateCover mUpdateCover;

    public UpdateCoverAction() {
        this.mUpdateCover = new UpdateCover();
    }

    public UpdateCoverAction color1(String color1) {
        this.mUpdateCover.setColor1(color1);
        return this;
    }

    public UpdateCoverAction color2(String color2) {
        this.mUpdateCover.setColor2(color2);
        return this;
    }

    public UpdateCover set() {
        return mUpdateCover;
    }

}
