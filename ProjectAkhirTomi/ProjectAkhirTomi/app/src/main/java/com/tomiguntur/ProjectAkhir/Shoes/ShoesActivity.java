package com.tomiguntur.ProjectAkhir.Shoes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tomiguntur.ProjectAkhir.R;

public class ShoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes);
        getSupportActionBar().hide();
    }
}