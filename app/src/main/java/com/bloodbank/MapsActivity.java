package com.bloodbank;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.bloodbank.EventActivities.EventsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        GPSTracker gpsTracker = new GPSTracker(MapsActivity.this);
        Location locatio = gpsTracker.getLocation();
        Double lat = 0.0, lat_long = 0.0;
        if (locatio != null) {
            lat = locatio.getLatitude();
            lat_long = locatio.getLongitude();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double lat  = Double.valueOf(getIntent().getStringExtra("latitude"));
        Double longitude  = Double.valueOf(getIntent().getStringExtra("longitude"));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Blood Bank found here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(2));
       /* BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.current_position_tennis_ball)

        MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                .title("Current Location")
                .snippet("Thinking of finding some thing...")
                .icon(icon);

        mMarker = googleMap.addMarker(markerOptions);*/

    }
}
