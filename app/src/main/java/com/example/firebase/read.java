package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class read extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("farmer");
        final DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Industry");
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                       final String crop=dataSnapshot.child(Id).child("Crop").getValue().toString();
                final String[] str1 = crop.split(" ");

                      //  Log.d("crop" ,crop);
                        final ArrayList<String>name_ind=new ArrayList<>();
                        final ArrayList<String> crop_ind=new ArrayList<>();
                        //final int count=0;
                        ref2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int count=0;

                                for(DataSnapshot ds : dataSnapshot.getChildren())
                                {
                                    name_ind.add(ds.child("Name").getValue().toString());
                                    crop_ind.add(ds.child("Crop").getValue().toString());

                                }
                                for(int i=0;i<str1.length;i++)
                                {

                                    for(int j=0;j<crop_ind.size();j++)
                                    {
                                        String s2=crop_ind.get(j);
                                        if(str1[i].equalsIgnoreCase(s2))
                                        {
                                            count=count+1;
                                        }
                                    }
                                }
                                Log.d("Count ",""+count);


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
