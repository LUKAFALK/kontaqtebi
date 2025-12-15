package com.example.kontaqtebi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class meore extends AppCompatActivity {

    TextView tvName, tvPhone, tvCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meore);

        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvCircle = findViewById(R.id.tvCircl);

        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");

        tvName.setText(name);
        tvPhone.setText(phone);
        tvCircle.setText(String.valueOf(name.charAt(0)).toUpperCase());
    }
}