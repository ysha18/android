package com.example.downloadingwebcontents;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
//            Log.i("URL",strings[0]);

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data!=-1){
                    char current = (char)data;
                    result += current;
                    data = reader.read();
                }

                return result;


            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }


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
            Log.i("Exception: ", e.getMessage());
        }

        Log.i("RESULT",result);
    }
}
