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
import android.widget.TextView;

import com.example.menno.shakeit.Connector.ApiJSONStrategy;
import com.example.menno.shakeit.Connector.ApiStrategy;
import com.example.menno.shakeit.Connector.ExecuteAsync;
import com.example.menno.shakeit.Connector.LoginUser;
import com.example.menno.shakeit.MainActivity;
import com.example.menno.shakeit.Models.Globals;
import com.example.menno.shakeit.R;

import org.json.JSONException;

public class Login extends AppCompatActivity {
    ExecuteAsync controller = new ExecuteAsync();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.btnLogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });

        TextView register = (TextView) findViewById(R.id.registerLink);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }

    public void login(View v){
        EditText userName = (EditText) findViewById(R.id.etUser);
        EditText password = (EditText) findViewById(R.id.etPassword);

        try {
            ApiStrategy login = new LoginUser(userName.getText().toString(), password.getText().toString());
            controller.setStrategy(login);
            controller.executeStrategy(Login.this);
        } catch (JSONException e) {
            e.printStackTrace();
            TextView fail = (TextView) findViewById(R.id.tvLoginFail);
            fail.setVisibility(View.VISIBLE);
        }

    }


}
