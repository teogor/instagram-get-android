package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

public interface FailureCallback<T> {

    interface OnFailureListener {
        void onFailure(@NonNull Exception e);
    }

}
