package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.os.AsyncTask;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import me.jeremy.fithub.TalkToServer;

public class Search extends AppCompatActivity {

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    String s = formURL("LEG PRESS","Search","Exercise");


    public String formURL(String eName, String requestType,String searchType){
        //hard code googleid until figure out how to grab it from sign o
        return baseURL + "?requestType="+requestType+"&exerciseName="+eName +"&searchType=" + searchType;

    }

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String str = "test";

        try {
            // Add date to param string
            s = s.replaceAll("\\s+","+");
            Log.d("SEARCHSTRING:",s);
            str = new TalkToServer().execute(s).get();

            Log.d("RESULT:",str);
            JSONObject jobj = new JSONObject(str);
            //Log.d("SEARCHSTRING:",str);


           // JSONArray res = new JSONArray(jobj);


            //TextView testReq = (TextView) findViewById(R.id.test);

            TextView testReq = (TextView) findViewById(R.id.search);

            testReq.setText(str);



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
        }

        ListView lv = (ListView)findViewById(R.id.searchList);
        ArrayList<String> arrayExercise = new ArrayList<>();
        arrayExercise.addAll(Arrays.asList(getResources().getStringArray(R.array.exercise)));

        adapter = new ArrayAdapter<>(
                Search.this,
                android.R.layout.simple_list_item_1,
                arrayExercise);
        lv.setAdapter(adapter);

        //@Override
        //public boolean onCreateOptionsMenu(Menu menu) {
        //    MenuInflater inflater = getMenuInflater();
        //    return super.onCreateOptionsMenu(menu);
        //}
    }
}
