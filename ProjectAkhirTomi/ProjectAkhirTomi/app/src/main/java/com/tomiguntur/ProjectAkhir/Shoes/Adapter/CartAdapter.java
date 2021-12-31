package com.tomiguntur.ProjectAkhir.Shoes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tomiguntur.ProjectAkhir.R;
import com.tomiguntur.ProjectAkhir.Shoes.Model.CartModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {

    List<CartModel> cartModelList;

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlistlayout, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(CartAdapter.CartHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(cartModelList.get(position).getImageURL()).into(holder.imageOfShoes);
        holder.price.setText("Ordered " + String.valueOf(cartModelList.get(position).getQuantity()) + " for $" + String.valueOf(cartModelList.get(position).getTotalprice()));
        holder.name.setText(cartModelList.get(position).getShoesname());

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public void setCartModelList(List<CartModel> cartModelList) {
        this.cartModelList = cartModelList;
    }
    class CartHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        ImageView imageOfShoes;

        public CartHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cartshoesname);
            price = itemView.findViewById(R.id.orderdetail);
            imageOfShoes = itemView.findViewById(R.id.cartImage);
        }
    }
}
