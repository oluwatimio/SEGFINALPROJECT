package com.seglab5.android.segandroidproject;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by owotu on 2017-11-21.
 */

public class StatusDialog extends DialogFragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewUser = database.getReference("segandroidproject");
    private FirebaseAuth mAuth;
    FirebaseUser user;
    ArrayList<Status> allUsers = new ArrayList<>();
    TextView userID;
    TextView status1;
    String uid;
    String[] statusArray;
    private final String LOG_TAG = Home.class.getSimpleName();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        uid = user.getUid();
        statusArray =   getResources().getStringArray(R.array.Statuses);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Set Position")
                .setItems(R.array.Statuses, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(LOG_TAG,String.valueOf(which) + "herec");
                        setStuff(statusArray,which);
                    }
                });
        return builder.create();
    }


    public void setStuff(String[] statusArray, int key){
        Log.d(LOG_TAG,statusArray[key] + "key");
        //Status update = new Status(allUsers.get(0).getUid(),allUsers.get(0).getEmail(),statusArray[key]);

        DatabaseReference userref = database.getReference("segandroidproject/USERS/" + uid);
        Map<String, Object> hopperUpdates = new HashMap<>();
        hopperUpdates.put("status", statusArray[key]);
        userref.updateChildren(hopperUpdates);
        //NewUser.child("USERS").child(allUsers.get(0).getUid()).setValue(update);
    }
}
