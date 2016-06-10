package com.example.menno.shakeit.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.menno.shakeit.Connector.ApiStrategy;
import com.example.menno.shakeit.Connector.ExecuteAsync;
import com.example.menno.shakeit.Connector.SaveRegistration;
import com.example.menno.shakeit.R;

import org.json.JSONException;

public class Register extends AppCompatActivity {
    ExecuteAsync controller = new ExecuteAsync();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button register = (Button) findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(v);
            }
        });

    }

    public void registerUser(View v){
        EditText userName = (EditText) findViewById(R.id.etUser);
        EditText password = (EditText) findViewById(R.id.etPassword);
        EditText password2 = (EditText) findViewById(R.id.etPassword2);

        if(password.getText().toString().equals(password2.getText().toString())){
            try {
                ApiStrategy registerUser = new SaveRegistration(userName.getText().toString(), password.getText().toString());
                controller.setStrategy(registerUser);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
