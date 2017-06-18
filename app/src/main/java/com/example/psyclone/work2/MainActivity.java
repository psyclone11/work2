package com.example.psyclone.work2;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadData().execute();
    }
    private class LoadData extends AsyncTask<Void, Void,Void>{
        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected  Void doInBackground(Void... params) {
            final  String BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
            final  String QUERY_PARAM = "q";
            final  String MAX_RESULTS = "maxResults";
            final  String PRINT_TYPE = "printType";

            Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, "pride+prejudice")
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            URL requestURL = null;
            try {
                requestURL = new URL(builtURI.toString());
                HttpURLConnection conn = null;
                conn = (HttpURLConnection) requestURL.openConnection();
                conn.setReadTimeout(1000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                int response = conn.getResponseCode();
                InputStream is = conn.getInputStream();

                BufferedReader r = new BufferedReader(new InputStreamReader(is));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) !=null){
                    total.append(line).append('\n');
                }

                String stringResult = total.toString();
                Log.i("json", stringResult);
                JSONObject jsonObject = new JSONObject(stringResult);
                JSONArray itemArray  = jsonObject.getJSONArray("items");
                
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


    }


}
