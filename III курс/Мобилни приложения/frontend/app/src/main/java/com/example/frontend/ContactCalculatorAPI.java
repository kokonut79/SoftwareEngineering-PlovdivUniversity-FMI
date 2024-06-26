package com.example.frontend;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface ContactCalculatorAPI {
    public class message{
        public String status;
        public message(ContactCalculatorAPI.message m){
            this.status = m.status;
        }
        public message(String _status){
            this.status = _status;
        }
    }
    public class calculator {
        public int first_num;
        public String operation;
        public int second_num;
        public String result;

        public String getResult() {
            return result;
        }

        public String getOperation() {
            return operation;
        }

        public calculator(calculator m){
            first_num = m.first_num;
            operation = m.operation;
            second_num = m.second_num;
        }
        public calculator(int _first_num, String _operation,  int _second_num){
            first_num = _first_num;
            operation = _operation;
            second_num = _second_num;
        }

    }


    @POST("/calculator/")
    public Call<ContactCalculatorAPI.calculator> api_calculate(@Body ContactCalculatorAPI.calculator c);

    @GET("/calculator/")
    public Call<String> getOperation();

    @GET("/getresult/")
    public Call<String> getResult();


}
