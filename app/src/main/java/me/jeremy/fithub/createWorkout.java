package me.jeremy.fithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class createWorkout extends AppCompatActivity {

    Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6, spinner7, spinner8, spinner9, spinner10, spinner11, spinner12, spinner13, spinner14, spinner15;
    ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;

    public int w_ID = 5;
    String str_w_ID = String.format("%06d", w_ID);

    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    String myResult = "";
    TalkToServer getRequest = new TalkToServer();
    //String s = formURL("000005","Search","LEG PRESS","9","3","Joe Schmoe");
    List<Exercise> postExer;

    public String formURL(String wID, String requestType,String eName,String nReps,String nSets,String wAuthor,String wName){

        return baseURL + "?requestType="+requestType+"&workoutID="+wID +"&exerciseName="
                + eName+"&W_REPS="+nReps+"&W_SETS="+nSets+"&W_AUTHOR="+wAuthor+"&GOOGLE_ID=333"+"&W_NAME="+wName;

    }
    // https://people.eecs.ku.edu/~jbondoc/test2.php?requestType=Post&workoutID=000005&exerciseName1=LEG_PRESS&W_REPS1=9&W_SETS1=3&W_AUTHOR=Joe_Schmoe&googleID=333
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

        Button submit = (Button) findViewById(R.id.submit);
        submit.setVisibility(Button.VISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText title = (EditText) findViewById(R.id.title);
                String nameOfWorkout = title.getText().toString();
                String text1 = spinner1.getSelectedItem().toString();
                String text2 = spinner2.getSelectedItem().toString();
                String text3 = spinner3.getSelectedItem().toString();
                TextView test1 = (TextView) findViewById(R.id.test1);
                TextView test2 = (TextView) findViewById(R.id.test2);
                TextView test3 = (TextView) findViewById(R.id.test3);
                //test1.setText(text1);
                //test2.setText(text2);
                //test3.setText(text3);

                String text4 = spinner4.getSelectedItem().toString();
                String text8 = spinner8.getSelectedItem().toString();
                String text12 = spinner12.getSelectedItem().toString();
                TextView test4 = (TextView) findViewById(R.id.test1);
                TextView test8 = (TextView) findViewById(R.id.test2);
                TextView test12 = (TextView) findViewById(R.id.test3);
                //test1.setText(text4);
                //test2.setText(text8);
                //test3.setText(text12);

                String text5 = spinner5.getSelectedItem().toString();
                String text9 = spinner9.getSelectedItem().toString();
                String text13 = spinner13.getSelectedItem().toString();
                TextView test5 = (TextView) findViewById(R.id.test1);
                TextView test9 = (TextView) findViewById(R.id.test2);
                TextView test13 = (TextView) findViewById(R.id.test3);
                //test1.setText(text5);
                //test2.setText(text9);
                //test3.setText(text13);

                String text6 = spinner6.getSelectedItem().toString();
                String text10 = spinner10.getSelectedItem().toString();
                String text14 = spinner14.getSelectedItem().toString();
                TextView test6 = (TextView) findViewById(R.id.test1);
                TextView test10 = (TextView) findViewById(R.id.test2);
                TextView test14 = (TextView) findViewById(R.id.test3);
                //test1.setText(text6);
                //test2.setText(text10);
                //test3.setText(text14);

                String text7 = spinner7.getSelectedItem().toString();
                String text11 = spinner11.getSelectedItem().toString();
                String text15 = spinner15.getSelectedItem().toString();
                TextView test7 = (TextView) findViewById(R.id.test1);
                TextView test11 = (TextView) findViewById(R.id.test2);
                TextView test15 = (TextView) findViewById(R.id.test3);
                test1.setText(text7);
                test2.setText(text11);
                test3.setText(text15);



                String str;
                try {




                    text1 = text1.toUpperCase();
                    str = formURL("000008","Post",text1,text2,text3,"My App",nameOfWorkout);
                    str = str.replaceAll("\\s+","+");
                    Log.d("SEARCHSTRING:",str);
                    String res = new TalkToServer().execute(str).get();

                    text4 = text4.toUpperCase();
                    str = formURL("000008","Post",text4,text8,text12,"My App",nameOfWorkout);
                    str = str.replaceAll("\\s+","+");
                    Log.d("SEARCHSTRING:",str);
                    res = new TalkToServer().execute(str).get();

                    text5 = text5.toUpperCase();
                    str = formURL("000008","Post",text5,text9,text13,"My App",nameOfWorkout);
                    str = str.replaceAll("\\s+","+");
                    Log.d("SEARCHSTRING:",str);
                    res = new TalkToServer().execute(str).get();

                    text6 = text6.toUpperCase();
                    str = formURL("000008","Post",text6,text10,text14,"My App",nameOfWorkout);
                    str = str.replaceAll("\\s+","+");
                    Log.d("SEARCHSTRING:",str);
                    res = new TalkToServer().execute(str).get();

                    text7 = text7.toUpperCase();
                    str = formURL("000008","Post",text7,text11,text15,"My App",nameOfWorkout);
                    str = str.replaceAll("\\s+","+");
                    Log.d("SEARCHSTRING:",str);
                    res = new TalkToServer().execute(str).get();
                   // TextView testReq = (TextView) findViewById(R.id.test);
                    //TextView testReq = (TextView) findViewById(R.id.search);

                    //testReq.setText(str);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
