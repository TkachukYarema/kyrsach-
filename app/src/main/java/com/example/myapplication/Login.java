package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONException;
import org.json.JSONObject;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;


public class Login extends AppCompatActivity {

private Intent intent;
    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignUp;
    String AnonKeyclient = "sb_publishable_pfOqNPwzinU_KL0Usgm1Cg_VoGO2Oex";
    String SupUrlclient = "https://xdvqoufyfbqhyqdsthna.supabase.co";
    private Supabase SupaBaseClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.tvSignUp);

        SupaBaseClient = new Supabase(SupUrlclient,AnonKeyclient);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Введіть пошту та пароль", Toast.LENGTH_SHORT).show();
                    return;


                }
                SupaBaseClient.signUpUser(email, password, new Supabase.ApiCallback() {
                    @Override
                    public void onSuccess(String resultJson) {
                        runOnUiThread(() -> {
                        try{
                            JSONObject jsonResponse = new JSONObject(resultJson);



                        } catch (JSONException e) {

                        }

                        });
                    }

                    @Override
                    public void onFailure(String errorMessage) {

                    }
                });


            }
        });
    }
}



