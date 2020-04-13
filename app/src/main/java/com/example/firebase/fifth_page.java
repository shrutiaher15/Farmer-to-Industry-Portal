package com.example.firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class fifth_page extends AppCompatActivity {
    CollapsingToolbarLayout collapse;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_page);
        collapse=(CollapsingToolbarLayout) findViewById(R.id.collapse5);
        collapse.setTitle("Wheat");
        Toolbar toolbar=(Toolbar) findViewById(R.id.tool5);
      //  setSupportActionBar(toolbar);
        btn=(Button) findViewById(R.id.btn_help4);
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