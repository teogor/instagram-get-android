package com.dolphpire.api.presence;

public class Presence {

    public Presence() {

    }

    public UserPresence user() {
        return new UserPresence();
    }

    public NewUserUID newUser() {
        return new NewUserUID();
    }

}
