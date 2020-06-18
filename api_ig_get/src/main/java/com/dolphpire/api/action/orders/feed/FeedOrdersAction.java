package com.dolphpire.api.action.orders.feed;

public class FeedOrdersAction
{

    private FeedOrders mFeedOrders;

    public FeedOrdersAction() {
        this.mFeedOrders = new FeedOrders();
    }

    public FeedOrders all() {
        this.mFeedOrders.setType(String.valueOf(0));
        return this.mFeedOrders;
    }

}
