package com.ram.systemtest.utils;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */


public interface ApiCallInterface {

    @GET(Urls.TRUE_CALLER_10TH_CHARACTER_REQUEST)
    Observable<String> trueCaller10thCharacterRequest();

    @GET(Urls.TRUE_CALLER_EVERY_10TH_CHARACTER_REQUEST)
    Observable<String> trueCallerEvery10thCharacterRequest();

    @GET(Urls.TRUE_CALLER_WORD_COUNTER_REQUEST)
    Observable<String> trueCallerWordCounterRequest();
}
