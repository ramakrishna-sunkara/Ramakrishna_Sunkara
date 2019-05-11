package com.ram.systemtest.manager;

import com.ram.systemtest.utils.ApiCallInterface;

import io.reactivex.Observable;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call trueCaller10thCharacterRequest api
     * */
    public Observable<String> callTrueCaller10thCharacterRequest() {
        return apiCallInterface.trueCaller10thCharacterRequest();
    }

    /*
     * method to call trueCallerEvery10thCharacterRequest api
     * */
    public Observable<String> callTrueCallerEvery10thCharacterRequest() {
        return apiCallInterface.trueCallerEvery10thCharacterRequest();
    }

    /*
     * method to call trueCallerWordCounterRequest api
     * */
    public Observable<String> callTrueCallerWordCounterRequest() {
        return apiCallInterface.trueCallerWordCounterRequest();
    }

}
