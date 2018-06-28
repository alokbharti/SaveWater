package com.example.bhart.savewater;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UsersIdea extends AppCompatActivity {

    DatabaseReference dataBase;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    List<User> listItem;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_idea);
        setTitle("Water saving Ideas");

        //for back button
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        dataBase = FirebaseDatabase.getInstance().getReference("User");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItem=new ArrayList<>();

        //Initialising dialog box
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data....");
        progressDialog.show();

        mAdapter = new Adapter(listItem,this);
        recyclerView.setAdapter(mAdapter);

        getUserIdea();
        //Log.d("size",String.valueOf(listItem.size()));

        mAdapter.notifyDataSetChanged();
/**
        if(listItem.isEmpty()){
            progressDialog.dismiss();
            Snackbar.make(findViewById(android.R.id.content),"Failed to load data! '_' ",Snackbar.LENGTH_SHORT)
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();
        }
**/

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)(findViewById(R.id.swipeRefreshLayout));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listItem.clear();
                getUserIdea();
                mAdapter.notifyDataSetChanged();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },4000);
            }
        });

    }

    public void getUserIdea(){
        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("inside database","reached");
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    User user= snapshot.getValue(User.class);
                    //Log.d("snapshot",snapshot.toString());
                    listItem.add(user);
                    progressDialog.dismiss();
                }
                mAdapter.notifyDataSetChanged();
                //Log.e("details",listItem.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            startActivity(new Intent(UsersIdea.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
