/** Copyright 2017 cozyplanes

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.example.locasend1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener
{
    LocationManager locationManager;
    String mprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button _share = (Button)findViewById(R.id.share);
        _share.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                //sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, LONGITUDE_HERE); // longitude.setText(" " + location.getLongitude());
                //sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, LATITUDE_HERE); //  latitude.setText(" " + location.getLatitude());
                if(LONGITUDE_HERE!=null&&LATITUDE_HERE!=null) // not needed but just for better performance without issues in some devices
                {
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, LONGITUDE_HERE);
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, LATITUDE_HERE);
                }
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals(""))
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { return; }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this); // Parameters : String provider, long minTime, float minDistance, LocationListener listener

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(),
                "Location not found, Check your GPS settings and make sure location access permission for this app is turned on.",
                Toast.LENGTH_LONG).show();
        }
    }

    //public  static double LONGITUDE_HERE= Double.parseDouble("");
    //public  static double LATITUDE_HERE= Double.parseDouble("");

    String LONGITUDE_HERE="";
    String LATITUDE_HERE="";
    @Override
    public void onLocationChanged(Location location)
    {
        TextView longitude = (TextView) findViewById(R.id.longitude_placeholder);
        TextView latitude = (TextView) findViewById(R.id.latitude_placeholder);

        longitude.setText("Longitude :" + location.getLongitude());
        latitude.setText("Latitude : " + location.getLatitude());

        LONGITUDE_HERE= String.valueOf(location.getLongitude());
        LATITUDE_HERE= String.valueOf(location.getLatitude());

    }

    /*
    @Override
    public void onLocationChanged(Location location)
    {
        TextView longitude = (TextView) findViewById(R.id.longitude_placeholder);
        TextView latitude = (TextView) findViewById(R.id.latitude_placeholder);



        longitude.setText(" " + location.getLongitude());
        latitude.setText(" " + location.getLatitude());
    }
    */

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) { }

    @Override
    public void onProviderEnabled(String s) { }

    @Override
    public void onProviderDisabled(String s) { }
}