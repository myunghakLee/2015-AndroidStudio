package com.example.myunghak.fifthweek3;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    double lat;
    double lng;
    int first = 0;
    private GoogleMap map;
    TextView logView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("Main", "onCreate");

        logView = (TextView) findViewById(R.id.log);
        logView.setText("GPS 가 잡혀야 좌표가 구해짐");

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // GPS 프로바이더 사용가능여부
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 네트워크 프로바이더 사용가능여부
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Log.d("Main", "isGPSEnabled=" + isGPSEnabled);
        Log.d("Main", "isNetworkEnabled=" + isNetworkEnabled);

        LocationListener locationListener = new LocationListener() {
            Marker location_mark;
            public void onLocationChanged(Location location) {

                lat = location.getLatitude();
                lng = location.getLongitude();

                if(lat>0 && lng>0 && first ==0) {
                    first =1;
                    LatLng MyLocation = new LatLng(lat, lng);
                    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                            .getMap();
                    location_mark = map.addMarker(new MarkerOptions().position(MyLocation)
                            .title("MyLocation(" + lat + lng + ")"));


                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(MyLocation, 15));

                    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
                }
                else if(first ==1)
                {
                    location_mark.remove();
                    LatLng MyLocation = new LatLng(lat, lng);

                    location_mark = map.addMarker(new MarkerOptions().position(MyLocation)
                            .title("MyLocation(" + lat + lng + ")"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(MyLocation));
                }
              }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                logView.setText("onStatusChanged");
            }

            public void onProviderEnabled(String provider) {
                logView.setText("onProviderEnabled");
            }

            public void onProviderDisabled(String provider) {
                logView.setText("onProviderDisabled");
            }
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        // 수동으로 위치 구하기
        String locationProvider = LocationManager.GPS_PROVIDER;
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastKnownLocation != null) {
            double lng = lastKnownLocation.getLatitude();
            double lat = lastKnownLocation.getLatitude();
            Log.d("Main", "longtitude=" + lng + ", latitude=" + lat);
            logView.setText("longtitude=" + lng + ", latitude=" + lat);
        }
    }
}