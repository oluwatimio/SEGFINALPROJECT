package com.seglab5.android.segandroidproject;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Delays extends AppCompatActivity {
    private ListView Delays;
    private FirebaseAuth mAuth;
    ArrayAdapter<String> adapter;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewUser = database.getReference("segandroidproject");
    Status allUsers = new Status("","","");
    String main;
    ArrayList<String> arrayofDelays;
    ArrayList<Delay> realarrayDelays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delays);
        USER(FirebaseAuth.getInstance().getCurrentUser().getUid());
        /*try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        final Context b = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(allUsers.getStatus().equals("Passenger"))){
                    Intent start = new Intent(Delays.this, adddelay.class);
                    startActivity(start);
                }

                else{
                    Toast.makeText(b, "Restricted Portal. Passenger!", Toast.LENGTH_LONG).show();
                }
            }
        });

        arrayofDelays = new ArrayList<>();
        realarrayDelays = new ArrayList<>();


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayofDelays);

        Delays = (ListView) findViewById(R.id.list_Delays);
        Delays.setAdapter(adapter);
        DELAYS();
        final Context c = this;
        Delays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(c, realarrayDelays.get(position).getWaitime() + " Minute Wait Time", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void DELAYS(){
        ChildEventListener eventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Delay delay = dataSnapshot.getValue(Delay.class);
                arrayofDelays.add(delay.getFlightNumber());
                realarrayDelays.add(delay);
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

        DatabaseReference userref = database.getReference("segandroidproject/DELAYS/");
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
