package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import me.jeremy.fithub.TalkToServer;

public class Search extends AppCompatActivity {

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    String s = formURL("LEG_PRESS","Search","Exercise");


    public String formURL(String eName, String requestType,String searchType){
        //hard code googleid until figure out how to grab it from sign o
        return baseURL + "?requestType="+requestType+"&exerciseName="+eName +"&searchType=" + searchType;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String str = "test";

        try {

            // Add date to param string
            str = new TalkToServer().execute(s).get();
            JSONObject jobj = new JSONObject(str);


            TextView testReq = (TextView) findViewById(R.id.test);
            testReq.setText(str);



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
        }

    }
}
