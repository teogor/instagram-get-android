package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface ZFlowPresenterCallback<T> {

    interface OnCompleteListener<ZeoFlowPresenter> {
        void onComplete(@NonNull List<ZeoFlowPresenter> dataList);
    }

}
