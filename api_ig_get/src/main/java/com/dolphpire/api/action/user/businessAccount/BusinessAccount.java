package com.dolphpire.api.action.user.businessAccount;

import com.dolphpire.api.action.user.businessAccount.configure.ConfigureBusinessAccount;

public class BusinessAccount {

    public BusinessAccount() {

    }

    public ConfigureBusinessAccount configure() {
        return new ConfigureBusinessAccount();
    }

}
