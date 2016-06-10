package com.example.menno.shakeit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.menno.shakeit.Connector.ApiStrategy;
import com.example.menno.shakeit.Connector.ExecuteAsync;
import com.example.menno.shakeit.Connector.GetLocationList;
import com.example.menno.shakeit.Models.Globals;
import com.example.menno.shakeit.View.GetRandom;
import com.example.menno.shakeit.View.Login;
import com.example.menno.shakeit.View.SaveLocation;

public class MainActivity extends AppCompatActivity implements ShakeEvent.ShakeListener, VariableChangeListener {
    private ShakeEvent sd;
    private ExecuteAsync controller = new ExecuteAsync();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Globals global = Globals.getInstance();
        global.setVariableChangeListener(MainActivity.this);

        if(!Globals.loggedin){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }

        sd = new ShakeEvent();
        sd.setListener(this);
        sd.init(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onShake() {
        if(!Globals.shaked){
            getLocation();
        }

    }

    public void getLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        double longitude = 0;
        double latitude = 0;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            longitude = location.getLongitude();
            latitude = location.getLatitude();

            Globals.shaked = true;

            Intent intent = new Intent(MainActivity.this, SaveLocation.class);
            intent.putExtra("longitude", longitude);
            intent.putExtra("latitude", latitude);
            startActivity(intent);
        } else{

        }

    }

    public void getRandom(View v){
        Intent intent = new Intent(MainActivity.this, GetRandom.class);
        startActivity(intent);
    }


    @Override
    public void onVariableChanged(int userId) {
        ApiStrategy getList = new GetLocationList(userId);
        controller.setStrategy(getList);
        controller.executeStrategy();
    }
}


