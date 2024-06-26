package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrimaryScreen extends AppCompatActivity {
    Button calculate, frequency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_screen);
        calculate = findViewById(R.id.calculate);
        frequency = findViewById(R.id.frequency);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalculusActivity();
            }
        });

        frequency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFrequencyActivity();
            }
        });

    }

    public void openCalculusActivity(){
        Intent intent = new Intent (this, Calculate.class);
        startActivity(intent);
    }

    public void openFrequencyActivity(){
        Intent intent = new Intent (this, Frequent.class);
        startActivity(intent);
    }
}