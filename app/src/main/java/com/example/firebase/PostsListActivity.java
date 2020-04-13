package com.example.firebase;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/***   user can see their order */

public class PostsListActivity extends AppCompatActivity {

    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    Button sell,map;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);
        sell=findViewById(R.id.sell_p);
        map=findViewById(R.id.map);
        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PostsListActivity.this,add_p_for_sell.class);
                startActivity(i);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PostsListActivity.this,map_activity.class);
                startActivity(i);
            }
        });
        //Actionbar
        ActionBar actionBar = getSupportActionBar();
        //set title
        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "newest"); //where if no settingsis selected newest will be default

        if (mSorting.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means newest first
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (mSorting.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means oldest first
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mRecyclerView.setLayoutManager(mLayoutManager);

        //send Query to FirebaseDatabase
         Id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(currentuser);
    }

    //search data
    private void firebaseSearch(String searchText) {

        //convert string entered in SearchView to lowercase
        String query = searchText.toLowerCase();

        Query firebaseSearchQuery = mRef.orderByChild("crop").startAt(query).endAt(query + "\uf8ff");

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getCrop(),model.getQuantity());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views







                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }


                };

        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(),  model.getCrop(), model.getQuantity());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Views
                                TextView name = view.findViewById(R.id.indname);
                                //TextView qut = view.findViewById(R.id.qut);
                               TextView ph_no = view.findViewById(R.id.ph_no);
                                //get data from views
                                final String name1 = name.getText().toString();
//                                String price1 = price.getText().toString();
                                String ph_no1=ph_no.getText().toString();  /****for  quantity ***/


                                //pass this data to new activity
                                //Intent intent = new Intent(view.getContext(), CheckAc.class);
                                //intent.putExtra("name", name1); //put bitmap image as array of bytes
                             //   intent.putExtra("price", price1); // put title
                                //intent.putExtra("ph_no", ph_no1); //put description
                                //startActivity(i1); //start activity




                                //get data from views



                                //pass this data to new activity
                                Intent intent = new Intent(view.getContext(), sell_product.class);

                                /*intent.putExtra("cropname", name1); // put title
                                intent.putExtra("quantity", ph_no1);*/

                                final ArrayList<String> indname, inddis, indphone, indemail, indprice, indquantity, indId, indcrop,indlat,indlon,indtrans;

                                final ArrayList<String> pindname, dis, pindphone, pindemail, pindprice, pindquantity, pindtrans, profit,pindId,passlat,passlon;

                                indname = new ArrayList<>();
                                indemail = new ArrayList<>();
                                indphone = new ArrayList<>();
                                indprice = new ArrayList<>();
                                inddis = new ArrayList<>();
                                indquantity = new ArrayList<>();
                                indcrop = new ArrayList<>();
                                indId=new ArrayList<>();
                                indlat=new ArrayList<>();
                                indlon=new ArrayList<>();
                                indtrans=new ArrayList<>();
                                profit=new ArrayList<>();



                                pindname = new ArrayList<>();
                                pindemail = new ArrayList<>();
                                pindphone = new ArrayList<>();
                                pindprice = new ArrayList<>();
                                dis = new ArrayList<>();
                                pindtrans=new ArrayList<>();
                                pindquantity = new ArrayList<>();
                                pindId=new ArrayList<>();
                                passlat=new ArrayList<>();
                                passlon=new ArrayList<>();


                                final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Industry");
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        // cropname = getIntent().getStringExtra("name");
                                        // quantity = getIntent().getStringExtra("ph_no");
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            //name_ind.add(ds.child("Name").getValue().toString())
                                            indname.add(ds.child("name").getValue().toString());
                                            indemail.add(ds.child("email").getValue().toString());
                                            indphone.add(ds.child("phone_no").getValue().toString());
                                            indprice.add(ds.child("price").getValue().toString());
                                            indquantity.add(ds.child("quantity").getValue().toString());
                                            indtrans.add(ds.child("transport").getValue().toString());
                                            indcrop.add(ds.child("crop").getValue().toString());
                                            indId.add(ds.child("Id").getValue().toString());
                                            indlat.add(ds.child("lat").getValue().toString());
                                            indlon.add(ds.child("lon").getValue().toString());

                                        }
                                        for (int i = 0; i < indname.size(); i++) {

                                            if(name1.contains(indcrop.get(i))) {
                                                pindname.add(indname.get(i));
                                                pindemail.add(indemail.get(i));
                                                pindphone.add(indphone.get(i));
                                                pindprice.add(indprice.get(i));
                                                pindquantity.add(indquantity.get(i));
                                                pindId.add(indId.get(i));
                                                passlat.add(indlat.get(i));
                                                passlon.add(indlon.get(i));
                                                pindtrans.add(indtrans.get(i));
                                            }

                                        }
                                        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("farmer");
                                        final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                String curlat=dataSnapshot.child(currentuser).child("lat").getValue().toString();
                                                String curlon=dataSnapshot.child(currentuser).child("lon").getValue().toString();
                                                Log.d("passLAt",""+passlat.size());

                                                for(int i=0;i<passlat.size();i++)
                                                {
                                                    int p;
                                                    //LatLng l1=new LatLng(Double.parseDouble(lat.get(i)),Double.parseDouble(lon.get(i)));
                                                    Location a=new Location("location a");
                                                    Location b=new Location("location b");

                                                    a.setLatitude(Double.parseDouble(passlat.get(i)));
                                                    a.setLongitude(Double.parseDouble(passlon.get(i)));

                                                    b.setLatitude(Double.parseDouble(curlat));
                                                    b.setLongitude(Double.parseDouble(curlon));
                                                    float distance=a.distanceTo(b)/1000;
                                                    int xdis=(int)distance;
                                                    dis.add(String.valueOf(xdis));


                                                    if(pindtrans.get(i).equals("yes"))
                                                    {
                                                        p=Integer.parseInt(pindprice.get(i))*Integer.parseInt(pindquantity.get(i))-Integer.parseInt(dis.get(i))*5;
                                                        profit.add(String.valueOf(p));

                                                    }else {

                                                        p=Integer.parseInt(pindprice.get(i))*Integer.parseInt(pindquantity.get(i));
                                                        profit.add(String.valueOf(p));


                                                    }


                                                }
                                                Bundle b1=new Bundle();
                                                Bundle b2=new Bundle();
                                                Bundle b3=new Bundle();
                                                Bundle b4=new Bundle();
                                                Bundle b5=new Bundle();
                                                Bundle b6=new Bundle();
                                                Log.d("size dis",""+dis.size());

                                                b1.putSerializable("id",pindId);
                                                b2.putSerializable("name",pindname);
                                                b3.putSerializable("dis",dis);
                                                b4.putSerializable("quantity",pindquantity);
                                                b5.putSerializable("price",pindprice);
                                                b6.putSerializable("profit",profit);

                                                Intent i=new Intent(PostsListActivity.this,sell_product.class);
                                                i.putExtra("b1",b1);
                                                i.putExtra("b2",b2);
                                                i.putExtra("b3",b3);
                                                i.putExtra("b4",b4);
                                                i.putExtra("b5",b5);
                                                i.putExtra("b6",b6);
                                                startActivity(i);
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });




                                        // go to next intent



                                    }


                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }

                            @Override
                            public void onItemLongClick(View view, int position) {
                                //TODO do your own implementaion on long item click
                            }
                        });

                        return viewHolder;
                    }

                };

        //set adapter to recyclerview
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it present
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter as you type
                firebaseSearch(newText);
                return false;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //handle other action bar item clicks here
        if (id == R.id.action_sort) {
            //display alert dialog to choose sorting
            showSortDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        //options to display in dialog
        String[] sortOptions = {" Newest", " Oldest"};
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by") //set title
                .setIcon(R.drawable.common_google_signin_btn_icon_dark) //set icon
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        // 0 means "Newest" and 1 means "oldest"
                        if (which == 0) {
                            //sort by newest
                            //Edit our shared preferences
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "newest"); //where 'Sort' is key & 'newest' is value
                            editor.apply(); // apply/save the value in our shared preferences
                            recreate(); //restart activity to take effect
                        } else if (which == 1) {
                            {
                                //sort by oldest
                                //Edit our shared preferences
                                SharedPreferences.Editor editor = mSharedPref.edit();
                                editor.putString("Sort", "oldest"); //where 'Sort' is key & 'oldest' is value
                                editor.apply(); // apply/save the value in our shared preferences
                                recreate(); //restart activity to take effect
                            }
                        }
                    }
                });
        builder.show();
    }

}