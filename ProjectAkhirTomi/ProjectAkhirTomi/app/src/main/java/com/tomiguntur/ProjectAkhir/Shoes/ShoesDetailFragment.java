package com.tomiguntur.ProjectAkhir.Shoes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.tomiguntur.ProjectAkhir.R;
import com.tomiguntur.ProjectAkhir.Shoes.Model.ShoesModel;

import java.util.HashMap;

public class ShoesDetailFragment extends Fragment {

    NavController navController;
    int quantity = 0;
    FirebaseFirestore firebaseFirestore;
    Button add, sub, order;
    TextView shoesname, description, quantityview, orderINFO;
    ImageView imageView;
    String shoesid, name, shoesdescription, imageURL;
    int price = 0;

    int totalPrice = 0;

    public ShoesDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoes_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.ShoesDetailImage);
        shoesname = view.findViewById(R.id.shoesnamedetail);
        description = view.findViewById(R.id.shoesdetaildetail);
        add = view.findViewById(R.id.incrementshoes);
        sub = view.findViewById(R.id.decrementshoes);
        quantityview = view.findViewById(R.id.quantityDETAILnUMBER);
        firebaseFirestore = FirebaseFirestore.getInstance();
        navController = Navigation.findNavController(view);
        order = view.findViewById(R.id.orderdetail);
        orderINFO = view.findViewById(R.id.orderINFO);

        name = ShoesDetailFragmentArgs.fromBundle(getArguments()).getShoesname();
        shoesid = ShoesDetailFragmentArgs.fromBundle(getArguments()).getId();
        imageURL = ShoesDetailFragmentArgs.fromBundle(getArguments()).getImageurl();
        shoesdescription = ShoesDetailFragmentArgs.fromBundle(getArguments()).getDescription();
        price = ShoesDetailFragmentArgs.fromBundle(getArguments()).getPrice();

        Glide.with(view.getContext()).load(imageURL).into(imageView);
        shoesname.setText(name + " $" + String.valueOf(price));
        description.setText(shoesdescription);

//        fetching the recent quantity in firestore and displaying it
        firebaseFirestore.collection("Shoees").document(shoesid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot value,  FirebaseFirestoreException error) {

                ShoesModel shoesModel = value.toObject(ShoesModel.class);
                quantity = shoesModel.getQuantity();
                quantityview.setText(String.valueOf(quantity));
                // showing the price
                totalPrice = quantity * price;
                orderINFO.setText(String.valueOf("Total Price is " + totalPrice));

                if (quantity == 0) {

                    firebaseFirestore.collection("Cart").document(name).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete( Task<Void> task) {

                        }
                    });

                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 10) {
                    Toast.makeText(getContext(), "You've reached maximum order", Toast.LENGTH_SHORT).show();
                    quantityview.setText(String.valueOf(quantity));
                } else {
                    quantity++; // quantity = quantity+1; similar
                    quantityview.setText(String.valueOf(quantity));

                    // showing the price
                    totalPrice = quantity * price;
                    orderINFO.setText(String.valueOf("Total Price is " + totalPrice));

                    //updating quantities
                    firebaseFirestore.collection("Shoees").document(shoesid).update("quantity", quantity).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                        }
                    });
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 0) {
                    Toast.makeText(getContext(), "Nothing in Cart", Toast.LENGTH_SHORT).show();
                    quantityview.setText(String.valueOf(quantity));
                    quantity = 0;
                    totalPrice = 0;
                } else {
                    quantity--;
                    quantityview.setText(String.valueOf(quantity));
                    // showing the price
                    totalPrice = quantity * price;
                    orderINFO.setText(String.valueOf("Total Price is " + totalPrice));
                    //updating quantity
                    firebaseFirestore.collection("Coffies").document(shoesid).update("quantity", quantity).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                        }
                    });
                }
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity == 0) {

                    navController.navigate(R.id.action_coffeeDetailFragment_to_allCoffeeListFragment);
                    Toast.makeText(getContext(), "You did not order " + name, Toast.LENGTH_SHORT).show();
                } else {

                    AddToCart();
                    ShoesDetailFragmentDirections.ActionCoffeeDetailFragmentToAllCoffeeListFragment
                            action = ShoesDetailFragmentDirections.actionCoffeeDetailFragmentToAllCoffeeListFragment();
                    action.setQuantity(quantity);
                    navController.navigate(action);
                    Toast.makeText(getContext(), "You've ordered " + name, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void AddToCart() {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("shoesname", name);
        hashMap.put("quantity", quantity);
        hashMap.put("totalprice", totalPrice);
        hashMap.put("shoesid", shoesid);
        hashMap.put("imageURL", imageURL);

//           creating new collection for cart
        firebaseFirestore.collection("Cart").document(name).set(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {

            }
        });

    }
}