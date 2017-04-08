package com.satyam.gpsexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button b_get;
    private GPSTracker gps;
    double longitude;
    double latitude;
    TextView txt_latitude,txt_longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_get = (Button)findViewById(R.id.get);
        txt_latitude= (TextView) findViewById(R.id.txt_latitude);
        txt_longitude= (TextView) findViewById(R.id.txt_longitude);
        //For marshmallow permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        b_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps = new GPSTracker(MainActivity.this);
                if(gps.canGetLocation()){

                    longitude = gps.getLongitude();
                    latitude = gps .getLatitude();
                    txt_latitude.setText("Your Latitude="+latitude);
                    txt_longitude.setText("Your Longitude="+longitude);
                }
                else
                {
                    gps.showSettingsAlert();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        gps.stopUsingGPS();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
}