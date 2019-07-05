package com.example.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    MediaPlayer mp1, mp2, mp3, mp4, mp5, mp6, mp7, mp8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mp1 = MediaPlayer.create(this, R.raw.doyouspeakenglish);
//        mp2 = MediaPlayer.create(this, R.raw.goodevening);
//        mp3 = MediaPlayer.create(this, R.raw.hello);
//        mp4 = MediaPlayer.create(this, R.raw.howareyou);
//        mp5 = MediaPlayer.create(this, R.raw.ilivein);
//        mp6 = MediaPlayer.create(this, R.raw.mynameis);
//        mp7 = MediaPlayer.create(this, R.raw.please);
//        mp8 = MediaPlayer.create(this, R.raw.welcome);

    }

    public void playSound(View view){
        Button btn = (Button) view;
//        int tag = Integer.parseInt((String) btn.getTag());
//        Log.i("Tag: ",tag+"");
//        switch (tag){
//            case 1 : mp1.start();
//            break;
//            case 2 : mp2.start();
//                break;
//            case 3 : mp3.start();
//                break;
//            case 4 : mp4.start();
//                break;
//            case 5 : mp5.start();
//                break;
//            case 6 : mp6.start();
//                break;
//            case 7 : mp7.start();
//                break;
//            case 8 : mp8.start();
//                break;
//            default: break;
//        }

        MediaPlayer md = MediaPlayer.create(this, getResources().getIdentifier(btn.getTag().toString(), "raw", getPackageName()));

        md.start();

    }
}
