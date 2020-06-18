package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

import java.util.List;

public interface OnFeedRetrieved<T>
{

    interface OnCompleteListener<FeedModel>
    {
        void onSuccess(@NonNull List<FeedModel> dataList);
    }

}
