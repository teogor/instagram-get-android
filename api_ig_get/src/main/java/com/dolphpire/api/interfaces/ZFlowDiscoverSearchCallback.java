package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowDiscoverSearchCallback<T> {

    interface OnCompleteListener<ZeoFlowDiscoverModel> {
        void onComplete(@NonNull List<ZeoFlowDiscoverModel> dataList);
    }

}
