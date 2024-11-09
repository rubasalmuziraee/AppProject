package com.example.expensestracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuth;

public class ReseatActivity extends AppCompatActivity {

    private EditText email;
    private Button btn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reseat);
        email=(EditText) findViewById(R.id.reset_email);
        btn=(Button) findViewById(R.id.reset_btn);
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail=email.getText().toString().trim();

                if(TextUtils.isEmpty(useremail)){
                    Toast.makeText(ReseatActivity.this,"Please enter the email",Toast.LENGTH_LONG).show();
                }else {
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ReseatActivity.this,"please check your mail account\nand reset"+"your password",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                            }else {
                                String message=task.getException().getMessage();
                                Toast.makeText(ReseatActivity.this,"Error Occured.."+message,Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }
}