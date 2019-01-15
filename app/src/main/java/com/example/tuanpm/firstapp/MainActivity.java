package com.example.tuanpm.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static EditText username;
    private static EditText password;
    private static Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton();
    }

    public void loginButton(){
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.btnLogin);
        btn_login.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                Log.v("TAG", username.getText().toString());
            }
        });
    }

}
