package com.dolphpire.api.interfaces;

public interface ApiCallback<T> {

    interface ApiKeyError {
        void badApi();
    }

}
