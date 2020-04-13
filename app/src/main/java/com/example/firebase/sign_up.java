package com.example.firebase;


        import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity implements View.OnClickListener{

    EditText editTextemail, editTextpassword, username, phone_no;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    Spinner simplelan,simplestate,simpledistrict,simpletaluka;
    Button btn_one;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setContentView(R.layout.activity_sign_up);

        username=(EditText) findViewById(R.id.quuu);
        phone_no=(EditText)findViewById(R.id.phone);
        editTextemail=(EditText) findViewById(R.id.editText3);
        editTextpassword=(EditText) findViewById(R.id.editText4);
        findViewById(R.id.deal).setOnClickListener(this);
        findViewById(R.id.textView).setOnClickListener(this);
        //progressBar.setVisibility(View.GONE);
        mAuth= FirebaseAuth.getInstance();
        simplelan=(Spinner) findViewById(R.id.spin1);
        simplestate=(Spinner) findViewById(R.id.spin2);
        simpledistrict=(Spinner) findViewById(R.id.spin3);
        simpletaluka=(Spinner) findViewById(R.id.spin4);
        ArrayAdapter <CharSequence> adapter=ArrayAdapter.createFromResource(
                this,R.array.spinner_language,android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        simplelan.setAdapter(adapter);

        ArrayAdapter <CharSequence> adapter1=ArrayAdapter.createFromResource(
                this,R.array.spinner_state,android.R.layout.simple_spinner_item
        );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        simplestate.setAdapter(adapter1);

        ArrayAdapter <CharSequence> adapter2=ArrayAdapter.createFromResource(
                this,R.array.spinner_district,android.R.layout.simple_spinner_item
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        simpledistrict.setAdapter(adapter2);

        ArrayAdapter <CharSequence> adapter3=ArrayAdapter.createFromResource(
                this,R.array.spinner_taluka,android.R.layout.simple_spinner_item
        );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        simpletaluka.setAdapter(adapter3);



    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            //handle the already login user
        }
    }

    private void UserLogin()
    {

        final String email=editTextemail.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();
        final String name=username.getText().toString().trim();
        final String phone=phone_no.getText().toString().trim();
        final String language=simplelan.getSelectedItem().toString();
        final String state=simplestate.getSelectedItem().toString();
        final String dis=simpledistrict.getSelectedItem().toString();
        final String taluka=simpletaluka.getSelectedItem().toString();
        if(email.isEmpty())
        {
            editTextemail.setError("email is required");
            editTextemail.requestFocus();
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextemail.setError("Please enter valid error");
            editTextemail.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            editTextpassword.setError("minimum length of password should 6");
            editTextpassword.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            editTextpassword.setError("password is required");
            editTextpassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("farmer");
                          //  String Id=
                            String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            mDatabase.child(currentuser).child("Name").setValue(name);
                            mDatabase.child(currentuser).child("Email").setValue(email);
                            mDatabase.child(currentuser).child("phone_no").setValue(phone);
                            mDatabase.child(currentuser).child("Crop").setValue("wheat sugercane");
                            mDatabase.child(currentuser).child("lat").setValue("18");
                            mDatabase.child(currentuser).child("lon").setValue("78.80");
                           // mDatabase.child(currentuser).child("Name").setValue(name);
                            mDatabase.child(currentuser).child("Id").setValue(currentuser);




                        }
                        else
                        {
                            Toast.makeText(sign_up.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.textView:
                startActivity(new Intent(this,login.class));
                break;

            case R.id.deal:
                UserLogin();
                break;
        }

    }



}