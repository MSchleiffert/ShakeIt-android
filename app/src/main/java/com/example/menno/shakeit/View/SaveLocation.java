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

import com.example.menno.shakeit.Connector.ApiJSONStrategy;
import com.example.menno.shakeit.Connector.ApiStrategy;
import com.example.menno.shakeit.Connector.ExecuteAsync;
import com.example.menno.shakeit.Connector.SaveLocationStrat;
import com.example.menno.shakeit.Models.Globals;
import com.example.menno.shakeit.R;

import org.json.JSONException;

public class SaveLocation extends AppCompatActivity {
    ExecuteAsync controller = new ExecuteAsync();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        double longitude = intent.getDoubleExtra("longitude", 0);
        double latitude = intent.getDoubleExtra("latitude", 0);

        EditText etLongitude = (EditText) findViewById(R.id.longitude);
        EditText etLatitude = (EditText) findViewById(R.id.latitude);

        etLongitude.setText(Double.toString(longitude));
        etLatitude.setText(Double.toString(latitude));

        Button btn = (Button) findViewById(R.id.btnSave);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocation(v);
            }
        });

    }

    public void saveLocation(View v){
        EditText etLongitude = (EditText) findViewById(R.id.longitude);
        EditText etLatitude = (EditText) findViewById(R.id.latitude);
        EditText etLocationName = (EditText) findViewById(R.id.locationName);

        try {
            ApiStrategy saveLocation = new SaveLocationStrat(Globals.userId, etLocationName.getText().toString(), etLongitude.getText().toString(), etLatitude.getText().toString());
            controller.setStrategy(saveLocation);
            controller.executeStrategy();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Globals.shaked = false;
        finish();
        return;
    }

}
