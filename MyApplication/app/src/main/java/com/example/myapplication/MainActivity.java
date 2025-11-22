package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;
    private FirebaseAuth mAuth;

    User usertest = new User("kolya","1234");
    User usertest2 = new User("vasya","12345");
    public User getUsertest2() {
        return usertest2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        etUsername = findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        setupClickListeners();
    }

    private void setupClickListeners() {
        Intent intent = new Intent(this, GroupList.class);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                signIn(email, password); //yarematk@gmail.com 123456 - логін пароль -
              /*  if (etUsername.getText().toString().equals(usertest.getLogin())
                        && etPassword.getText().toString().equals(usertest.getPassword())){

                    Toast.makeText(MainActivity.this,
                            "nelox",
                            Toast.LENGTH_SHORT).show();


                    startActivity(intent);

                } else if (etUsername.getText().toString().equals(usertest2.getLogin())
                        && etPassword.getText().toString().equals(usertest2.getPassword())){

                    Toast.makeText(MainActivity.this,
                            "nelox (vasya)",
                            Toast.LENGTH_SHORT).show();

                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "lox", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
    private void signIn(String email, String password) {
        Intent intent = new Intent(this, GroupList.class);
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this,
                                "ivanzolo",
                                Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "melstroyGay",
                                Toast.LENGTH_SHORT).show();
                        Exception e = task.getException();
                    }
                });
    }
}










