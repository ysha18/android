package com.harold.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int n = 0;
    Button btnPlayAgain;
    EditText txtNumber;

    public void verifyNumber(View view){
         txtNumber = (EditText) findViewById(R.id.txtNumber);
         if(n < Integer.parseInt(txtNumber.getText().toString())){
            Toast.makeText(this, "Higher", Toast.LENGTH_SHORT).show();
        } else  if (n > Integer.parseInt(txtNumber.getText().toString())){
            Toast.makeText(this, "Lower", Toast.LENGTH_SHORT).show();
        } else{
             txtNumber.setEnabled(false);
             Toast.makeText(this, "Wow, you GUESSED IT!", Toast.LENGTH_LONG).show();
             btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
             btnPlayAgain.setVisibility(View.VISIBLE);
         }

    }

    public void playAgain(View view){
        generateNumber();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateNumber();
        setContentView(R.layout.activity_main);
    }

    public void generateNumber(){
        // Generate the number
        Random rand = new Random();
        // Obtain a number between [0 - 49].
        n = rand.nextInt(51);
        if(txtNumber!=null)
            txtNumber.setEnabled(true);

        if(btnPlayAgain!=null)
            btnPlayAgain.setVisibility(View.INVISIBLE);
    }
}
