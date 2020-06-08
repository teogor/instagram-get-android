package com.dolphpire.api.action.igaccount;

import com.dolphpire.api.action.igaccount.addAccount.AddAccountAction;

public class IGAccountAction
{

    public IGAccountAction()
    {

    }

    public AddAccountAction addAccount()
    {
        return new AddAccountAction();
    }

}
