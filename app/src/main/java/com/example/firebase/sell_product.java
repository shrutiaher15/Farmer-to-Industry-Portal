package com.example.firebase;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.adaptor.GroceryProductAdapter;

import java.util.ArrayList;
import java.util.List;
/***** user can see industry order */
public class sell_product extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GroceryProductAdapter mAdapter;
    private List<Grocery> mProductList;
    Button b1;
    String cropname, quantity;
    ArrayList<String> indname, inddis, indphone, indemail, indprice, indquantity, indId, indcrop,indlat,indlon,indtrans;
    ArrayList<String> pindname, pinddis, pindphone, pindemail, pindprice, pindquantity, pindtrans, pindcrop,pindId,passlat,passlon;

    //  final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Industry");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
       /* indname = new ArrayList<>();
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

        pindname = new ArrayList<>();
        pindemail = new ArrayList<>();
        pindphone = new ArrayList<>();
        pindprice = new ArrayList<>();
        pinddis = new ArrayList<>();
        pindtrans=new ArrayList<>();
        pindquantity = new ArrayList<>();
        pindcrop = new ArrayList<>();
        pindId=new ArrayList<>();
        passlat=new ArrayList<>();
        passlon=new ArrayList<>();



        cropname = getIntent().getStringExtra("name");
        quantity = getIntent().getStringExtra("ph_no");

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

                x(pindname,pindprice,pindquantity,pindId,passlat,passlon,pindtrans);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    void x(final ArrayList<String> pname, final ArrayList<String> pprice, final ArrayList<String> pquantity, final ArrayList<String> pId, final ArrayList<String>lat, final  ArrayList<String>lon,final ArrayList<String> trans)
    {
        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.idRecyclerView);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String>dis=new ArrayList<>();
        final ArrayList<String>profit=new ArrayList<>();

        //Populate the products
        mProductList = new ArrayList<>();
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("farmer");
        //  String Id=
        final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String curlat=dataSnapshot.child(currentuser).child("lat").getValue().toString();
                String curlon=dataSnapshot.child(currentuser).child("lon").getValue().toString();
               // Location a=new Location();

                for(int i=0;i<lat.size();i++)
                {
                    int p;
                    //LatLng l1=new LatLng(Double.parseDouble(lat.get(i)),Double.parseDouble(lon.get(i)));
                    Location a=new Location("location a");
                    Location b=new Location("location b");

                    a.setLatitude(Double.parseDouble(lat.get(i)));
                    a.setLongitude(Double.parseDouble(lon.get(i)));

                    b.setLatitude(Double.parseDouble(curlat));
                    b.setLongitude(Double.parseDouble(curlon));
                    float distance=a.distanceTo(b)/1000;
                    int xdis=(int)distance;
                    dis.add(String.valueOf(xdis));


                    if(pindtrans.get(i).equals("yes"))
                    {
                         p=Integer.parseInt(pprice.get(i))*Integer.parseInt(pquantity.get(i))-Integer.parseInt(dis.get(i))*5;
                         profit.add(String.valueOf(p));

                    }else {

                        p=Integer.parseInt(pprice.get(i))*Integer.parseInt(pquantity.get(i));
                        profit.add(String.valueOf(p));


                    }
                    y(pname,pprice,pquantity,pId,dis,profit);






                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    void y(ArrayList<String> pname,ArrayList<String> pprice,ArrayList<String>pquantity,ArrayList<String> pId,ArrayList<String> dis,ArrayList<String>profit)
    {
        /*ArrayList<String> name1, price1 , quantity1, id1, dis1, profit1;
            name1=new ArrayList<>();
            price1=new ArrayList<>();
            quantity1=new ArrayList<>();
            id1=new ArrayList<>();
            dis1=new ArrayList<>();
            profit1=new ArrayList<>();

            Log.d("dd",""+pname.size());




        for(int i=0;i<pname.size();i++)
            name1.add(pname.get(i));


        Log.d("nname1",""+pname.size());


        for(int i=0;i<pprice.size();i++)
            price1.add(pprice.get(i));




        for(int i=0;i<pquantity.size();i++)
            quantity1.add(pquantity.get(i));




        for(int i=0;i<pname.size();i++)
            id1.add(pId.get(i));




        for(int i=0;i<dis.size();i++)
            dis1.add(dis.get(i));






        for(int i=0;i<profit.size();i++)
            profit1.add(profit.get(i));





        for(int i=0;i<profit1.size();i++)
        {



            for(int j=i+1;j<profit1.size();j++)
            {
                String tname,tprice,tquantity,tId,tprofit,tdis;
                int temp1=Integer.parseInt(profit1.get(i));
                int temp2=Integer.parseInt(profit1.get(j));
                    if(temp1<temp2)
                    {
                        tname=name1.get(i);
                        name1.set(i, name1.get(j));
                        name1.set(j,tname);

                        tprice=price1.get(i);
                        price1.set(i,price1.get(j));
                        price1.set(j,tprice);

                        tquantity=quantity1.get(i);
                        quantity1.set(i,quantity1.get(j));
                        quantity1.set(j,tquantity);

                        tId=pId.get(i);
                        id1.set(i,id1.get(j));
                        id1.set(j,tId);


                        tdis=dis1.get(i);
                        dis1.set(i,dis1.get(j));
                        dis1.set(j,tdis);


                        tprofit=profit1.get(i);
                        profit1.set(i,profit1.get(j));
                        profit1.set(j,tprofit);


                    }


            }


        }

        Log.d("size" ,""+profit1.size());


        Log.d("size",""+dis.size());*/

        Intent i=getIntent();
        Bundle args1=i.getBundleExtra("b1");
        Bundle args2=i.getBundleExtra("b2");
        Bundle args3=i.getBundleExtra("b3");
        Bundle args4=i.getBundleExtra("b4");
        Bundle args5=i.getBundleExtra("b5");
        Bundle args6=i.getBundleExtra("b6");
        Bundle args7=i.getBundleExtra("b7");



        ArrayList<String> id=(ArrayList<String>) args1.getSerializable("id");
        ArrayList<String> name=(ArrayList<String>) args2.getSerializable("name");
        ArrayList<String> dis=(ArrayList<String>) args3.getSerializable("dis");
        ArrayList<String> quantity=(ArrayList<String>) args4.getSerializable("quantity");
        ArrayList<String> price=(ArrayList<String>) args5.getSerializable("price");
        ArrayList<String> profit=(ArrayList<String>) args6.getSerializable("profit");

        for(int i1=0;i1<profit.size();i1++)
        {



            for(int j=i1+1;j<profit.size();j++)
            {
                String tname,tprice,tquantity,tId,tprofit,tdis;
                int temp1=Integer.parseInt(profit.get(i1));
                int temp2=Integer.parseInt(profit.get(j));
                if(temp1<temp2)
                {
                    tname=name.get(i1);
                    name.set(i1, name.get(j));
                    name.set(j,tname);

                    tprice=price.get(i1);
                    price.set(i1,price.get(j));
                    price.set(j,tprice);

                    tquantity=quantity.get(i1);
                    quantity.set(i1,quantity.get(j));
                    quantity.set(j,tquantity);

                    tId=id.get(i1);
                    id.set(i1,id.get(j));
                    id.set(j,tId);
                    tdis=dis.get(i1);
                    dis.set(i1,dis.get(j));
                    dis.set(j,tdis);


                    tprofit=profit.get(i1);
                    profit.set(i1,profit.get(j));
                    profit.set(j,tprofit);
                }
            }
        }
        mRecyclerView=findViewById(R.id.idRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductList = new ArrayList<>();

        for(int z=0;z<id.size();z++)
        {

            mProductList.add(new Grocery(name.get(z),price.get(z),"8999",quantity.get(z),id.get(z),dis.get(z),profit.get(z)));
        }
        mAdapter = new GroceryProductAdapter(mProductList, this);
        mRecyclerView.setAdapter(mAdapter);


    }
}