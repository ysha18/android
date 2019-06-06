package com.harold.loginproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void onClickBtnLogin(View view){
        EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        EditText txtPwd = (EditText) findViewById(R.id.txtPassword);

        if(txtUserName.getText().toString().equals("admin") && txtPwd.getText().toString().equals("123")){
            Log.i("Info", "Login successful");
        } else {
            Log.i("Warning", "Error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
