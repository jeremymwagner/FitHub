package me.jeremy.fithub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class FollowingFollowers extends AppCompatActivity {

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    String s = formURL("LEG PRESS","Search","Exercise");


    public String formURL(String eName, String requestType,String searchType){
        //hard code googleid until figure out how to grab it from sign o
        return baseURL + "?requestType="+requestType+"&exerciseName="+eName +"&searchType=" + searchType;

    }

    ArrayAdapter<String> adapter;
    ListView lv;
    ArrayList<String> arrayFollowers;
    String[] items;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //String str = "test";

        lv = (ListView)findViewById(R.id.followersList);

        try {
            // Add date to param string
            s = s.replaceAll("\\s+","+");
            Log.d("SEARCHSTRING:",s);
            String str = new TalkToServer().execute(s).get();

            Log.d("RESULT:",str);
            JSONObject jObject = new JSONObject(str);
            Log.d("JSON:",jObject.toString());
            Iterator<?> keys = jObject.keys();

            while( keys.hasNext() ) {
                String key = (String)keys.next();
                if ( jObject.get(key) instanceof JSONObject ) {
                    Log.d("ARRAY:", jObject.get(key).toString());
                }
            }



            // JSONArray res = new JSONArray(jobj);


            //TextView testReq = (TextView) findViewById(R.id.test);

            //TextView testReq = (TextView) findViewById(R.id.search);

            //testReq.setText(str);



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch(JSONException e){
            e.printStackTrace();
        }

        initList();
    }

    public void initList() {

        items = new String[]{"Joe", "Bob", "Jessica", "Frank", "Rachel", "Patrick", "Sandy", "Cloud"};


        arrayFollowers=new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayFollowers);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //do anything to get search result here
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
