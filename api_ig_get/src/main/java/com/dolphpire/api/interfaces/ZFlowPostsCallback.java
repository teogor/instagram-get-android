package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowPostsCallback<T> {

    interface OnCompleteListener<ZeoFlowPost> {
        void onComplete(@NonNull List<ZeoFlowPost> dataList);
    }

}
