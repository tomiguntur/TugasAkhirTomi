package com.tomiguntur.ProjectAkhir.Account;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tomiguntur.ProjectAkhir.R;

public class UpdateActivity extends AppCompatActivity {

    EditText nama_input, tanggal_input, jarak_input;
    Button update_button, delete_button;

    String id, nama, tanggal, jarak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().hide();

        nama_input = findViewById(R.id.nama_input2);
        tanggal_input = findViewById(R.id.tanggal_input2);
        jarak_input = findViewById(R.id.jarak_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nama);
        }

        update_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                nama = nama_input.getText().toString().trim();
                tanggal = tanggal_input.getText().toString().trim();
                jarak = jarak_input.getText().toString().trim();
                myDB.updateData(id, nama, tanggal, jarak);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nama") &&
                getIntent().hasExtra("tanggal") && getIntent().hasExtra("jarak")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            nama = getIntent().getStringExtra("nama");
            tanggal = getIntent().getStringExtra("tanggal");
            jarak = getIntent().getStringExtra("jarak");

            //Setting Intent Data
            nama_input.setText(nama);
            tanggal_input.setText(tanggal);
            jarak_input.setText(jarak);
            Log.d("stev", nama+" "+tanggal+" "+ jarak);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + tanggal + " ?");
        builder.setMessage("Are you sure you want to delete " + tanggal + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}