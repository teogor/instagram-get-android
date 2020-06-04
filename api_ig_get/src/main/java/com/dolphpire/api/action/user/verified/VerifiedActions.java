package com.dolphpire.api.action.user.verified;

import com.dolphpire.api.action.user.verified.checkState.CheckRequestState;
import com.dolphpire.api.action.user.verified.send.VerifiedSendAction;

public class VerifiedActions {

    public VerifiedActions() {

    }

    public VerifiedSendAction sendRequest() {
        return new VerifiedSendAction();
    }

    public CheckRequestState checkRequestState() {
        return new CheckRequestState();
    }

}
