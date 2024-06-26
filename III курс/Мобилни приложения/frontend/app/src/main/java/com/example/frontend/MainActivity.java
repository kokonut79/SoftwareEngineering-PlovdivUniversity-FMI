package com.example.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button register, login;
    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
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

                        Call<ContactUserAPI.users> loggedUser =
                                api.login_user(new ContactUserAPI.users(
                                        username.getText().toString(),
                                        password.getText().toString()
                                ));
                        Response<ContactUserAPI.users>
                                r = loggedUser.execute();
                        if(r.isSuccessful()){
                            ContactUserAPI.users resp = r.body();
                            runOnUiThread(()->Toast.makeText(
                                    getApplicationContext(),
                                    "Logged in!",
                                    Toast.LENGTH_LONG
                            ).show());
                            openMainScreen();


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


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });
    }

    public void openRegisterActivity(){
        Intent intent = new Intent (this, Register.class);
        startActivity(intent);
    }


    public void openMainScreen(){
        Intent intent = new Intent (this, PrimaryScreen.class);
        startActivity(intent);
    }
}