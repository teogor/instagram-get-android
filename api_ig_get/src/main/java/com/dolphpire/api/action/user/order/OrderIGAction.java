package com.dolphpire.api.action.user.order;

public class OrderIGAction
{

    public OrderIGAction() {

    }

    public OrderIG likes(int userID, int order) {
        return new OrderIG(String.valueOf(userID), String.valueOf(order), 0);
    }

    public OrderIG followers(int userID, int order) {
        return new OrderIG(String.valueOf(userID), String.valueOf(order), 1);
    }

}
