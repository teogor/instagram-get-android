package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowQUsersCallback<T> {

    interface OnCompleteListener<ZeoFlowQuickUser> {
        void onComplete(@NonNull List<ZeoFlowQuickUser> dataList);
    }

}
