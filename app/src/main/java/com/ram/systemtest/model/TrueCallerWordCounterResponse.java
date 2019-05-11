package com.ram.systemtest.model;

import com.ram.systemtest.utils.Status;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.ram.systemtest.utils.Status.ERROR;
import static com.ram.systemtest.utils.Status.LOADING;
import static com.ram.systemtest.utils.Status.SUCCESS;


/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

public class TrueCallerWordCounterResponse {

    public final Status status;

    @Nullable
    public final String data;

    @Nullable
    public final Throwable error;

    private TrueCallerWordCounterResponse(Status status, @Nullable String data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static TrueCallerWordCounterResponse loading() {
        return new TrueCallerWordCounterResponse(LOADING, null, null);
    }

    public static TrueCallerWordCounterResponse success(@NonNull String wordModels) {
        return new TrueCallerWordCounterResponse(SUCCESS, wordModels, null);
    }

    public static TrueCallerWordCounterResponse error(@NonNull Throwable error) {
        return new TrueCallerWordCounterResponse(ERROR, null, error);
    }

}
