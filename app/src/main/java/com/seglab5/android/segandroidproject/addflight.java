package com.seglab5.android.segandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addflight extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewF = database.getReference("segandroidproject");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addflight);
    }

    public void addFlight(View view){
        EditText flightID = (EditText) findViewById(R.id.editText5);
        EditText boardingtime = (EditText) findViewById(R.id.editText6);
        EditText arrivaltime = (EditText) findViewById(R.id.editText7);
        EditText destination = (EditText) findViewById(R.id.editText8);

        FlightSchedule fl = new FlightSchedule("Flight ID: " + flightID.getText().toString(),"Boarding Time: " + boardingtime.getText().toString(),"Arrival Time: " + arrivaltime.getText().toString(),"Destination: " +  destination.getText().toString());

        NewF.child("FLIGHTS").child(flightID.getText().toString()).setValue(fl);

        finish();
    }
}
