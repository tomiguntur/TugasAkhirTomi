package com.tomiguntur.ProjectAkhir.News;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomiguntur.ProjectAkhir.R;

public class DescriptionNews extends AppCompatActivity {

    ImageView img;
    TextView nama,info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_news);
        getSupportActionBar().hide();

        Intent xtra = getIntent();
        img = findViewById(R.id.imgDeskFoto);
        nama = findViewById(R.id.txtDeskNama);
        info = findViewById(R.id.txtDeskInfo);
        img.setImageResource(xtra.getExtras().getInt("gambar"));
        nama.setText(xtra.getExtras().getString("nama"));
        info.setText(xtra.getExtras().getString("deskripsi"));
    }

}