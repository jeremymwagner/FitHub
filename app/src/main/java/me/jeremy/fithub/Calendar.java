package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import android.util.JsonReader;
import java.io.InputStream;
import android.app.DatePickerDialog;



import android.util.Log;
import android.os.AsyncTask;

import me.jeremy.fithub.TalkToServer;


import org.json.JSONObject;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    String s ;//= formURL(0117,"Calendar");
    //getRequest.execute(s);
    public static String wDate;
    /**
     *  Form url queries by grabbing startdate from selected calendar day
     * @param startDate
     * @param requestType
     * @return
     */
    public String formURL(String startDate, String requestType){
        //hard code googleid until figure out how to grab it from sign    109689297173623922729
           return baseURL + "?requestType="+requestType+"&startDate="+ startDate+"&googleId="+Login.uid;

    }


    public String eformURl(String requestType,String id) {
        return baseURL + "?requestType="+requestType+"&searchType=ExerciseID&exerciseID=" + id;
    }

    String author;
    int ns;
    int nr;
    String wname;
    List<ExerciseByID> eidlist = new ArrayList<ExerciseByID>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);



        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Toast.makeText(getApplicationContext(), month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_LONG).show();
                wDate = "" + Integer.toString(month + 1) + Integer.toString(dayOfMonth) + Integer.toString(year);
                //Prints out the date selected below the calendar
                TextView textElement = (TextView) findViewById(R.id.date);
                textElement.setText((month + 1) + "/" + dayOfMonth + "/" + year);

                String str = "test";

                try {

                    // Add date to param string
                    s = formURL(wDate,"Calendar");
                    str = new TalkToServer().execute(s).get();
                    Log.d("URL",str);
                    JSONObject jobj = new JSONObject(str);
                    Iterator<?> keys = jobj.keys();


                        String key = (String) keys.next();
                        Workout w = new Workout();
                        w.setWID(Integer.parseInt(key));
                        JSONObject wo = new JSONObject(jobj.get(key).toString());
                        Log.d("WO",wo.toString());
                        keys = wo.keys();
                    String wn = (String) keys.next();
                    wname = wo.get(wn).toString();
                    while(keys.hasNext()) {

                        String a = (String) keys.next();
                        int b = Integer.parseInt(a);
                        ExerciseByID neid = new ExerciseByID(b);
                        if(wo.get(a) instanceof JSONObject){
                            JSONObject eval = new JSONObject(wo.get(a).toString());
                            Log.d("VAL:", eval.toString());
                            //w.setAuthor(eval.get("W_NAME").toString());
                            int s = Integer.valueOf(eval.get("W_SETS").toString());
                            int r = Integer.valueOf(eval.get("W_REPS").toString());
                            Log.d("S",a);

                            author = eval.get("W_AUTHOR").toString();

                            neid.setSets(s);
                            neid.setReps(r);
                            eidlist.add(neid);
                        }
                    }


                    int[] g = new int[eidlist.size()];
                    int [] s = new int[eidlist.size()];
                    int [] r = new int[eidlist.size()];
                    String [] e = new String[eidlist.size()];
                    int count = 0;

                    for (ExerciseByID it : eidlist) {
                        //count ++;
                        Log.d("W", Integer.toString(it.getReps()));
                        g[count] = it.getEid();
                        s[count] = it.getSets();
                        r[count] = it.getReps();
                        count ++;
                    }

                    for(int i = 0; i < e.length; i ++){
                        try {
                            String st = eformURl("Search", Integer.toString(g[i]));
                            st = new TalkToServer().execute(st).get();
                            Log.d("E", st);
                            e[i] = st;
                        }catch (InterruptedException er) {
                            er.printStackTrace();
                        } catch (ExecutionException er) {
                            er.printStackTrace();
                        }
                    }

                    TextView workoutName = (TextView) findViewById(R.id.workout);
                    workoutName.setText(wname);

                    TextView exer1 = (TextView) findViewById(R.id.exer_1);
                    exer1.setText(e[0] + " Sets: " + s[0] + " Reps: " + r[0]);

                    TextView exer2 = (TextView) findViewById(R.id.exer_2);
                    exer2.setText(e[1]+ " Sets: " + s[1] + " Reps: " + r[1]);

                    TextView exer3 = (TextView) findViewById(R.id.exer_3);
                    exer3.setText(e[2]+ " Sets: " + s[2] + " Reps: " + r[2]);

                    TextView exer4 = (TextView) findViewById(R.id.exer_4);
                    exer4.setText(e[3]+ " Sets: " + s[3] + " Reps: " + r[3]);

                    TextView exer5 = (TextView) findViewById(R.id.exer_5);
                    exer5.setText(e[4]+ " Sets: " + s[4] + " Reps: " + r[4]);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch(JSONException e){
                    e.printStackTrace();
                }





                //Print workouts and whatever is needed below the printed date
                /*try{
                    str = getJSONObjectFromURL(formURL(0117, "Calendar")).toString();
                }catch (IOException e) {
                    e.printStackTrace();
                    Log.d("ErrorIO: ",e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ErrorJSON: ", e.toString());
                }*/

                //TextView workoutInfo = (TextView) findViewById(R.id.workout);
                //workoutInfo.setText(str);
                Log.d("JSON test", str);
                //Button that will redirect user to create a workout for the date if no workout is posted
                //Button that will give an option to delete the workout => confirm notification
                //Create a flag that will keep track if there is a workout created or not
                //Multiple Workouts? Add a workout button?
                //Clicking on a listed workout should prompt a pop up box that provides more info about the workout

                //if(there is no workout created)
                Button createNew = (Button) findViewById(R.id.newWorkout);
                createNew.setVisibility(Button.VISIBLE);
                //else if(there is a workout)
                //button to add workout or delete workout

                //
                // CHANGE BACK TO CREATE WORKOUT
                //
                createNew.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        //Need to push (month + 1), dayOfMonth, and year to database
                        Intent i = new Intent(Calendar.this, createWorkout.class);
                        startActivity(i);
                    }
            });
        }});
    }


}
