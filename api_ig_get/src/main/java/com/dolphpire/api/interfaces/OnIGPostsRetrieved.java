package com.dolphpire.api.interfaces;

import androidx.annotation.NonNull;

public interface OnIGPostsRetrieved<T>
{

    interface OnCompleteListener<IGPostsModel>
    {
        void onRetrieved(@NonNull IGPostsModel mIGPostsModel);
    }

}
