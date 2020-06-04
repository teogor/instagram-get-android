package com.dolphpire.api.interfaces;

public interface ZFlowCredentialsCallback<T> {

    interface OnCredentialsError {
        void onInsertError();
        void onUpdateError();
    }

    interface OnCredentialsCompleted {
        void onInsert();
        void onUpdate();
    }

}
