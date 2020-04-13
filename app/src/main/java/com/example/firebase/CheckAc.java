package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class CheckAc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Intent i=getIntent();
        Bundle args1=i.getBundleExtra("b1");
        ArrayList<String> nameF=(ArrayList<String>) args1.getSerializable("id");
        Log.d("myxxxx",""+nameF.size());
    }
}
