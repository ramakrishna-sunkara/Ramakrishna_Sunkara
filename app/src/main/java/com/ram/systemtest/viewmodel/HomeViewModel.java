package com.ram.systemtest.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ram.systemtest.manager.DataProcessManager;
import com.ram.systemtest.manager.Repository;
import com.ram.systemtest.model.TrueCaller10thCharacterResponse;
import com.ram.systemtest.model.TrueCallerEvery10thCharacterResponse;
import com.ram.systemtest.model.TrueCallerWordCounterResponse;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

public class HomeViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<TrueCaller10thCharacterResponse> trueCaller10thCharacterLiveData = new MutableLiveData<>();
    private final MutableLiveData<TrueCallerEvery10thCharacterResponse> trueCallerEvery10thCharacterLiveData = new MutableLiveData<>();
    private final MutableLiveData<TrueCallerWordCounterResponse> truecallerWordCounterLiveData = new MutableLiveData<>();
    private Repository repository;
    private DataProcessManager dataProcessManager;

    public HomeViewModel(Repository repository, DataProcessManager dataProcessManager) {
        this.repository = repository;
        this.dataProcessManager = dataProcessManager;
    }

    public MutableLiveData<TrueCaller10thCharacterResponse> trueCaller10thCharacterResponse() {
        return trueCaller10thCharacterLiveData;
    }

    public MutableLiveData<TrueCallerEvery10thCharacterResponse> trueCallerEvery10thCharacterResponse() {
        return trueCallerEvery10thCharacterLiveData;
    }

    public MutableLiveData<TrueCallerWordCounterResponse> trueCallerWordCounterResponse() {
        return truecallerWordCounterLiveData;
    }

    /*
     * method to call TrueCaller10thCharacterRequest
     * */
    public void hitTrueCaller10thCharacterRequestApi() {

        disposables.add(repository.callTrueCaller10thCharacterRequest()
                .flatMap((Function<String, ObservableSource<String>>) data -> dataProcessManager.processTrueCaller10thCharacterResult(data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> trueCaller10thCharacterLiveData.setValue(TrueCaller10thCharacterResponse.loading()))
                .subscribe(
                        result -> trueCaller10thCharacterLiveData.setValue(TrueCaller10thCharacterResponse.success(result)),
                        throwable -> trueCaller10thCharacterLiveData.setValue(TrueCaller10thCharacterResponse.error(throwable))
                ));
    }

    /*
     * method to call TrueCallerEvery10thCharacterRequest
     * */
    public void hitTrueCallerEvery10thCharacterRequestApi() {

        disposables.add(repository.callTrueCallerEvery10thCharacterRequest()
                .flatMap((Function<String, ObservableSource<String>>) data -> dataProcessManager.processTrueCallerEvery10thCharacterResult(data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> trueCallerEvery10thCharacterLiveData.setValue(TrueCallerEvery10thCharacterResponse.loading()))
                .subscribe(
                        result -> trueCallerEvery10thCharacterLiveData.setValue(TrueCallerEvery10thCharacterResponse.success(result)),
                        throwable -> trueCallerEvery10thCharacterLiveData.setValue(TrueCallerEvery10thCharacterResponse.error(throwable))
                ));
    }

    /*
     * method to call TrueCallerWordCounterRequest
     * */
    public void hitTrueCallerWordCounterRequestApi() {

        disposables.add(repository.callTrueCallerWordCounterRequest()
                .flatMap((Function<String, ObservableSource<String>>) data -> dataProcessManager.processTrueCallerWordCounterResult(data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> truecallerWordCounterLiveData.setValue(TrueCallerWordCounterResponse.loading()))
                .subscribe(
                        result -> truecallerWordCounterLiveData.setValue(TrueCallerWordCounterResponse.success(result)),
                        throwable -> truecallerWordCounterLiveData.setValue(TrueCallerWordCounterResponse.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
