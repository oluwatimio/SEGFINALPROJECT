package com.seglab5.android.segandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adddelay extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewDelay = database.getReference("segandroidproject");
    Status allUsers = new Status();
    EditText fn;
    EditText wt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddelay);

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

    public void  Write(View view){
        fn = (EditText) findViewById(R.id.editText3);
        wt = (EditText) findViewById(R.id.editText4);

        String number = fn.getText().toString();
        String wait = wt.getText().toString();

        Delay up = new Delay(number,wait);

        NewDelay.child("DELAYS").child(number).setValue(up);
        finish();
    }
}
