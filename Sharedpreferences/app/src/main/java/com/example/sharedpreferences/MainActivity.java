package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);
//        sharedPreferences.edit().putString("test","test").apply();
        String s = sharedPreferences.getString("test","default");



//        ArrayList<String> list = new ArrayList<>(Arrays.asList("Harold","Esy","David","Myriam"));
//        try {
//            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(list)).apply();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String s_friends = sharedPreferences.getString("friends","default");
        try {
            ArrayList<String> list = (ArrayList<String>) ObjectSerializer.deserialize(s_friends);
            Log.i("SAVED PREF LIST FRIENDS", list.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("SAVED PREF", s);

    }
}
