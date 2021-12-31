package com.tomiguntur.ProjectAkhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tomiguntur.ProjectAkhir.Account.AccountActivity;
import com.tomiguntur.ProjectAkhir.News.NewsActivity;
import com.tomiguntur.ProjectAkhir.Shoes.ShoesActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLogOut;
    FirebaseAuth mAuth;
    RelativeLayout relativeLayoutRunning, relativeLayoutShoes, relativeLayoutCart, relativeLayoutAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        btnLogOut = findViewById(R.id.btnLogout);
        relativeLayoutRunning = (RelativeLayout) findViewById(R.id.rlRunning);
        relativeLayoutShoes = (RelativeLayout) findViewById(R.id.rlShoes);
        relativeLayoutAccount = (RelativeLayout) findViewById(R.id.rlAccount);

        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        relativeLayoutRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });

        relativeLayoutShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShoesActivity.class));
            }
        });


        relativeLayoutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}