package com.tomiguntur.ProjectAkhir.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tomiguntur.ProjectAkhir.R;

public class AddActivity extends AppCompatActivity {

    EditText nama_input, tanggal_input, jarak_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();

        nama_input = findViewById(R.id.nama_input);
        tanggal_input = findViewById(R.id.tanggal_input);
        jarak_input = findViewById(R.id.jarak_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(nama_input.getText().toString().trim(),
                        tanggal_input.getText().toString().trim(),
                        Integer.valueOf(jarak_input.getText().toString().trim()));
            }
        });
    }
}