package com.harold.layout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showHomer(View view){
        Log.i("Info","You clicked n the image");

        ImageView bart = (ImageView) findViewById(R.id.bart);
//        bart.animate().alpha(bart.getAlpha()==1 ? 0 : 1).setDuration(2000);
//
//        ImageView homer = (ImageView) findViewById(R.id.homer);
//        homer.animate().alpha(homer.getAlpha()==1 ? 0 : 1).setDuration(2000);
        bart.animate().translationX(1100).setDuration(1000);
        bart.animate().rotation(1440).setDuration(1000);

    }

}
