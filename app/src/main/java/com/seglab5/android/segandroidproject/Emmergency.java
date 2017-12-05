package com.seglab5.android.segandroidproject;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class Emmergency extends AppCompatActivity {

    private ListView Emmergencies;
    private FirebaseAuth mAuth;
    ArrayAdapter<String> adapter;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewUser = database.getReference("segandroidproject");
    Status allUsers = new Status("","","");
    String main;
    ArrayList<String> arrayofEmergencies;
    ArrayList<Emergency> realarrayEmergencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emmergency);
        USER(FirebaseAuth.getInstance().getCurrentUser().getUid());

        final Context b = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(allUsers.getStatus().equals("Passenger"))){
                    Intent start = new Intent(Emmergency.this, addemergency.class);
                    startActivity(start);
                }

                else{
                    Toast.makeText(b, "Restricted Portal. Passenger!", Toast.LENGTH_LONG).show();
                }
            }
        });

        arrayofEmergencies = new ArrayList<>();
        realarrayEmergencies = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayofEmergencies);

        Emmergencies = (ListView) findViewById(R.id.list_emergency);
        Emmergencies.setAdapter(adapter);
        EMMERGENCIES();
        final Context c = this;
        Emmergencies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(c, realarrayEmergencies.get(position).getLocation() + " IS THE LOCATION, " + String.valueOf(realarrayEmergencies.get(position).getRank()) + " IS THE RANK", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void EMMERGENCIES(){
        ChildEventListener eventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Emergency emergency1 = dataSnapshot.getValue(Emergency.class);
                arrayofEmergencies.add(emergency1.getDescription());
                realarrayEmergencies.add(emergency1);
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

        DatabaseReference userref = database.getReference("segandroidproject/EMERGENCY/");
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

    } //READS INFO FROM DATABASE
}
