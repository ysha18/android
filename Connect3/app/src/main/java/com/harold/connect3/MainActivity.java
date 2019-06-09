package com.harold.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 1 => red, 2=> yellow, 0 => empty
    String activePlayer = "1";

    public void diveIn(View view){

        ImageView piece = (ImageView)view;

        // piece movement
        piece.setImageResource(R.drawable.red);
        piece.setTranslationY(-1500);
        piece.animate().rotation(2400).translationYBy(1500);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
