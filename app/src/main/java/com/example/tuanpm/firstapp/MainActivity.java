package com.example.tuanpm.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private static EditText email;
    private static EditText password;
    private static Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton();
    }

    public void loginButton(){
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btnLogin);

        //handle button click
        btn_login.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                callAPI(email.getText().toString(),password.getText().toString());
            }
        });
    }

    public void callAPI(String email, String password){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add(email,password)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.0.68/REST/users/login.json")
                .post(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.e("TAG",response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
