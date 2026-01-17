package com.example.kontaqtebi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    adapter adapter;
    db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }



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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            getSms();
        }
    }

    public void getSms() {
        Uri uri=Uri.parse("content://sms/inbox");
        Cursor cursor=getContentResolver().query(uri,null,null,null);
        if( cursor!=null && cursor.moveToFirst())     {         do {
            int indexAddress = cursor.getColumnIndex("address");
            int indexBody = cursor.getColumnIndex("body");
            if (indexAddress != -1 && indexBody != -1) {
                String address = cursor.getString(indexAddress);
                String body = cursor.getString(indexBody);
            }         }while(cursor.moveToNext());         cursor.close();     }
        else {         Log.d("SMS_DATA", "ინბოქსი  ცარიელია ან წვდომა არ არის.");     } }
}

