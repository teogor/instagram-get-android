package com.dolphpire.api.action.orders;

import com.dolphpire.api.action.orders.retrieve.RetrieveOrdersAction;

public class OrdersAction
{

    public OrdersAction()
    {

    }

    public RetrieveOrdersAction retrieve()
    {
        return new RetrieveOrdersAction();
    }

}
