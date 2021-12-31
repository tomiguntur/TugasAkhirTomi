package com.tomiguntur.ProjectAkhir.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.tomiguntur.ProjectAkhir.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter <NewsAdapter.ViewHolder> {
    private ArrayList<News> listKuliner;
    NewsActivity activity;
    public NewsAdapter(ArrayList<News> listNews, NewsActivity activity) {
        this.listKuliner = listNews;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_news, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = listKuliner.get(position);
        holder.txtNama.setText(news.getNama());
        holder.txtDesSingkat.setText(news.getDesSingkat());
        holder.imgFoto.setImageResource(news.getId_gambar());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openDetail(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKuliner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtNama, txtDesSingkat;
        public ImageView imgFoto;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.txtNama);
            txtDesSingkat = (TextView) itemView.findViewById(R.id.txtDesSingkat);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.mainLayout2);
        }
    }
}
