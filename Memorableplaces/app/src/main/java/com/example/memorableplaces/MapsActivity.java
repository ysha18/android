package com.example.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,locationListener);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        if (!MainActivity.locations.isEmpty()){
            for(int i = 1; i < MainActivity.locations.size(); i++){
                displayMarker(MainActivity.locations.get(i), MainActivity.listPlaces.get(i));
            }
        }

        int position = getIntent().getIntExtra("placeNumber",0);
        switch (position){
            case 0: // go to user location
                locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
                locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        displayMarker(new LatLng(location.getLatitude(),location.getLongitude()),"My position");
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };
                break;
            case 1:
                break;

        }

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                try{
//                Toast.makeText(getApplicationContext(),"Long click activated", Toast.LENGTH_SHORT).show();
                // store latlong
                MainActivity.locations.add(latLng);
                // store address
                String address = getAddress(latLng);
                MainActivity.listPlaces.add(address);
                MainActivity.arrayAdapter.notifyDataSetChanged();
                displayMarker(latLng,address);
                } catch (Exception e){
                    Log.e("ERROR LONG CLICK", e.getMessage());
                }
            }
        });

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                displayMarker(lastKnownLocation,"Your location");
            } else{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }

    private void displayMarker(LatLng ll, String address) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ll).title(address);
        mMap.addMarker(markerOptions);
    }


    public String getAddress(LatLng latLng){
         Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try{
            List<Address> listAddresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String address = listAddresses.get(0).getThoroughfare() + " " + listAddresses.get(0).getLocality() + " " +
                    listAddresses.get(0).getPostalCode()  + " " + listAddresses.get(0).getAdminArea();
            return address;

        } catch (Exception e){
            Log.e("ERROR LONG CLICK", e.getMessage());
        }
        return  null;
    }

}
