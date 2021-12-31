package com.tomiguntur.ProjectAkhir.Shoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tomiguntur.ProjectAkhir.R;
import com.tomiguntur.ProjectAkhir.Shoes.Adapter.ShoesAdapter;
import com.tomiguntur.ProjectAkhir.Shoes.MVVM.ShoesViewModel;
import com.tomiguntur.ProjectAkhir.Shoes.Model.CartModel;
import com.tomiguntur.ProjectAkhir.Shoes.Model.ShoesModel;

import java.util.ArrayList;
import java.util.List;

public class AllShoesListFragment extends Fragment implements ShoesAdapter.GetOneShoes {

    FirebaseFirestore firebaseFirestore;
    ShoesAdapter mAdapter;
    RecyclerView recyclerView;
    ShoesViewModel viewModel;
    NavController navController;
    int quantity = 0;
    FloatingActionButton fab;
    TextView quantityOnfAB;
    List<Integer> savequantity = new ArrayList<>();
    int quantitysum = 0;

    public AllShoesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_shoes_list, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseFirestore  = FirebaseFirestore.getInstance();
        recyclerView = view.findViewById(R.id.recViewAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter  = new ShoesAdapter(this);
        navController = Navigation.findNavController(view);
        quantityOnfAB = view.findViewById(R.id.quantityOnfAB);
        fab = view.findViewById(R.id.fab);
        viewModel = new ViewModelProvider(getActivity()).get(ShoesViewModel.class);

        viewModel.getShoesList().observe(getViewLifecycleOwner(), new Observer<List<ShoesModel>>() {
            @Override
            public void onChanged(List<ShoesModel> shoesModels) {
                mAdapter.setShoesModelList(shoesModels);
                recyclerView.setAdapter(mAdapter);
            }
        });

        quantity = AllShoesListFragmentArgs.fromBundle(getArguments()).getQuantity();
        firebaseFirestore.collection("Cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot ds: task.getResult().getDocuments()) {
                        CartModel cartModel = ds.toObject(CartModel.class);
                        int initialquantity = cartModel.getQuantity();
                        savequantity.add(initialquantity);
                    }

                    for (int i =0; i < savequantity.size(); i++) {
                        quantitysum+= Integer.parseInt(String.valueOf(savequantity.get(i)));
                    }

                    quantityOnfAB.setText(String.valueOf(quantitysum));
                    quantitysum = 0;
                    savequantity.clear(); // unless we add something new to our list// previous records are cleared.
                }

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_allShoesListFragment_to_cartFragment);
            }
        });
    }

    @Override
    public void clickedShoes(int position, List<ShoesModel> shoesModels) {

        String shoesid = shoesModels.get(position).getShoesid();
        String description = shoesModels.get(position).getDescription();
        String shoesename = shoesModels.get(position).getShoesname();
        int price = shoesModels.get(position).getPrice();
        String imageURL = shoesModels.get(position).getImageURL();


        AllShoesListFragmentDirections.ActionAllShoesListFragmentToShoesDetailFragment
                action = AllShoesListFragmentDirections.actionAllShoesListFragmentToShoesDetailFragment();


        action.setShoesname(shoesename);
        action.setDescription(description);
        action.setImageurl(imageURL);
        action.setPrice(price);
        action.setId(shoesid);

        navController.navigate(action);

    }

}