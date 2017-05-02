package me.jeremy.fithub;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.DatePickerDialog;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by Axeldama on 4/23/17.
 */

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    public WorkoutAdapter(Context context, ArrayList<Workout> users) {
        super(context, 0, users);
    }
    String baseURL = "https://people.eecs.ku.edu/~jbondoc/test2.php";
    public String formURl(String requestType,String id) {
        return baseURL + "?requestType="+requestType+"&searchType=ExerciseID&exerciseID=" + id;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Workout w = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_work2, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.wName);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.wAuthor);
        //TextView tvGroup = (TextView) convertView.findViewById(R.id.eGroup);
        // Populate the data into the template view using the data object
        tvName.setText("Workout: " + w.getName());
        tvDesc.setText("Author: " + w.getAuthor());


        List<ExerciseByID> eidlist = w.getExerciseList();
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
                String str = formURl("Search", Integer.toString(g[i]));
                str = new TalkToServer().execute(str).get();
                Log.d("E", str);
                e[i] = str;
            }catch (InterruptedException er) {
                er.printStackTrace();
            } catch (ExecutionException er) {
                er.printStackTrace();
            }
        }
//        for(int i = 0; i < 5;i++){
//            Log.d("W",Integer.toString(eidlist.get(i).getEid()));
//        }


        TextView tv1 = (TextView) convertView.findViewById(R.id.e1);
        tv1.setText(e[0] + " Sets: " + Integer.toString(s[0]) + "Reps: " + Integer.toString(r[0]));

        TextView tv2 = (TextView) convertView.findViewById(R.id.e2);
        tv2.setText(e[1] + " Sets: " + Integer.toString(s[1]) + "Reps: " + Integer.toString(r[1]));

        TextView tv3 = (TextView) convertView.findViewById(R.id.e3);
        tv3.setText(e[2] + " Sets: " + Integer.toString(s[2]) + "Reps: " + Integer.toString(r[2]));

        TextView tv4 = (TextView) convertView.findViewById(R.id.e4);
        tv4.setText(e[3] + " Sets: " + Integer.toString(s[3]) + "Reps: " + Integer.toString(r[3]));

        TextView tv5 = (TextView) convertView.findViewById(R.id.e5);
        tv5.setText(e[4] + " Sets: " + Integer.toString(s[4]) + "Reps: " + Integer.toString(r[4]));



        //tvGroup.setText("Exercises: " + w.getMuscleGroup());
        // Return the completed view to render on screen
        return convertView;
    }
}
