package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowUsersCallback<T> {

    interface OnCompleteListener<ZeoFlowUser> {
        void onComplete(@NonNull List<ZeoFlowUser> dataList);
    }

}
