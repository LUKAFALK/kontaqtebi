package com.example.kontaqtebi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    adapter adapter;
    db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new db(this);

        listView = findViewById(R.id.list);

        ArrayList<contact> list = db.getContacts();
        Button Add = findViewById(R.id.damateba);

        Add.setOnClickListener(v -> {

            View view = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.dialog_add, null);

            EditText etName = view.findViewById(R.id.etName);
            EditText etPhone = view.findViewById(R.id.etPhone);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("კონტაქტის დამატება")
                    .setView(view)
                    .setPositiveButton("დამატება", (dialog, which) -> {

                        String name = etName.getText().toString().trim();
                        String phone = etPhone.getText().toString().trim();

                        if (!name.isEmpty() && !phone.isEmpty()) {
                            db.addContact(name, phone);


                            adapter = new adapter(
                                    MainActivity.this,
                                    db.getContacts()
                            );
                            listView.setAdapter(adapter);
                        }
                    })
                    .setNegativeButton("გაუქმება", null)
                    .show();
        });



        adapter = new adapter(this, list);
        listView.setAdapter(adapter);
    }
}

