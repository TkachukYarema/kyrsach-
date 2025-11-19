package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;

    User usertest = new User("kolya","1234");
    User usertest2 = new User("vasya","12345");
    public User getUsertest2() {
        return usertest2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                if (etUsername.getText().toString().equals(usertest.getLogin())
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
                }
            }
        });
    }
}










