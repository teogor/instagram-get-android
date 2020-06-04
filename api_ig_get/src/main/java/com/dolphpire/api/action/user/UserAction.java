package com.dolphpire.api.action.user;

import com.dolphpire.api.action.user.authenticate.UserAuthCheck;
import com.dolphpire.api.action.user.block.BlockAction;
import com.dolphpire.api.action.user.businessAccount.BusinessAccount;
import com.dolphpire.api.action.user.check.DataCheckAction;
import com.dolphpire.api.action.user.connections.ConnectionsAction;
import com.dolphpire.api.action.user.details.DetailsAction;
import com.dolphpire.api.action.user.interact.UserInteractAction;
import com.dolphpire.api.action.user.password.PasswordAction;
import com.dolphpire.api.action.user.searchHistory.SearchHistory;
import com.dolphpire.api.action.user.update.UserUpdateAction;
import com.dolphpire.api.action.user.verified.VerifiedActions;

public class UserAction {

    public UserAction() {

    }

    public BlockAction blockAction() {
        return new BlockAction();
    }

    public DetailsAction details() {
        return new DetailsAction();
    }

    public ConnectionsAction connections() {
        return new ConnectionsAction();
    }

    public UserInteractAction interact() {
        return new UserInteractAction();
    }

    public PasswordAction changePassword() {
        return new PasswordAction();
    }

    public DataCheckAction check() {
        return new DataCheckAction();
    }

    public UserAuthCheck authenticate() {
        return new UserAuthCheck();
    }

    public VerifiedActions verified() {
        return new VerifiedActions();
    }

    public UserUpdateAction update() {
        return new UserUpdateAction();
    }

    public SearchHistory searchHistory() {
        return new SearchHistory();
    }

    public BusinessAccount businessAccount() {
        return new BusinessAccount();
    }

}
