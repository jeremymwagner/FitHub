package me.jeremy.fithub;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;
import javax.net.ssl.HttpsURLConnection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Axeldama on 2/14/17.
 */

public class TalkToServer extends AsyncTask<String,Void,String> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(String... urls) {
        String stringUrl = urls[0];
        String result;
        String inputLine;
        try {
            URL myURL = new URL(stringUrl);

            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();

            conn.setRequestMethod(REQUEST_METHOD);
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);

            conn.connect();

            InputStreamReader isr = new InputStreamReader(conn.getInputStream());

            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while((inputLine = reader.readLine()) != null){
                sb.append(inputLine);
            }

            reader.close();
            isr.close();

            result = sb.toString();
            Log.d("Str test", result);
        }catch(MalformedURLException e){
            e.printStackTrace();
            result = null;
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute("");
    }
}
