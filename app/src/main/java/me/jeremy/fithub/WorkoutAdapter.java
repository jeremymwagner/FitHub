package me.jeremy.fithub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Axeldama on 4/23/17.
 */

public class WorkoutAdapter extends ArrayAdapter<Workout>{
    public WorkoutAdapter(Context context, ArrayList<Workout> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Workout w = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.eName);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.eDesc);
        TextView tvGroup = (TextView) convertView.findViewById(R.id.eGroup);
        // Populate the data into the template view using the data object
        tvName.setText("Workout: " + w.getName());
        tvDesc.setText("Author: " + w.getAuthor());
        //tvGroup.setText("Exercises: " + w.getMuscleGroup());
        // Return the completed view to render on screen
        return convertView;
    }
}
