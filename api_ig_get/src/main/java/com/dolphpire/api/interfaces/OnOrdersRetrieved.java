package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface OnOrdersRetrieved<T>
{

    interface OnCompleteListener<OrderModel>
    {
        void onSuccess(@NonNull List<OrderModel> dataList);
    }

}
