package com.example.bhart.savewater;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackForm extends AppCompatActivity {

    EditText topic,email,detail;
    Button submit;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);
        setTitle("Share your idea");

        topic = (EditText)findViewById(R.id.topic);
        email=(EditText)findViewById(R.id.email);
        detail=(EditText)findViewById(R.id.detail);
        submit=(Button)findViewById(R.id.submit);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //final String t=topic.getText().toString();
        //final String e=email.getText().toString();
        //final String d=detail.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String t=topic.getText().toString();
                final String e=email.getText().toString();
                final String d=detail.getText().toString();
                if(t.isEmpty() || e.isEmpty() || d.isEmpty()){
                    Snackbar.make(findViewById(android.R.id.content),"you didn't fill all the field",Snackbar.LENGTH_SHORT)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                            .show();
                }else{
                    writeUser(e,t,d,0,0);
                    Snackbar.make(findViewById(android.R.id.content),"your idea is submitted. Thank you for your valuable time!",Snackbar.LENGTH_SHORT)
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                            .show();
                }
            }
        });


    }

    public void writeUser(String e, String t, String d, int l, int disl){
        User user=new User(e,t,d,l,disl);

        mDatabase.child("User").push().setValue(user);
    }
}
