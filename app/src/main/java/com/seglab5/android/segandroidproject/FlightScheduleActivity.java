package com.seglab5.android.segandroidproject;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FlightScheduleActivity extends AppCompatActivity {
    private ListView Flights;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewUser = database.getReference("segandroidproject");
    Status allUsers = new Status("","","");
    String main;
    ArrayList<String> arrayofFlights;
    ArrayList<FlightSchedule> realarrayFlights;
    FlightAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_schedule);
        final Context b = this;
        USER(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((allUsers.getStatus().equals("Airport Management"))){
                    Intent start = new Intent(FlightScheduleActivity.this, addflight.class);
                    startActivity(start);
                }

                else{
                    Toast.makeText(b, "Restricted Portal. You are not management", Toast.LENGTH_LONG).show();
                }
            }
        });

        arrayofFlights = new ArrayList<>();
        realarrayFlights = new ArrayList<>();

        adapter = new FlightAdapter(this,realarrayFlights);

         Flights = (ListView) findViewById(R.id.list_flights);
         Flights.setAdapter(adapter);

         FLIGHTS();
    }

    public void FLIGHTS(){
        ChildEventListener eventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FlightSchedule flights = dataSnapshot.getValue(FlightSchedule.class);
                realarrayFlights.add(flights);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        DatabaseReference userref = database.getReference("segandroidproject/FLIGHTS/");
        userref.addChildEventListener(eventListener);
    }

    public void USER(String uid){
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                allUsers = dataSnapshot.getValue(Status.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        DatabaseReference userref = database.getReference("segandroidproject/USERS/" + uid);
        userref.addValueEventListener(eventListener);

    }


}
