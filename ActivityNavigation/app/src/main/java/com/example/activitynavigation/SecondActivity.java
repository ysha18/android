package com.example.activitynavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView txtName = findViewById(R.id.txtName);
        txtName.setText(getIntent().getStringExtra("name"));
        TextView txtAge = findViewById(R.id.txtAge);
        txtAge.setText(String.valueOf(getIntent().getIntExtra("age",0)));
        TextView txtAddress = findViewById(R.id.txtAddress);
        txtAddress.setText(getIntent().getStringExtra("address"));
}

    public void goBack(View view){
//        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(intent);
        finish();
    }
}
