package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowProfileImagesCallback<T> {

    interface OnCompleteListener<ZeoFlowProfileImage> {
        void onComplete(@NonNull List<ZeoFlowProfileImage> dataList);
    }

}
