package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.util.Log;

import org.json.JSONObject;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;

    String baseURL = "https://people.eecs.ku.edu/~kmonagha/test2.php";

    public String formURL(int startDate, String requestType){
        //hard code googleid until figure out how to grab it from sign o
           return baseURL + "?requestType="+requestType+"&startDate=0117&googleId=109689297173623922729";

    }


    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {


        URL url = new URL(urlString);
        Log.d("test",urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        int responseCode = urlConnection.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        char[] buffer = new char[1024];

        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        jsonString = sb.toString();

        System.out.println("JSON: " + jsonString);

        return new JSONObject((jsonString));
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
                textElement.setText(month + "/" + dayOfMonth + "/" + year);

                String str = "test";

                //Print workouts and whatever is needed below the printed date
                try{
                    str = getJSONObjectFromURL(formURL(0117, "Calendar")).toString();
                }catch (IOException e) {
                    e.printStackTrace();
                    Log.d("ErrorIO: ",e.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ErrorJSON: ", e.toString());
                }

                TextView workoutInfo = (TextView) findViewById(R.id.workout);
                workoutInfo.setText(str);
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
