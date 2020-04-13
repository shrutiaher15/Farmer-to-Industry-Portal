package com.example.firebase;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.firebase.App.CHANNEL_1_ID;

public class splashScreen extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String value1;
    View v;
    private static int Splash_time=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent homeintent=new Intent(splashScreen.this, home_page.class);
                startActivity(homeintent);
                finish();
            }
        },Splash_time);
        notificationManager = NotificationManagerCompat.from(this);
        database=FirebaseDatabase.getInstance();
//        mroot=findViewById(R.id.root1);
        myRef=database.getReference().child("Industry");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String value=ds.child("flag").getValue().toString();
                    if(value.equals("0"))
                    {
                        value1=ds.child("crop").getValue().toString();
                        Intent i=new Intent(splashScreen.this,splashScreen.class);
                        PendingIntent pi=PendingIntent.getActivity(splashScreen.this,1,i,PendingIntent.FLAG_UPDATE_CURRENT);
                        Notification notification = new NotificationCompat.Builder(splashScreen.this, CHANNEL_1_ID)
                                .setSmallIcon(R.drawable.icon)
                                .setContentTitle("Industry Request")
                                .setContentText(value1)
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .setAutoCancel(true)
                                .setContentIntent(pi)
                                .build();
                        notificationManager.notify(1, notification);
                        database.getReference().child("Industry").child(ds.getKey()).child("flag").setValue("1");

                    }
                    //Toast.makeText(splashScreen.this,"Value is :"+value,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(splashScreen.this,"No value found",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
