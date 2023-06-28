package com.example.recipeapplec;


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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {
    Button btnRegis, btnchange;
    EditText email, username, password, cpassword;
    FirebaseAuth mAuth;

    public Integer cekPass(TextView a){
        String b=a.getText().toString();
        if(b.length()<5){
            return 1;
        }

        char[] x = a.getText().toString().toCharArray();

        for(int i=0;i<a.getText().toString().length();i++){
            if( !( (x[i]>='0' && x[i]<='9') || (x[i]>='A' && x[i]<='Z') || (x[i]>='a' && x[i]<='z') )){
                return 2;
            }
        }
        return 0;
    }

    public boolean pasmatch(TextView a, TextView b){
        if(a.getText().toString().matches(b.getText().toString())){
            return true;
        }
        return false;
    }

    public boolean cekEmail(TextView a){
        String b=a.getText().toString();
        if(b.contains(".com") && b.contains("@")){
            return true;
        }
        return false;
    }

    public void salert(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        Intent move = new Intent(this,registration.class);
        btnRegis = findViewById(R.id.btnRegis);
        btnchange = findViewById(R.id.btnchange);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        mAuth = FirebaseAuth.getInstance();


        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email, Username, Password, confirmpass;
                Email = String.valueOf(email.getText());
                Username = String.valueOf(username.getText());
                Password = String.valueOf(password.getText());
                confirmpass = String.valueOf(cpassword.getText());

                if(TextUtils.isEmpty(Email) && TextUtils.isEmpty(Username) && TextUtils.isEmpty(Password) && TextUtils.isEmpty(confirmpass)){
                    Toast.makeText(registration.this,"Please fill the credentials", Toast.LENGTH_SHORT).show(); return;
                }
                else{
                    if(!cekEmail(email)){
                        salert("email has to contain '.com' and '@'"); return;
                    }

                    if(cekPass(password)==1){
                        salert("password length has to be more than 5"); return;
                    }
                    else if(cekPass(password)==2){
                        salert("password is only alphanumerical"); return;
                    }

                    if(!pasmatch(password, cpassword)){
                        salert("password has to match"); return;
                    }

                    salert("Registration successful");

                }

                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(registration.this, "Account Created", Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        btnchange.setOnClickListener(e->{
            Intent intent = new Intent(registration.this, login.class);
            startActivity(intent);
        });



    }
}