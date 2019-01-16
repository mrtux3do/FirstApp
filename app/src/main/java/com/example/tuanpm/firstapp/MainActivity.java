package com.example.tuanpm.firstapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
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
                new AsyncTaskNetwork(email.getText().toString(), password.getText().toString()).execute("http://192.168.0.74/REST/users/login.json");
            }
        });
    }

    protected class AsyncTaskNetwork extends AsyncTask<String, Void, String>{

        OkHttpClient client = new OkHttpClient();
        String email, password;

        public AsyncTaskNetwork(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... params) {
            RequestBody formBody = new FormBody.Builder()
                    .add("email", email)
                    .add("password", password)
                    .build();
            Request request = new Request.Builder()
                    .url(params[0]).post(formBody)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    String error = e.getMessage();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                   String obj = response.body().string();
                }
            });
            return null;
        }

    }

}
