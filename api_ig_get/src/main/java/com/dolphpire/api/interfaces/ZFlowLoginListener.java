package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

public interface ZFlowLoginListener<T> {

    interface OnLoggedIn<ZeoFlowUser> {
        void onLoggedIn(ZeoFlowUser userData);
    }

    interface OnLoginFailure {
        void onAccountClosed(@NonNull String error);
        void onEmailNotVerified(@NonNull String error);
        void onTwoStepsAuth(@NonNull String error);
        void onBadLogKey(@NonNull String error);
        void onBadPassword(@NonNull String error);
    }

}
