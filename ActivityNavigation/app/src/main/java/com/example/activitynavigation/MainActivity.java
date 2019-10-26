package com.example.activitynavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mlv = findViewById(R.id.myListView);

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Nicolas");
        friends.add("Malick");
        friends.add("Suni");
        friends.add("William");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
        mlv.setAdapter(arrayAdapter);

        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                Log.i("Position clicked: " , position+"");
                switch (position){
                    case 0: intent.putExtra("name", "Nicolas");
                        intent.putExtra("age", 30);
                        intent.putExtra("address", "Luxemburg");
                    break;

                    case 1: intent.putExtra("name", "Malick");
                        intent.putExtra("age", 31);
                        intent.putExtra("address", "Paris");
                        break;
                    case 3: intent.putExtra("name", "Suni");
                        intent.putExtra("age", 34);
                        intent.putExtra("address", "BHM");
                        break;
                    case 4: intent.putExtra("name", "William");
                        intent.putExtra("age", 31);
                        intent.putExtra("address", "BHM");
                        break;
                }
                startActivity(intent);
            }
        });

    }


}
