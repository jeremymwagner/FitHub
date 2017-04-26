package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import me.jeremy.fithub.TalkToServer;

public class Search extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter1;

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    String s = formURL("LEG PRESS","Search","Exercise");


    public String formURL(String eName, String requestType,String searchType){
        //hard code googleid until figure out how to grab it from sign o
        return baseURL + "?requestType="+requestType+"&exerciseName="+eName +"&searchType=" + searchType;

    }

    public String workoutformURL(String wName, String requestType,String searchType){
        //hard code googleid until figure out how to grab it from sign o
        return baseURL + "?requestType="+requestType+"&workoutName="+wName +"&searchType=" + searchType;

    }

    public String peopleformURL(String userID, String requestType,String friendType){
        //hard code googleid until figure out how to grab it from sign o
        return baseURL + "?requestType="+requestType+"&userID="+userID +"&friendType=" + friendType;

    }

    ArrayAdapter<String> adapter;
    ListView lv ;
    EditText et;
    ArrayList<String> arrayExercise;
    ArrayList<Workout> wList = new ArrayList<Workout>();
    String[] items;
    String text;
    ArrayList<Exercise> eList= new ArrayList<Exercise>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String str = "test";

        spinner = (Spinner)findViewById(R.id.searchSpinner);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.search, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);

        text = spinner.getSelectedItem().toString();




        lv = (ListView)findViewById(R.id.searchList);
        et = (EditText)findViewById(R.id.txtsearch);

        Button submit = (Button) findViewById(R.id.searchButton);
        submit.setVisibility(Button.VISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text = spinner.getSelectedItem().toString();
                eList= new ArrayList<Exercise>();
                wList = new ArrayList<Workout>();

                
                //
                // Handling of JSON strings into objects
                //
                if(text.equals("Exercise")) {
                    s = formURL(et.getText().toString(), "Search", text);
                    initList();

                    try {
                        // Add date to param string
                        s = s.replaceAll("\\s+", "+");
                        Log.d("SEARCHSTRING:", s);
                        String str = new TalkToServer().execute(s).get();

                        Log.d("RESULT:", str);
                        JSONObject jObject = new JSONObject(str);
                        Log.d("JSON:", jObject.toString());
                        Iterator<?> keys = jObject.keys();


                        while (keys.hasNext()) {
                            String key = (String) keys.next();
                            if (jObject.get(key) instanceof JSONObject) {
                                Log.d("ARRAY:", jObject.get(key).toString());
                                JSONObject exercise = new JSONObject(jObject.get(key).toString());
                                Log.d("EXER:", exercise.toString());
                                Iterator<?> ekeys = exercise.keys();
                                String ekey = (String) ekeys.next();
                                String val = exercise.get(ekey).toString();
                                JSONObject eval = new JSONObject(val);
                                Log.d("VAL:", val);


                                Exercise e = new Exercise(eval.get("EXERCISE_NAME").toString(), eval.get("EXERCISE_HOW").toString(), eval.get("EXERCISE_MGROUP").toString());
                                eList.add(e);
                                Log.d("LIST:", eList.get(0).getDescription());

                            }
                        }

                        ;



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(text.equals("Workout")) {
                    s = workoutformURL(et.getText().toString(), "Search", text);
                    initList();

                    try {
                        // Add date to param string
                        s = s.replaceAll("\\s+", "+");
                        Log.d("SEARCHSTRING:", s);
                        String str = new TalkToServer().execute(s).get();

                        Log.d("RESULT:", str);
                        JSONObject jObject = new JSONObject(str);
                        Log.d("JSON:", jObject.toString());
                        Iterator<?> keys = jObject.keys();


                        while (keys.hasNext()) {
                            String key = (String) keys.next();
                            Workout w = new Workout();
                            w.setWID(Integer.parseInt(key));
                            if (jObject.get(key) instanceof JSONObject) {
                                Log.d("ARRAY:", jObject.get(key).toString());
                                JSONObject workout = new JSONObject(jObject.get(key).toString());
                                Log.d("EXER:", workout.toString());
                                Iterator<?> ekeys = workout.keys();
                                String ekey = (String) ekeys.next();


                                String wName = workout.get(ekey).toString();
                                ArrayList<ExerciseByID> eidlist = new ArrayList<ExerciseByID>();
                                String author = "";
                                while(ekeys.hasNext()) {
                                    String a = (String) ekeys.next();
                                    Log.d("EID", a);
                                    int b = Integer.parseInt(a);
                                    ExerciseByID neid = new ExerciseByID(b);
                                    if(workout.get(a) instanceof JSONObject){
                                        JSONObject eval = new JSONObject(workout.get(a).toString());
                                        Log.d("VAL:", eval.toString());
                                        //w.setAuthor(eval.get("W_NAME").toString());
                                        int s = Integer.valueOf(eval.get("W_SETS").toString());
                                        int r = Integer.valueOf(eval.get("W_REPS").toString());
                                        author = eval.get("W_AUTHOR").toString();

                                        neid.setSets(s);
                                        neid.setReps(r);
                                        eidlist.add(neid);
                                    }
                                }

                                w.setExerciseList(eidlist);
                                w.setAuthor(author);
                                w.setName(wName);
                                Log.d("WNAME", w.getName());

                                wList.add(w);
                            }
                        }

                        ;


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        initList();
        /*
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")) {
                    //reset listview
                    initList();
                }
                else {
                    //perform search
                    searchItem(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/

    }

    public void initList() {
//        arrayExercise = new ArrayList<>();
//        arrayExercise.addAll(Arrays.asList(getResources().getStringArray(R.array.exercise)));
//
//        adapter = new ArrayAdapter<>(
//                Search.this,
//                android.R.layout.simple_list_item_1,
//                arrayExercise);
//        lv.setAdapter(adapter);
        //items = new String[]{text};

        if(text.equals("Exercise")) {


            ExerciseAdapter adapter = new ExerciseAdapter(this, eList);
            lv.setAdapter(adapter);
        }
        else if(text.equals("Workout")) {
            items = new String[]{"List of workouts here"};
            WorkoutAdapter wadapter = new WorkoutAdapter(this,wList);
            lv.setAdapter(wadapter);
        }
        else if(text.equals("Friend")) {
            items = new String[]{"No Friends :("};
        }
        else {
            items = new String[]{"Error"};
        }


       // ExerciseAdapter adapter = new ExerciseAdapter(this, eList);
        //lv.setAdapter(adapter);
    }

    public void searchItem(String textToSearch) {
        for(String item:items) {
            if(!item.toLowerCase().contains(textToSearch.toLowerCase())) {
                arrayExercise.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
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
