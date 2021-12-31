package com.tomiguntur.ProjectAkhir.Account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.tomiguntur.ProjectAkhir.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList lari_id, lari_nama, lari_tanggal, lari_jarak;

    CustomAdapter(Activity activity, Context context, ArrayList lari_id, ArrayList lari_nama, ArrayList lari_tanggal,
                  ArrayList lari_jarak){
        this.activity = activity;
        this.context = context;
        this.lari_id = lari_id;
        this.lari_nama = lari_nama;
        this.lari_tanggal = lari_tanggal;
        this.lari_jarak = lari_jarak;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.lari_id_txt.setText(String.valueOf(lari_id.get(position)));
        holder.lari_nama_txt.setText(String.valueOf(lari_nama.get(position)));
        holder.lari_tanggal_txt.setText(String.valueOf(lari_tanggal.get(position)));
        holder.lari_jarak_txt.setText(String.valueOf(lari_jarak.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(lari_id.get(position)));
                intent.putExtra("nama", String.valueOf(lari_nama.get(position)));
                intent.putExtra("tanggal", String.valueOf(lari_tanggal.get(position)));
                intent.putExtra("jarak", String.valueOf(lari_jarak.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return lari_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView lari_id_txt, lari_nama_txt, lari_tanggal_txt, lari_jarak_txt;
        LinearLayout mainLayout;
        Animation translate_amim;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lari_id_txt = itemView.findViewById(R.id.lari_id_txt);
            lari_nama_txt = itemView.findViewById(R.id.lari_nama_txt);
            lari_tanggal_txt = itemView.findViewById(R.id.lari_tanggal_txt);
            lari_jarak_txt = itemView.findViewById(R.id.lari_jarak_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout2);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}