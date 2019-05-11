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

public class TrueCallerEvery10thCharacterResponse {

    public final Status status;

    @Nullable
    public final String data;

    @Nullable
    public final Throwable error;

    private TrueCallerEvery10thCharacterResponse(Status status, @Nullable String data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static TrueCallerEvery10thCharacterResponse loading() {
        return new TrueCallerEvery10thCharacterResponse(LOADING, null, null);
    }

    public static TrueCallerEvery10thCharacterResponse success(@NonNull String data) {
        return new TrueCallerEvery10thCharacterResponse(SUCCESS, data, null);
    }

    public static TrueCallerEvery10thCharacterResponse error(@NonNull Throwable error) {
        return new TrueCallerEvery10thCharacterResponse(ERROR, null, error);
    }

}
