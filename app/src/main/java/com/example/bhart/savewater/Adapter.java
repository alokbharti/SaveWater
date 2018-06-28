package com.example.bhart.savewater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<User> userList;
    private Context context;

    public Adapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.user_advice,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user=userList.get(position);
        holder.topic.setText(user.getTopic());
        holder.details.setText(user.getDetail());
        holder.t1.setText(String.valueOf(user.likes));
        holder.t2.setText(String.valueOf(user.getDislikes()));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        DatabaseReference databaseReference;
        public TextView topic;
        public TextView details;
        public TextView t1;
        public TextView t2;
        public Button l;
        public Button d;
        public ViewHolder(View itemView) {
            super(itemView);
            topic=(TextView)itemView.findViewById(R.id.topic);
            details=(TextView)itemView.findViewById(R.id.details);
            t1=(TextView)itemView.findViewById(R.id.t1);
            t2=(TextView)itemView.findViewById(R.id.t2);
            l=(Button)itemView.findViewById(R.id.like);
            d=(Button)itemView.findViewById(R.id.dislike);

            databaseReference= FirebaseDatabase.getInstance().getReference("User");

            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String li=t1.getText().toString();
                    int lik=Integer.parseInt(li);
                    lik+=1;
                    li=String.valueOf(lik);
                    t1.setText(li);
                    l.setEnabled(false);
                    d.setEnabled(false);
                    final int pos = getAdapterPosition();
                    final int finalLik = lik;
 /**
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Log.e("inside database","reached");
                            int c=0;
                            for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                                if(c==pos) {
                                    databaseReference.child(snapshot.getKey()).child("likes").setValue(finalLik);
                                    notifyItemChanged(pos);
                                    break;
                                }
                                c++;
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
  **/
                }
            });
            d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String li=t2.getText().toString();
                    int lik=Integer.parseInt(li);
                    lik-=1;
                    li=String.valueOf(lik);
                    t2.setText(li);
                    d.setEnabled(false);
                    l.setEnabled(false);
                }
            });

        }
    }
}
