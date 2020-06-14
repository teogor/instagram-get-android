package com.dolphpire.api.action.user;

import com.dolphpire.api.action.user.check.DataCheckAction;
import com.dolphpire.api.action.user.details.DetailsAction;
import com.dolphpire.api.action.user.order.OrderIGAction;

public class UserAction
{

    public UserAction()
    {

    }

    public DetailsAction details()
    {
        return new DetailsAction();
    }

    public OrderIGAction order()
    {
        return new OrderIGAction();
    }

    public DataCheckAction check()
    {
        return new DataCheckAction();
    }

}
