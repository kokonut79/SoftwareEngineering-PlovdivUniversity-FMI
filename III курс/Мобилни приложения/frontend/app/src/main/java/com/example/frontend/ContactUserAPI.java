package com.example.frontend;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ContactUserAPI {
    public class message{
        public String status;
        public message(message m){
            this.status = m.status;
        }
        public message(String _status){
            this.status = _status;
        }
    }
    public class users {
        public String username;
        public String email;
        public String password;
        public String password_check;
        public users(users m){
            username = m.username;
            email = m.email;
            password = m.password;
            password_check = m.password_check;
        }
        public users(String _username, String _email,
                     String _password, String _password_check){
            username = _username;
            email = _email;
            password = _password;
            password_check = _password_check;
        }

        public users(String _username, String _password){
            username = _username;
            password = _password;
        }
    }

    @POST("/user/")
    public Call<users> api_add_user(@Body users c);

    @POST("/login/")
    public Call<users> login_user(@Body users c);

    @PUT("user/")
    public Call<users> api_update_user(@Body users c);

}
