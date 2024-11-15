package com.example.expensestracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensestracker.mainactivity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPass;
    private Button btnReg;
    private TextView mSignin;
    private ProgressDialog mDialog;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");



        mAuth = FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);
        registration();
    }

    private void registration(){

        mEmail=findViewById(R.id.email_reg);
        mPass=findViewById(R.id.password_reg);
        btnReg=findViewById(R.id.btn_reg);
        mSignin=findViewById(R.id.Signin_here);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email=mEmail.getText().toString().trim();
                String pass=mPass.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email Required..");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    mPass.setError("Password Required..");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Complete",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            mDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Field..",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            }
        });
    }
}