package com.example.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class login extends AppCompatActivity implements View.OnClickListener {
    Button btn_start;
    FirebaseAuth mAuth;
    EditText editTextemail,editTextpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login Form");
        editTextemail=(EditText) findViewById(R.id.editText);
        editTextpassword=(EditText) findViewById(R.id.editText2);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        mAuth= FirebaseAuth.getInstance();


    }
    private void UserLogin()
    {

        String email=editTextemail.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();
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
        final ProgressDialog progressDialog=ProgressDialog.show(login.this,"Authenticating",null,true);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(login.this,home_page.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    progressDialog.dismiss();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"some error occured",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btn_two:
                startActivity(new Intent(login.this,sign_up.class));
                break;

            case R.id.btn_one:
                UserLogin();
                break;
        }
    }
}