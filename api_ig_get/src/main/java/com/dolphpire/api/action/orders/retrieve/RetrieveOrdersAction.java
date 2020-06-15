package com.dolphpire.api.action.orders.retrieve;

public class RetrieveOrdersAction
{

    private RetrieveOrders mRetrieveOrders;

    public RetrieveOrdersAction() {
        this.mRetrieveOrders = new RetrieveOrders();
    }

    public RetrieveOrders inProgress() {
        this.mRetrieveOrders.setType(String.valueOf(0));
        return this.mRetrieveOrders;
    }

    public RetrieveOrders completed() {
        this.mRetrieveOrders.setType(String.valueOf(1));
        return this.mRetrieveOrders;
    }

}
