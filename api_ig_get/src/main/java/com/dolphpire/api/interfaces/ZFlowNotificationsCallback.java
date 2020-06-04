package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowNotificationsCallback<T> {

    interface OnCompleteListener<ZeoFlowNotification> {
        void onComplete(@NonNull List<ZeoFlowNotification> dataList);
    }

}
