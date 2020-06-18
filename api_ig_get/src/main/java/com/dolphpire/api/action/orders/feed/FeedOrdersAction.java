package com.dolphpire.api.action.orders.feed;

public class FeedOrdersAction
{

    private FeedOrders mFeedOrders;

    public FeedOrdersAction() {
        this.mFeedOrders = new FeedOrders();
    }

    public FeedOrders inProgress() {
        this.mFeedOrders.setType(String.valueOf(0));
        return this.mFeedOrders;
    }

    public FeedOrders completed() {
        this.mFeedOrders.setType(String.valueOf(1));
        return this.mFeedOrders;
    }

}
