package com.harold.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view){

        EditText amountTxt = (EditText) findViewById(R.id.amountText);
        double result = Double.parseDouble(amountTxt.getText().toString()) * 570;
        result = Math.round(result);
        Log.i("in XOF: " , result + "" );
        Toast.makeText(this,"in XOF: " + result, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
