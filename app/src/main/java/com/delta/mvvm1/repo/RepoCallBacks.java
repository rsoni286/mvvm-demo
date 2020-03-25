package com.delta.mvvm1.repo;

import androidx.annotation.Nullable;

import java.util.List;

public interface RepoCallBacks {
    void onProgress();

    <T> void onSuccess(boolean remote, @Nullable T localData, Class<T> type);

    void onFailure(boolean remote, @Nullable String error);
}
