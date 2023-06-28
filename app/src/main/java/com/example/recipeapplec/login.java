package com.example.recipeapplec;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class login extends AppCompatActivity {
    Button btnlogin, btnchange;
    EditText email, password;
    FirebaseAuth mAuth;



    public boolean ck(TextView a){
        if(TextUtils.isEmpty(a.getText().toString())){
            return true;
        }
        return false;
    }

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
        setContentView(R.layout.activity_login);


        String Email, Password;
        Intent move = new Intent(this,registration.class);
        btnlogin = findViewById(R.id.btnlogin);
        btnchange = findViewById(R.id.btnchange);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Intent tohome = new Intent(this, Home.class);
        Email = String.valueOf(email.getText());
        Password = String.valueOf(password.getText());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ck(email)&&ck(password)){
                    salert("please fill all credentials");
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

                    String uEmail = email.getText().toString();
                    String uPass = password.getText().toString();
                    salert("login successful");
                    startActivity(tohome);

                }
                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


        btnchange.setOnClickListener(e->{
            startActivity(move);
        });
    }
}