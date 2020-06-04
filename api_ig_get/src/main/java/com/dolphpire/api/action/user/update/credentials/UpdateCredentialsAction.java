package com.dolphpire.api.action.user.update.credentials;

public class UpdateCredentialsAction {

    private UpdateCredentials mUpdateCredentials;

    public UpdateCredentialsAction() {
        this.mUpdateCredentials = new UpdateCredentials();
    }

    public UpdateCredentialsAction setUsername(String username) {
        this.mUpdateCredentials.setUsername(username);
        return this;
    }

    public UpdateCredentialsAction setEmail(String email) {
        this.mUpdateCredentials.setEmail(email);
        return this;
    }

    public UpdateCredentialsAction setPhone(String phone) {
        this.mUpdateCredentials.setPhone(phone);
        return this;
    }

    public UpdateCredentials set() {
        return mUpdateCredentials;
    }

}
