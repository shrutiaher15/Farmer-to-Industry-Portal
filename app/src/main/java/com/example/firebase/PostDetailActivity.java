package com.example.firebase;


import android.content.Intent;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity {

/**industry detail page **/
    public TextView name,phno,crop,latitude,longitude,transport,price,quantity;
    public Button needh;

    Button makeDeal;
    public static TextView mail;

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        setTitle("Post Detail");
        final String str=getIntent().getStringExtra("Id");
        Log.d("My_id",str);
        makeDeal=findViewById(R.id.deal);
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Industry");
        final DatabaseReference ref2=FirebaseDatabase.getInstance().getReference("Industry_Info");
        makeDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   id=dataSnapshot.child(str).child("Id").getValue().toString();
                        ref2.child(id).child("response").setValue(str);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                try
                {
                    LongOperation l=new LongOperation();
                    l.execute();  //sends the email in background
                    Toast.makeText(v.getContext(), l.get(), Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }
        });

        final TextView name1,crop1,description1,email1,price1,quantity1,transport1;

        name1=findViewById(R.id.indname);
        crop1=findViewById(R.id.indcrop);
        // description1=findViewById(R.id.des);
        email1=findViewById(R.id.email);
        price1=findViewById(R.id.indprice);
        quantity1=findViewById(R.id.indquant);


        mDatabase.addValueEventListener(new ValueEventListener() {
            String name,crop,description,emailt,price,quantity,transport;


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              //  mDatabase.removeEventListener(ValueEventListener);
                name=dataSnapshot.child(str).child("name").getValue().toString();
                crop=dataSnapshot.child(str).child("crop").getValue().toString();
                quantity=dataSnapshot.child(str).child("quantity").getValue().toString();
               //emailt=dataSnapshot.child(str).child("email").getValue().toString();
                price=dataSnapshot.child(str).child("price").getValue().toString();



                name1.setText(name);
                crop1.setText(crop);
              //email1.setText(emailt);
                price1.setText(price);
                quantity1.setText(quantity);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // databaseReference.removeEventListener(valueEventListener);
        needh=findViewById(R.id.needh);
        needh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("Industry");
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String indcrop=dataSnapshot.child(str).child("crop").getValue().toString();
                        final DatabaseReference ref2= FirebaseDatabase.getInstance().getReference("farmer");
                        ref2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String currentuser= FirebaseAuth.getInstance().getCurrentUser().getUid();
                                String curlat=dataSnapshot.child(currentuser).child("lat").getValue().toString();
                                String curlon=dataSnapshot.child(currentuser).child("lon").getValue().toString();

                                /** get lat lon of current farmer **/
                                ArrayList<String> cropf=new ArrayList<>();
                                ArrayList<String> namef=new ArrayList<>();
                                ArrayList<String> phonef=new ArrayList<>();
                              //  ArrayList<String> quantityf=new ArrayList<>();
                                ArrayList<String> fId=new ArrayList<>();
                                ArrayList<String>flat=new ArrayList<>();
                                ArrayList<String>flon=new ArrayList<>();
                                ArrayList<String>dis=new ArrayList<>();

                                ArrayList<String> pnamef=new ArrayList<>();
                                ArrayList<String> pphonef=new ArrayList<>();
                                ArrayList<String> pfId=new ArrayList<>();
                                ArrayList<String>pdis=new ArrayList<>();

                                for(DataSnapshot ds : dataSnapshot.getChildren())
                                {
                                   namef.add(ds.child("Name").getValue().toString());
                                   phonef.add(ds.child("phone_no").getValue().toString());
                                   cropf.add(ds.child("Crop").getValue().toString());
                                   fId.add(ds.child("Id").getValue().toString());
                                   flat.add(ds.child("lat").getValue().toString());
                                   flon.add(ds.child("lon").getValue().toString());


                                   /**get each farmer current lat and lon  and pass  **/


                                }
                                for(int i=0;i<flat.size();i++)
                                {
                                    Location a=new Location("location a");
                                    Location b=new Location("location b");

                                    a.setLatitude(Double.parseDouble(curlat));
                                    a.setLongitude(Double.parseDouble(curlon));

                                    b.setLatitude(Double.parseDouble(flat.get(i)));
                                    b.setLongitude(Double.parseDouble(flon.get(i)));


                                    dis.add(String.valueOf((a.distanceTo(b))/1000));
                                    //b.setLongitude();

                                }
                                for(int i=0;i<cropf.size();i++)
                                {


                                        if(cropf.get(i).contains(indcrop)) {
                                            pfId.add(fId.get(i));
                                            pnamef.add(namef.get(i));
                                            pphonef.add(phonef.get(i));
                                            pdis.add(dis.get(i));
                                          //  Log.d("yes","fdg");
                                        }



                                }
                              //  Log.d("phone",""+phonef.size());

                               // Log.d("dis tyttds",""+dis.size());
                                for(int i=0;i<dis.size();i++)
                                    Log.d("dis45",dis.get(i));
                                Intent i=new Intent(PostDetailActivity.this,near_by_farmer.class);
                                Bundle b1=new Bundle();
                                Bundle b2=new Bundle();
                                Bundle b3=new Bundle();
                                Bundle b4=new Bundle();


                                b1.putSerializable("farmer_name",pnamef);
                                b2.putSerializable("farmer_phno",phonef);
                                b3.putSerializable("farmer_id",pfId);
                                b4.putSerializable("farmer_dis",pdis);


                                i.putExtra("bundle1",b1);
                                i.putExtra("bundle2",b2);
                                i.putExtra("bundle3",b3);
                                i.putExtra("bundle4",b4);


                                startActivity(i);


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
        });


    }









}
