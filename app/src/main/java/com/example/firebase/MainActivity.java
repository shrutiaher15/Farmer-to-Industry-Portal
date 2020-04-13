package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText mail,name,phno,crop,latitude,longitude,transport,price,quantity;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.indname);
        mail=findViewById(R.id.email);
        crop=findViewById(R.id.quuu);
        phno=findViewById(R.id.ph_no);
        latitude=findViewById(R.id.lat);
        longitude=findViewById(R.id.lon);
        transport=findViewById(R.id.tran);
        price=findViewById(R.id.indname);
        quantity=findViewById(R.id.quuu);

        submit=findViewById(R.id.yes);
        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Industry");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                String name1=name.getText().toString();
                String phno1=phno.getText().toString();
                String crop1=crop.getText().toString();
                String mail1=mail.getText().toString();
                String lat1=latitude.getText().toString();
                String lon1=longitude.getText().toString();
                String trans=transport.getText().toString();
                String price1=price.getText().toString();
                String quantity1=quantity.getText().toString();
                mDatabase.child(Id).child("name").setValue(name1);
                mDatabase.child(Id).child("phone_no" ).setValue(phno1);
                mDatabase.child(Id).child("email" ).setValue(mail1);
                mDatabase.child(Id).child("crop").setValue(crop1);
                mDatabase.child(Id).child("lat").setValue(lat1);
                mDatabase.child(Id).child("lon").setValue(lon1);
                mDatabase.child(Id).child("price").setValue(price1);
                mDatabase.child(Id).child("transport").setValue(trans);
                mDatabase.child(Id).child("quantity").setValue(quantity1);
                mDatabase.child(Id).child("description").setValue("kdlkfs sdkfj lsdjfl sldfjl sldfjl sldfj");
                mDatabase.child(Id).child("Id").setValue(Id);
                Intent i1=new Intent(MainActivity.this,splashScreen.class);
                startActivity(i1);
            }
        });
    }
}
