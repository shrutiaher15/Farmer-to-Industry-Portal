package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.android.material.appbar.CollapsingToolbarLayout;

public class sixth_page extends AppCompatActivity {
    CollapsingToolbarLayout collapse;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_page);
        collapse=(CollapsingToolbarLayout) findViewById(R.id.collapse6);
        collapse.setTitle("Cotton");
        Toolbar toolbar=(Toolbar) findViewById(R.id.tool6);
        //setSupportActionBar(toolbar);

        btn=(Button) findViewById(R.id.btn_help5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1800-180-1551"));
                startActivity(intent);
            }
        });
    }
}