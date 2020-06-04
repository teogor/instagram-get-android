package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

public interface ZFlowFailureCallback<T> {

    interface OnFailureListener {
        void onFailure(@NonNull Exception e);
    }

}
