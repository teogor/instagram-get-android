package com.dolphpire.api.interfaces;

public interface ZFlowOnVerifiedStateCallback<T> {

    interface OnComplete {
        void onCompleted(int state);
    }

}
