package com.dolphpire.api.interfaces;

public interface ZFlowPasswordCallback<T> {

    interface OnChangePassword {
        void onChanged();
        void onWrongPassword();
    }

}
