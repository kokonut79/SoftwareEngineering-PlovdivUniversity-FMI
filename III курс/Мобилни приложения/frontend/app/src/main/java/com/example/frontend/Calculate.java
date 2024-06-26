package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class Calculate extends AppCompatActivity {
    Button calculate;
    EditText first_num, operation, second_num;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        calculate = findViewById(R.id.calculate);
        first_num = findViewById(R.id.first_num);
        operation = findViewById(R.id.operation);
        second_num = findViewById(R.id.second_num);
        resultTextView = findViewById(R.id.resultTextView);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread t = new Thread(()->{
                    try{
                        OkHttpClient client =
                                new OkHttpClient.Builder()
                                        .build();
                        Retrofit retrofit
                                = new Retrofit.Builder()
                                .baseUrl("http://192.168.50.159:8000")
                                .addConverterFactory(
                                        GsonConverterFactory.create()
                                )
                                .client(client)
                                .build();
                        int num = Integer.valueOf(first_num.getText().toString());
                        int num2 = Integer.valueOf(second_num.getText().toString());
                        ContactCalculatorAPI api = retrofit.create(ContactCalculatorAPI.class);
                        Call<ContactCalculatorAPI.calculator> calculate =
                                api.api_calculate(new ContactCalculatorAPI.calculator(
                                        num,
                                        operation.getText().toString(),
                                        num2
                                ));
                        System.out.println(second_num.getText().toString());
                        Response<ContactCalculatorAPI.calculator>
                                r = calculate.execute();
                        if(r.isSuccessful()){
                            ContactCalculatorAPI.calculator resp = r.body();
                            setResultTextView();

                        }else{
                            throw new RuntimeException("Server Error: "
                                    +r.errorBody().string()
                            );
                        }

                    }catch (Exception e){
                        runOnUiThread(()->Toast
                                .makeText(getApplicationContext(),
                                        e.getLocalizedMessage(),
                                        Toast.LENGTH_LONG)
                                .show());
                    }
                });
                t.start();
            }
        });
    }

    public void setResultTextView() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.50.159:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ContactCalculatorAPI ContactCalculatorAAPI = retrofit.create(ContactCalculatorAPI.class);

        Call<String> call = ContactCalculatorAAPI.getResult();


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    resultTextView.setText("Code : " + response.code());
                }else{
                    resultTextView.setText(response.body());
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                resultTextView.setText(t.getMessage());
            }
        });

    }
}