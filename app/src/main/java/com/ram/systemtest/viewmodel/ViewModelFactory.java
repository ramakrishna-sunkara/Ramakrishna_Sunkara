package com.ram.systemtest.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ram.systemtest.manager.DataProcessManager;
import com.ram.systemtest.manager.Repository;

import javax.inject.Inject;

/**
 * Created by Ramakrishna Sunkara on 10-05-2019
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;
    private DataProcessManager dataProcessManager;

    @Inject
    public ViewModelFactory(Repository repository, DataProcessManager dataProcessManager) {
        this.repository = repository;
        this.dataProcessManager = dataProcessManager;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(repository, dataProcessManager);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
