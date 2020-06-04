package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

public interface ZFlowUserCallback<T> {

    interface OnCompleteListener<ZeoFlowUser> {
        void onComplete(@NonNull ZeoFlowUser userData);
    }

}
