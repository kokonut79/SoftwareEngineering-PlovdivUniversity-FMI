package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Frequent extends AppCompatActivity {
    Button get_btn;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent);
        get_btn = findViewById(R.id.get_btn);
        result = findViewById(R.id.result);

        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResultTextView();
            }
        });
    }

    public void setResultTextView() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.50.159:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactCalculatorAPI ContactCalculatorAAPI = retrofit.create(ContactCalculatorAPI.class);

        Call<String> call = ContactCalculatorAAPI.getOperation();


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body().toString());
                if (!response.isSuccessful()) {
                    result.setText(response.code());
                }else{
                    result.setText(response.body());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage().toString());
                result.setText(t.getMessage());
            }
        });

    }
}