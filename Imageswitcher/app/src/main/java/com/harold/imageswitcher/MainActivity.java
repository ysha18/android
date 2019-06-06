package com.harold.imageswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void buttonClicked(View view){
        ImageView cr7 = (ImageView) findViewById(R.id.imageView);
        cr7.setImageResource(R.drawable.fk);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
