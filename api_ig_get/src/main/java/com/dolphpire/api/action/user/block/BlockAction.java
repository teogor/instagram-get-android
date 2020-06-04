package com.dolphpire.api.action.user.block;

public class BlockAction {

    public BlockAction() {

    }

    public ExecuteAction block(int user_id) {
        return new ExecuteAction(user_id, 1);
    }

    public ExecuteAction unblock(int user_id) {
        return new ExecuteAction(user_id, 0);
    }

}
