package com.tomiguntur.ProjectAkhir.Shoes.MVVM;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tomiguntur.ProjectAkhir.Shoes.Model.ShoesModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Repositoryu {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    List<ShoesModel> shoesModelList = new ArrayList<>();

    ShoesList interfaceocfeelist;

    public Repositoryu(ShoesList interfaceocfeelist) {
        this.interfaceocfeelist = interfaceocfeelist;
    }

    public void getCoffee(){

        firebaseFirestore.collection("Shoees").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    shoesModelList.clear();
                    for (DocumentSnapshot ds: Objects.requireNonNull(task.getResult()).getDocuments()) {
                        ShoesModel shoesModel = ds.toObject(ShoesModel.class);
                        shoesModelList.add(shoesModel);
                        interfaceocfeelist.shoesLists(shoesModelList);
                    }
                }
            }
        });
    }
    public interface ShoesList {
        void shoesLists(List<ShoesModel> shoesModels);
    }
}
