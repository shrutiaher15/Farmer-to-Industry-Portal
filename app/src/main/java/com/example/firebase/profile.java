package com.example.firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {


    Button signout;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database1;
    DatabaseReference datarefer;
    farmer_user fuser = new farmer_user();
    TextView phone, name1, state, dis;

    public String uname, phone1, district, state1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // name=findViewById(R.id.name);


        name1 = (TextView) findViewById(R.id.Name);
        phone = (TextView) findViewById(R.id.phoneno);
        state = (TextView) findViewById(R.id.state3);
        dis = (TextView) findViewById(R.id.district2);

        datarefer = FirebaseDatabase.getInstance().getReference("farmuser");
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //signout = findViewById(R.id.logout);
        //datarefer = database1.getReference().child();
        datarefer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                uname = dataSnapshot.child(id).child("name").getValue().toString();
               /* phone1 = dataSnapshot.child("phone").getValue().toString();
                district = dataSnapshot.child("dis").getValue().toString();
                state1 = dataSnapshot.child("state").getValue().toString();*/
                phone1 = dataSnapshot.child(id).child("phone").getValue().toString();
                district = dataSnapshot.child(id).child("dis").getValue().toString();
                state1 = dataSnapshot.child(id).child("state").getValue().toString();
                name1.setText(uname);
                phone.setText(phone1);
                dis.setText(district);
                state.setText(state1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

}