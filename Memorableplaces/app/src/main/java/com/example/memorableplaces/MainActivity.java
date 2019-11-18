package com.example.memorableplaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewPlaces;
    static ArrayList<String> listPlaces = new ArrayList<>();
    static ArrayList<LatLng> locations = new ArrayList<>();
    static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewPlaces = findViewById(R.id.listViewPlaces);
        listPlaces.add("Add a new place");
        locations.add(new LatLng(0,0));
        if(getIntent().getStringArrayListExtra("placesAddresses")!=null)
            listPlaces.addAll(getIntent().getStringArrayListExtra("placesAddresses"));

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listPlaces);
        listViewPlaces.setAdapter(arrayAdapter);

        listViewPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("placeNumber",position);
                startActivity(intent);
            }
        });

    }
}
