package com.dolphpire.api.action.user.connections;

public class ConnectionsAction {

    public ConnectionsAction() {

    }

    public UserConnectionAction followers(int user_id) {
        return new UserConnectionAction(user_id, 0);
    }

    public UserConnectionAction following(int user_id) {
        return new UserConnectionAction(user_id, 1);
    }

}
