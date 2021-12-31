package com.tomiguntur.ProjectAkhir.Shoes.MVVM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tomiguntur.ProjectAkhir.Shoes.Model.ShoesModel;

import java.util.List;

public class ShoesViewModel extends ViewModel implements Repositoryu.ShoesList {

    MutableLiveData<List<ShoesModel>> mutableLiveData = new MutableLiveData<List<ShoesModel>>();
    Repositoryu repositoryu = new Repositoryu(this);

    public ShoesViewModel() {

        repositoryu.getCoffee();
    }

    public LiveData<List<ShoesModel>> getShoesList(){
        return mutableLiveData;
    }

    @Override
    public void shoesLists(List<ShoesModel> shoesModels) {
        mutableLiveData.setValue(shoesModels);
    }
}