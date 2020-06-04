package com.dolphpire.api.action.user.searchHistory;

public class SearchHistory {

    public SearchHistory() {

    }

    public SearchHistoryGet get() {
        return new SearchHistoryGet();
    }

    public SearchHistoryDelete delete() {
        return new SearchHistoryDelete();
    }

}
