package com.dolphpire.api.action.discover;

import com.dolphpire.api.action.discover.clicked.SearchClickedAction;

public class DiscoverAction {

    public DiscoverAction() {

    }

    public DiscoverSearch initialize() {
        return new DiscoverSearch();
    }

    public SearchClickedAction onClick() {
        return new SearchClickedAction();
    }

}
