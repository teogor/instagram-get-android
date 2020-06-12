package com.dolphpire.api.action.user.order;

public class OrderIGAction
{

    public OrderIGAction() {

    }

    public OrderIG likes(long userID, int order) {
        return new OrderIG(String.valueOf(userID), String.valueOf(order), 0);
    }

    public OrderIG followers(long userID, int order) {
        return new OrderIG(String.valueOf(userID), String.valueOf(order), 1);
    }

}
