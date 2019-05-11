package com.ram.systemtest.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.ram.systemtest.MyApplication;
import com.ram.systemtest.R;
import com.ram.systemtest.model.TrueCaller10thCharacterResponse;
import com.ram.systemtest.model.TrueCallerEvery10thCharacterResponse;
import com.ram.systemtest.model.TrueCallerWordCounterResponse;
import com.ram.systemtest.utils.Constant;
import com.ram.systemtest.viewmodel.HomeViewModel;
import com.ram.systemtest.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */
public class HomeActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    @BindView(R.id.rcv10thChar)
    ResultContainerView rcv10thChar;
    @BindView(R.id.rcvEvery10thChar)
    ResultContainerView rcvEvery10thChar;
    @BindView(R.id.rcvWordCount)
    ResultContainerView rcvWordCount;
    HomeViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);

        viewModel.trueCaller10thCharacterResponse().observe(this, this::consumeTrueCaller10thCharacterResponse);
        viewModel.trueCallerEvery10thCharacterResponse().observe(this, this::consumeTrueCallerEvery10thCharacterResponse);
        viewModel.trueCallerWordCounterResponse().observe(this, this::consumeTrueCallerWordCounterResponse);

    }


    @OnClick(R.id.btnLoadData)
    void onLoadDataClicked() {
        if (!Constant.checkInternetConnection(this)) {
            Toast.makeText(HomeActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        } else {
            viewModel.hitTrueCaller10thCharacterRequestApi();
            viewModel.hitTrueCallerEvery10thCharacterRequestApi();
            viewModel.hitTrueCallerWordCounterRequestApi();
        }
    }

    /*
     * method to handle TrueCaller10thCharacter Response
     * */
    private void consumeTrueCaller10thCharacterResponse(TrueCaller10thCharacterResponse response) {

        switch (response.status) {

            case LOADING:
                rcv10thChar.showProgress(true);
                break;

            case SUCCESS:
                rcv10thChar.showProgress(false);
                renderTrueCaller10thCharacterSuccessResponse(response.data);
                break;

            case ERROR:
                rcv10thChar.showProgress(false);
                rcv10thChar.setTxtResult(getString(R.string.errorString));
                break;

            default:
                break;
        }
    }

    /*
     * method to handle TrueCallerEvery10thCharacter Response
     * */
    private void consumeTrueCallerEvery10thCharacterResponse(TrueCallerEvery10thCharacterResponse response) {

        switch (response.status) {

            case LOADING:
                rcvEvery10thChar.showProgress(true);
                break;

            case SUCCESS:
                rcvEvery10thChar.showProgress(false);
                renderTrueCallerEvery10thCharacterSuccessResponse(response.data);
                break;

            case ERROR:
                rcvEvery10thChar.showProgress(false);
                rcvEvery10thChar.setTxtResult(getString(R.string.errorString));
                break;

            default:
                break;
        }
    }

    /*
     * method to handle TrueCallerWordCounter Response
     * */
    private void consumeTrueCallerWordCounterResponse(TrueCallerWordCounterResponse response) {

        switch (response.status) {

            case LOADING:
                rcvWordCount.showProgress(true);
                break;

            case SUCCESS:
                rcvWordCount.showProgress(false);
                renderTrueCallerWordCounterSuccessResponse(response.data);
                break;

            case ERROR:
                rcvWordCount.showProgress(false);
                rcvWordCount.setTxtResult(getString(R.string.errorString));
                break;

            default:
                break;
        }
    }

    /*
     * method to handle TrueCaller10thCharacter Success Response
     * */
    private void renderTrueCaller10thCharacterSuccessResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            rcv10thChar.setTxtResult(getString(R.string.msg_10th_char) + "\n" + response);
        } else {
            rcv10thChar.setTxtResult(getString(R.string.errorString));
        }
    }

    /*
     * method to handle TrueCallerEvery10thCharacter Success Response
     * */
    private void renderTrueCallerEvery10thCharacterSuccessResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            rcvEvery10thChar.setTxtResult(getString(R.string.msg_every_10th_char) + "\n" + response);
        } else {
            rcvEvery10thChar.setTxtResult(getString(R.string.errorString));
        }
    }

    /*
     * method to handle TrueCallerWordCounter Success Response
     * */
    private void renderTrueCallerWordCounterSuccessResponse(String response) {
        if (response != null) {
            rcvWordCount.setTxtResult(getString(R.string.msg_word_count) + "\n" + response);
        } else {
            rcvWordCount.setTxtResult(getString(R.string.errorString));
        }
    }
}
