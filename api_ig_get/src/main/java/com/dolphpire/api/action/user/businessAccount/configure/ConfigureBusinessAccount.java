package com.dolphpire.api.action.user.businessAccount.configure;

public class ConfigureBusinessAccount {

    private ExecuteAction mConfigureAccount;

    public ConfigureBusinessAccount() {
        mConfigureAccount = new ExecuteAction();
    }

    public ConfigureBusinessAccount setCategory(int category) {
        mConfigureAccount.setCategory(category);
        return this;
    }

    public ExecuteAction set() {
        return mConfigureAccount;
    }

}
