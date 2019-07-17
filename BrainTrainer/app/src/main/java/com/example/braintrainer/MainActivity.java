package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout goLayout, gameLayout;
    Button btnPlayAgain,btnTimer, btnScore;
    TextView txtScore;
    CountDownTimer cdt;
    final int time = 11000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goLayout = findViewById(R.id.goLayout);
        gameLayout = findViewById(R.id.gameLayout);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        txtScore = findViewById(R.id.txtScore);
        btnTimer = findViewById(R.id.btnTimer);
        btnScore = findViewById(R.id.btnScore);

    }

    public void start(View view){
        // visibility of the game
        goLayout.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        txtScore.setVisibility(View.INVISIBLE);

        gameLayout.setVisibility(View.VISIBLE);


        // generate question and answers


        // start timer
        cdt = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // display countdown
                btnTimer.setText(getTime(String.valueOf(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                // Display score
            }
        }.start();
    }

    private String getTime(String timeLeft) {
        String ret = String.valueOf(Integer.parseInt(timeLeft)/1000);
        return "0:" + (ret.length()==1 ? "0"+ret : ret);

    }

}
