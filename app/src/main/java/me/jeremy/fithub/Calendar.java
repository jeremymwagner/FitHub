package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;

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



                //Print workouts and whatever is needed below the printed date



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
