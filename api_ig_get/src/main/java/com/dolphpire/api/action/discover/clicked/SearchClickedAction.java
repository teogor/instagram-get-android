package com.dolphpire.api.action.discover.clicked;

public class SearchClickedAction {

    private SearchClicked mSearchClicked;

    public SearchClickedAction() {
        this.mSearchClicked = new SearchClicked();
    }

    public SearchClickedAction setType(int type) {
        this.mSearchClicked.setType(type);
        return this;
    }

    public SearchClickedAction setID(int id) {
        this.mSearchClicked.setID(id);
        return this;
    }

    public SearchClickedAction setWord(String word) {
        this.mSearchClicked.setWord(word);
        return this;
    }

    public SearchClicked set() {
        return mSearchClicked;
    }

}
