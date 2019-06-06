package com.harold.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){

        EditText nameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText pwdEditText = (EditText) findViewById(R.id.pwdEditText);
        Log.i("USERNAME: ", nameEditText.getText().toString() );
        Log.i("PASSWORD: ", pwdEditText.getText().toString() );

        Toast.makeText(this,"Hi there", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
