package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class Register extends AppCompatActivity {
    Button register;
    EditText username, email, password, password_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password_check = findViewById(R.id.password_check);
        register.setOnClickListener(new View.OnClickListener() {
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

                        ContactUserAPI api = retrofit.create(ContactUserAPI.class);

                        Call<ContactUserAPI.users> insertedUser =
                                api.api_add_user(new ContactUserAPI.users(
                                        username.getText().toString(),
                                        email.getText().toString(),
                                        password.getText().toString(),
                                        password_check.getText().toString()
                                ));
                        Response<ContactUserAPI.users>
                                r = insertedUser.execute();
                        if(r.isSuccessful()){
                            ContactUserAPI.users resp = r.body();
                            runOnUiThread(()->Toast.makeText(
                                    getApplicationContext(),
                                    "Successfully created an account!",
                                    Toast.LENGTH_LONG
                            ).show());
                            openRegisterActivity();


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
    public void openRegisterActivity(){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}