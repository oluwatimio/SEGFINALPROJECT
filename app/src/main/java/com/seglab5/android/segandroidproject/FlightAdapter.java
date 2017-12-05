package com.seglab5.android.segandroidproject;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owotu on 2017-12-04.
 */
public class FlightAdapter extends ArrayAdapter<FlightSchedule> {
    private  Context mContext;
    private List<Movie> flightlist = new ArrayList<>();
    public FlightAdapter(@NonNull Context context, ArrayList<FlightSchedule> list) {
        super(context,0, list);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.flight_info_layout,parent,false);
        }

        FlightSchedule moooooo = getItem(position);

        TextView flightID = (TextView) listItem.findViewById(R.id.Text1);
        flightID.setText(moooooo.getFlightID());

        TextView boardingTime = (TextView) listItem.findViewById(R.id.Text2);
        boardingTime.setText(moooooo.getBoarddingTime());

        TextView arrivalTime = (TextView) listItem.findViewById(R.id.Text3);
        arrivalTime.setText(moooooo.getArrivalTime());

        TextView destination = (TextView) listItem.findViewById(R.id.Text4);
        destination.setText(moooooo.getDestination());

        return  listItem;
    }
}
