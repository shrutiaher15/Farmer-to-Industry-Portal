package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class crop_info extends AppCompatActivity {
    CardView card,card1,card2,card3,card4,card5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_info);
        card5=(CardView) findViewById(R.id.card6);
        card4=(CardView) findViewById(R.id.card5);
        card2=(CardView) findViewById(R.id.card3);
        card=(CardView) findViewById(R.id.card1);
        card1=(CardView) findViewById(R.id.card2);
        card3=(CardView) findViewById(R.id.card4);
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(crop_info.this,sixth_page.class));
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(crop_info.this,fifth_page.class));
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(crop_info.this,fourth_page.class));
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(crop_info.this,third_page.class));
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(crop_info.this,second_page.class));
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(crop_info.this,first_page.class));
            }
        });
    }
}
