package com.example.instagramclonejava.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.instagramclonejava.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private FirebaseAuth auth;
    private static final String EMAİL_PATTERN = "[a-zA-Z0-9_\\-]+@([a-zA-Z0-9_\\-]+\\.)+(com)";
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(MainActivity.this, FeedActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void signInClicked(View view) {

        String email = mainBinding.emailText.getText().toString();
        String password = mainBinding.passwordText.getText().toString();

        if (email.equals("") || password.equals("")){
            Toast.makeText(this,"Email adresinizi ve parolanızı girin",Toast.LENGTH_LONG).show();
        }else {
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }

    public void signUpClicked(View view) {

        String password = mainBinding.passwordText.getText().toString();
        String email = mainBinding.emailText.getText().toString();

        Pattern patternEmail = Pattern.compile(EMAİL_PATTERN);
        Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcherEmail = patternEmail.matcher(email);
        Matcher matcherPassword = patternPassword.matcher(password);

        if(matcherEmail.matches() && matcherPassword.matches()){
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });

        }else {
            if(!matcherEmail.matches()){
                Toast.makeText(getApplicationContext(),"Email adresinizi dogru girin",Toast.LENGTH_LONG).show();
            }else if(!matcherPassword.matches()){
                Toast.makeText(getApplicationContext(),"Şifre en az bir rakam [0-9] içermelidir.\n" +
                        "Parola en az bir küçük Latin karakteri [az] içermelidir.\n" +
                        "Parola en az bir büyük Latin karakteri [AZ] içermelidir.\n" +
                        "Şifre, gibi en az bir özel karakter içermelidir ! @ # & ( ).\n" +
                        "Şifre en az 8 karakter ve en fazla 20 karakter uzunluğunda olmalıdır.",Toast.LENGTH_LONG).show();
            }

        }

    }
}