package com.example.downloadingwebcontents;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            Log.i("URL",strings[0]);
            return "Done";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String result = null;

        DownloadTask task =  new DownloadTask();
        try{
            result = task.execute("http://www.zappycode.com").get();
        } catch (Exception e){
            Log.i("Excepetion: ", e.getMessage());
        }

        Log.i("RESULT",result);
    }
}
