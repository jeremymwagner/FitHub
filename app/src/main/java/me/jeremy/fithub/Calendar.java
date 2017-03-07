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
import java.util.concurrent.ExecutionException;
import android.util.JsonReader;
import java.io.InputStream;



import android.util.Log;
import android.os.AsyncTask;

import me.jeremy.fithub.TalkToServer;


import org.json.JSONObject;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    String s = formURL(0117,"Calendar");
    //getRequest.execute(s);

    /**
     *  Form url queries by grabbing startdate from selected calendar day
     * @param startDate
     * @param requestType
     * @return
     */
    public String formURL(int startDate, String requestType){
        //hard code googleid until figure out how to grab it from sign o
           return baseURL + "?requestType="+requestType+"&startDate=0117&googleId=109689297173623922729";

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);



        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Toast.makeText(getApplicationContext(), month + "/" + dayOfMonth + "/" + year, Toast.LENGTH_LONG).show();

                //Prints out the date selected below the calendar
                TextView textElement = (TextView) findViewById(R.id.date);
                textElement.setText((month + 1) + "/" + dayOfMonth + "/" + year);

                String str = "test";

                try {

                    // Add date to param string
                    str = new TalkToServer().execute(s).get();
                    JSONObject jobj = new JSONObject(str);

                    JSONObject w1 = jobj.getJSONObject("1");
                    JSONObject e1 = w1.getJSONObject("1");
                    String s1 = e1.getString("W_SETS");
                    String r1 = e1.getString("W_REPS");
                    String a1 = e1.getString("W_AUTHOR");
                    JSONObject e2 = w1.getJSONObject("2");
                    String s2 = e1.getString("W_SETS");
                    String r2 = e1.getString("W_REPS");

                    TextView workoutName = (TextView) findViewById(R.id.workout);
                    workoutName.setText(a1+"'s workout ");

                    TextView exer1 = (TextView) findViewById(R.id.exer_1);
                    exer1.setText("Exercise: 1");

                    TextView set1 = (TextView) findViewById(R.id.sets_1);
                    set1.setText(s1);

                    TextView rep1 = (TextView) findViewById(R.id.reps_1);
                    workoutName.setText(r1);


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

                createNew.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(Calendar.this, createWorkout.class);
                        startActivity(i);
                    }
            });
        }});
    }


}
