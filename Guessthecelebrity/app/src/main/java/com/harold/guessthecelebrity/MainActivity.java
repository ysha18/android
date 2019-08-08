package com.harold.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = null;
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data!=-1){
                    char current =(char)data;
                    result += current;
                    data = reader.read();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {


            }
            return result;
        }
    }

    public class DownloadImageTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... celeb) {


            try {
                URL url = new URL(celeb[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();

                Bitmap myBitmap = BitmapFactory.decodeStream(in);

                imageView.setImageBitmap(myBitmap);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "DONE";
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        try {
            generateImage();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void generateImage() throws ExecutionException, InterruptedException {

        // get HTMl of all images and names
        String result = null;
        DownloadTask task = new DownloadTask();
        result = task.execute("http://www.posh24.se/kandisar").get();
//        System.out.println(result);

        /*
        *  Using String manipulation, create 2 maps: id->name and id->url
        */
        Map<String, String> mapCelebs = new HashMap<>();

        Pattern p = Pattern.compile("<div class=\"image\">"+"(?s)"+"(.*?)"+"</div>");
        Matcher m = p.matcher(result);
        List<String> celebs = new ArrayList<>();

        while(m.find()){
//            System.out.println(m.group(1));
            celebs.add(m.group(1));
        }


        Pattern pLink = Pattern.compile("<img src=\""+"(?s)"+"(.*?)"+"\"");
        Pattern pName = Pattern.compile("alt=\""+"(?s)"+"(.*?)"+"\"");
        Matcher mLink,mName;
        String name = null;
        for(int i = 0; i < celebs.size(); i++){
            mName = pName.matcher((celebs.get(i)));
            while(mName.find()){
                name = mName.group(1);
            }
            mLink = pLink.matcher(celebs.get(i));
            while(mLink.find()){
                mapCelebs.put(name,mLink.group(1));
            }
        }


        /*
         * randomly get id to display, get name and url
         */
            DownloadImageTask imageTask = new DownloadImageTask();
            imageTask.execute(mapCelebs.get("Kim Kardashian"));


        // randomly get 3 ids with names names


        // display all
    }
}
