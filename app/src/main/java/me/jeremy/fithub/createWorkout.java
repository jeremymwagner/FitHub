package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class createWorkout extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10, spinner11, spinner12, spinner13, spinner14, spinner15;
    ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        spinner1 = (Spinner)findViewById(R.id.selectExercise);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.exercise, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner2 = (Spinner)findViewById(R.id.reps);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.num, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner)findViewById(R.id.sets);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.num, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner4 = (Spinner)findViewById(R.id.selectExercise2);
        spinner4.setAdapter(adapter1);
        spinner8 = (Spinner)findViewById(R.id.reps2);
        spinner8.setAdapter(adapter2);
        spinner12 = (Spinner)findViewById(R.id.sets2);
        spinner12.setAdapter(adapter3);

        spinner5 = (Spinner)findViewById(R.id.selectExercise3);
        spinner5.setAdapter(adapter1);
        spinner9 = (Spinner)findViewById(R.id.reps3);
        spinner9.setAdapter(adapter2);
        spinner13 = (Spinner)findViewById(R.id.sets3);
        spinner13.setAdapter(adapter3);

        spinner6 = (Spinner)findViewById(R.id.selectExercise4);
        spinner6.setAdapter(adapter1);
        spinner10 = (Spinner)findViewById(R.id.reps4);
        spinner10.setAdapter(adapter2);
        spinner14 = (Spinner)findViewById(R.id.sets4);
        spinner14.setAdapter(adapter3);

        spinner7 = (Spinner)findViewById(R.id.selectExercise5);
        spinner7.setAdapter(adapter1);
        spinner11 = (Spinner)findViewById(R.id.reps5);
        spinner11.setAdapter(adapter2);
        spinner15 = (Spinner)findViewById(R.id.sets5);
        spinner15.setAdapter(adapter3);
    }
}
