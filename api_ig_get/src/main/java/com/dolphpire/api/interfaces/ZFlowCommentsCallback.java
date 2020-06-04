package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowCommentsCallback<T> {

    interface OnCompleteListener<ZeoFlowComment> {
        void onComplete(@NonNull List<ZeoFlowComment> dataList);
    }

}
