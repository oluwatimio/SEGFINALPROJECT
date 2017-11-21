package com.seglab5.android.segandroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    FirebaseUser user;
    final int RC_SIGN_IN = 1000;
    private final String LOG_TAG = Home.class.getSimpleName();
    int check = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference NewUser = database.getReference("segandroidproject");
    ArrayList<Status> allUsers = new ArrayList<Status>();
    TextView STATUS;
    EditText STATUSE;
    Button STATUSB;
    TextView userID;
    TextView Email;
    TextView status1;
    int l = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mAuth.signOut();
            firebaseUi();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void USER(){
        ChildEventListener userListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Status status = dataSnapshot.getValue(Status.class);

                Log.d(LOG_TAG, "in user");

                if (status.getUid().equals(mAuth.getCurrentUser().getUid())){
                    allUsers.clear();
                    allUsers.add(status);
                    setStuff();
                }

                else{
                    setStuff2();
                }

                if (allUsers.size()>0){
                    if (allUsers.get(0).getStatus().equals("") && l != 1){

                        STATUS = (TextView) findViewById(R.id.textView8);
                        STATUSE = (EditText) findViewById(R.id.statusset);
                        STATUSB = (Button) findViewById(R.id.sets);

                        STATUS.setVisibility(View.VISIBLE);
                        STATUSE.setVisibility(View.VISIBLE);
                        STATUSE.setVisibility(View.VISIBLE);
                        STATUSB.setVisibility(View.VISIBLE);
                    }
                }





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



        DatabaseReference userref = database.getReference("segandroidproject/USERS");
        userref.addChildEventListener(userListener);
    }



    @Override
    public void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null)
        {
            Log.d(LOG_TAG, "in onstart fireui");
            firebaseUi();
            Log.d(LOG_TAG, "in onstart fireui2");
            USER();
        }

        else{
            USER();
        }

        //else{
          //  Intent intent = getIntent();
            //finish();
            //startActivity(intent);
        //}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this, "SIGN IN SUCCESSFULL", Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
            else {
                Toast.makeText(this, "SIGN IN FAILED", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void firebaseUi()
    {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                                Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .build(),
                RC_SIGN_IN);
    }


    public void setStuff2(){

        String uid = mAuth.getCurrentUser().getUid();
        String email = mAuth.getCurrentUser().getEmail();
        String status = "";

        Status main = new Status(uid,email,status);

        Log.d(LOG_TAG, "in check");

        writeUser(main);

    }
    public void setStuff(){



        Log.d(LOG_TAG, "in else");

        userID = (TextView)findViewById(R.id.textView3);
        userID.setText(allUsers.get(0).getUid());
        Email = (TextView)findViewById(R.id.textView5);
        Email.setText(allUsers.get(0).getEmail());
        status1 = (TextView)findViewById(R.id.textView7);
        status1.setText(allUsers.get(0).getStatus());

    }

    public void writeUser(Status status){

        NewUser.child("USERS").child(status.getUid()).setValue(status);
        //NewUniversity.child("Programs").child(name).child("Program").setValue(y.getName());
    }

    public void writeUser2(View view){

        Status newone = new Status(allUsers.get(0).getUid(),allUsers.get(0).getEmail(),STATUSE.getText().toString());
        NewUser.child("USERS").child(allUsers.get(0).getUid()).setValue(newone);

        STATUS = (TextView) findViewById(R.id.textView8);
        STATUSE = (EditText) findViewById(R.id.statusset);
        STATUSB = (Button) findViewById(R.id.sets);

        STATUS.setVisibility(View.GONE);
        STATUSE.setVisibility(View.GONE);
        STATUSE.setVisibility(View.GONE);
        STATUSB.setVisibility(View.GONE);

        l = 1;

        USER();


    }
}
