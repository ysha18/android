package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int timerValue = 10;
    TextView timer;
    CountDownTimer cdt;
    Button btn;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        timer = findViewById(R.id.timer);

        timer.setText(getTime(timerValue));

        seekBar.setMax(300);
        seekBar.setProgress(timerValue);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                // stop timer if progress changes
                if(cdt!=null && btn!=null){
                    stopTimer(cdt,btn);
                    if(mediaPlayer!=null)
                        mediaPlayer.stop();
                }

                timer.setText(getTime(progress));
                timerValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // block less than 10 sec
                if(seekBar.getProgress()<10){
                    seekBar.setProgress(10);
                }
            }
        });

    }

    public void start(View view) throws InterruptedException {
        btn = findViewById(R.id.btnOk);
        String txt = (String)btn.getText();
        if(txt.equals("START")){
            btn.setText("STOP");

            cdt = new CountDownTimer(timerValue*1000,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    timerValue--;
                    timer.setText(getTime(Integer.valueOf(String.valueOf(millisUntilFinished/1000))));

                }

                @Override
                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                    mediaPlayer.start();
                    timerValue = 10;
                    seekBar.setProgress(timerValue);
                    timer.setText(getTime(timerValue));

                    stopTimer(cdt,btn);
                }
            }.start();
        } else if (txt.equals("STOP")){
            stopTimer(cdt,btn);
        }

    }

    private void stopTimer(CountDownTimer cdt, Button btn) {
        cdt.cancel();
        btn.setText("START");
    }


    public String getTime(int progress) {
        if(progress < 60){
            return "0:"+(String.valueOf(progress).length()==1 ? "0"+progress : progress+"");
        } else{
            int seconds = progress%60;
            String secs = String.valueOf(seconds).length()==1 ? "0"+seconds : seconds+"";
            return progress/60 + ":" + secs;
        }
    }
}
