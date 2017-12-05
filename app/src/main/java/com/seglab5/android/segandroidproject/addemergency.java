package com.seglab5.android.segandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addemergency extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewE = database.getReference("segandroidproject");
    Status allUsers = new Status();
    EditText DESCRIPT;
    EditText LOCATION;
    NumberPicker rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemergency);

        mAuth = FirebaseAuth.getInstance();

        USER(mAuth.getUid());
    }

    public void USER(String uid) {
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

    public void  Write1(View view){
        DESCRIPT = (EditText) findViewById(R.id.editText);
        LOCATION = (EditText) findViewById(R.id.editText2);
        rank = (NumberPicker) findViewById(R.id.numberPicker);

        String des = DESCRIPT.getText().toString();
        String locat = LOCATION.getText().toString();
        int rank1 = rank.getValue();
        Emergency up = new Emergency(des,locat,rank1);

        NewE.child("EMERGENCY").child(des).setValue(up);
        finish();
    }
}
