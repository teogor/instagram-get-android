package com.dolphpire.api.action.user.update.featured;

public class UpdateFeaturedProfilesAction {

    private UpdateFeaturedProfiles mUpdateFeaturedProfiles;

    public UpdateFeaturedProfilesAction() {
        this.mUpdateFeaturedProfiles = new UpdateFeaturedProfiles();
    }

    public UpdateFeaturedProfiles setList(String featuredProfiles) {
        mUpdateFeaturedProfiles.setList(featuredProfiles);
        return mUpdateFeaturedProfiles;
    }

}
