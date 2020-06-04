package com.dolphpire.api.action.user.verified.send;

public class VerifiedSendAction {

    private VerifiedSendRequest mVerifiedSendRequest;

    public VerifiedSendAction() {
        this.mVerifiedSendRequest = new VerifiedSendRequest();
    }

    public VerifiedSendAction setDocumentID(String documentID) {
        this.mVerifiedSendRequest.setDocumentID(documentID);
        return this;
    }

    public VerifiedSendAction setCategory(int category) {
        this.mVerifiedSendRequest.setCategory(category);
        return this;
    }

    public VerifiedSendAction setKnownFor(String knownFor) {
        this.mVerifiedSendRequest.setKnownFor(knownFor);
        return this;
    }

    public VerifiedSendAction setFullName(String fullName) {
        this.mVerifiedSendRequest.setFullName(fullName);
        return this;
    }

    public VerifiedSendRequest set() {
        return mVerifiedSendRequest;
    }

}
