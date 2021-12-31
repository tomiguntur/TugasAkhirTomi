package com.tomiguntur.ProjectAkhir.Shoes.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tomiguntur.ProjectAkhir.R;
import com.tomiguntur.ProjectAkhir.Shoes.Model.ShoesModel;

import java.util.List;

public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.ShoesListHolder> {

    List<ShoesModel> shoesModelList;
    GetOneShoes interfacegetShoes;

    public ShoesAdapter(GetOneShoes interfacegetShoes) {
        this.interfacegetShoes = interfacegetShoes;
    }

    @Override
    public ShoesListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoesliststyle, parent, false);
        return new ShoesListHolder(view);
    }

    @Override
    public void onBindViewHolder(ShoesListHolder holder, int position) {

        holder.shoesname.setText(shoesModelList.get(position).getShoesname());
        holder.description.setText(shoesModelList.get(position).getDescription());

        Glide.with(holder.itemView.getContext()).load(shoesModelList.get(position).getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return shoesModelList.size();
    }

    //    sets the shoes list from viewmodel
    public void setShoesModelList(List<ShoesModel> shoesModelList){
        this.shoesModelList = shoesModelList;
    }

    class ShoesListHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView shoesname, description;
        ImageView imageView;


        public ShoesListHolder(View itemView) {
            super(itemView);

            shoesname = itemView.findViewById(R.id.shoesName);
            description = itemView.findViewById(R.id.shoesDetail);
            imageView = itemView.findViewById(R.id.shoesImage);

            shoesname.setOnClickListener(this);
            description.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            interfacegetShoes.clickedShoes(getAdapterPosition(), shoesModelList);

        }
    }

    public interface GetOneShoes {
        void clickedShoes(int position, List<ShoesModel> shoesModels);
    }
}

