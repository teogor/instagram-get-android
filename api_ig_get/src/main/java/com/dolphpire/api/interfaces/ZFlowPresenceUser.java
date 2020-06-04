package com.dolphpire.api.interfaces;

public interface ZFlowPresenceUser<T> {

    interface OnComplete<ZeoFlowUser> {
        void onCompleted(ZeoFlowUser user);
    }

}
