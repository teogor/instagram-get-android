package com.dolphpire.api.action.autocomplete;

public class AutocompleteAction {

    public AutocompleteAction() {

    }

    public GetAutocompleteAction byHashtag() {
        return new GetAutocompleteAction(0);
    }

    public GetAutocompleteAction byMention() {
        return new GetAutocompleteAction(1);
    }

}
