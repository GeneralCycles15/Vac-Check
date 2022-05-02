package com.example.vaccheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this::register);
    }

    public void register(View view) {
        Log.d("registerButton", "onClick: Moving to next activity");
        startActivity(new Intent(MainActivity.this, register_scanner.class));
    }
}