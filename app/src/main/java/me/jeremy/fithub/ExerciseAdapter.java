package me.jeremy.fithub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Axeldama on 4/5/17.
 */

public class ExerciseAdapter  extends ArrayAdapter<Exercise> {

        public ExerciseAdapter(Context context, ArrayList<Exercise> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Exercise e = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_workout, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.wName);
            TextView tvDesc = (TextView) convertView.findViewById(R.id.wAuthor);
           // TextView tvGroup = (TextView) convertView.findViewById(R.id.eGroup);
            // Populate the data into the template view using the data object
            tvName.setText("Exercise: " + e.getName());
            tvDesc.setText("How to: " + e.getDescription());
            //tvGroup.setText("Muscle Group: " + e.getMuscleGroup());
            // Return the completed view to render on screen
            return convertView;
        }

}
