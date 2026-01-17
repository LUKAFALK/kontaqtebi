package com.example.kontaqtebi;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class meore extends AppCompatActivity {

    TextView tvName, tvPhone, tvCircle;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meore);
        db db = new db(this);
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvCircle = findViewById(R.id.tvCircl);
        id=getIntent().getIntExtra("id",-1);
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");

        tvName.setText(name);
        tvPhone.setText(phone);
        tvCircle.setText(String.valueOf(name.charAt(0)).toUpperCase());

        Button shecvla=findViewById(R.id.shecvla);
        shecvla.setOnClickListener(v -> {

            View view = LayoutInflater.from(meore.this)
                    .inflate(R.layout.dialog_add, null);


            new AlertDialog.Builder(meore.this)
                    .setTitle("კონტაქტის შეცვლა")
                    .setView(view)
                    .setPositiveButton("შეცვლა", (dialog, which) -> {

                        if (!name.isEmpty() && !phone.isEmpty()) {
                            db.updateContact(id,name, phone);
                        }
                    })
                    .setNegativeButton("გაუქმება", null)
                    .show();
        });

        Button washla=findViewById(R.id.washla);
        washla.setOnClickListener(v -> {
            new AlertDialog.Builder(meore.this)
                    .setTitle("წაშლა")
                    .setMessage("ნამდვილად გინდა კონტაქტის წაშლა?")
                    .setPositiveButton("კი", (dialog, which) -> {

                        if (id != -1) {
                            db.deleteContact(id);
                            finish();
                        }
                    })
                    .setNegativeButton("არა", null)
                    .show();

        });

    }
}