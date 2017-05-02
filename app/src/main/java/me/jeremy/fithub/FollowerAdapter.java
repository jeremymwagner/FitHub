package me.jeremy.fithub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Axeldama on 5/1/17.
 */

public class FollowerAdapter extends ArrayAdapter<People> {

    public FollowerAdapter(Context context, ArrayList<People> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        People p = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_follower, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.werName);
        //TextView tvDesc = (TextView) convertView.findViewById(R.id.wAuthor);
        // TextView tvGroup = (TextView) convertView.findViewById(R.id.eGroup);
        // Populate the data into the template view using the data object
        tvName.setText( p.getName());
        //tvDesc.setText("How to: " + p.g());
        //tvGroup.setText("Muscle Group: " + e.getMuscleGroup());
        // Return the completed view to render on screen
        return convertView;
    }
}
