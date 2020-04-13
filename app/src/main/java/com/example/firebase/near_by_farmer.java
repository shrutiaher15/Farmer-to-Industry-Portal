package com.example.firebase;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.adaptor.Farmer_nearby_adaptor;

import java.util.ArrayList;
import java.util.List;

public class near_by_farmer extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Farmer_nearby_adaptor mAdapter;
    private List<farmer_detail> mProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_farmer);
        Intent i=getIntent();
        Bundle args1=i.getBundleExtra("bundle1");
        Bundle args2=i.getBundleExtra("bundle2");
        Bundle args3=i.getBundleExtra("bundle3");
        Bundle arg4=i.getBundleExtra("bundle4");
        ArrayList<String> nameF=(ArrayList<String>) args1.getSerializable("farmer_name");
        ArrayList<String>phoneF=(ArrayList<String>)args2.getSerializable("farmer_phno");
        ArrayList<String>idf=(ArrayList<String>)args3.getSerializable("farmer_id");
        ArrayList<String>disf=(ArrayList<String>)arg4.getSerializable("farmer_dis");



        //getting the recyclerview from xml
        mRecyclerView = (RecyclerView) findViewById(R.id.idRecyclerView);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("namef",""+nameF.size());
        Log.d("phoneF",""+phoneF.size());
        Log.d("idf",""+idf.size());
        Log.d("dis",""+disf.size() );

        //Populate the products
        mProductList = new ArrayList<>();
        for(int j=0;j<nameF.size();j++) {
            mProductList.add(new farmer_detail(nameF.get(j), phoneF.get(j),idf.get(j),disf.get(j)));
        }
        //mProductList.add(new farmer_detail("Pineapple","544645"));

        //set adapter to recyclerview
        mAdapter = new Farmer_nearby_adaptor(mProductList,this);
        mRecyclerView.setAdapter(mAdapter);
    }
}