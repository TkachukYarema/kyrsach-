package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private EditText edEmail;
    private EditText edPassword;
    private Button btnReg;
    private Button btnSignIn;
    private Supabase SupaBaseClient;

    private  Intent intent;
    String AnonKeyclient = "sb_publishable_pfOqNPwzinU_KL0Usgm1Cg_VoGO2Oex";
    String SupUrlclient = "https://xdvqoufyfbqhyqdsthna.supabase.co";
    private void saveAccessToken(String token) {


        Log.d("AUTH", "Збережено токен: " + token);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        btnReg = findViewById(R.id.btnReg);
        edPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.tvSignIn);
        edEmail = findViewById(R.id.email);

        SupaBaseClient= new Supabase(SupUrlclient, AnonKeyclient);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String password = edPassword.getText().toString().trim();
                String email = edEmail.getText().toString().trim();

                if (email.isEmpty() || password.length() < 6) {
                    Toast.makeText(MainActivity.this, "Email та Пароль (мін. 6 символів) є обов'язковими.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SupaBaseClient.signUpUser(email, password, new Supabase.ApiCallback() {

                    @Override
                    public void onSuccess(String resultJson) {
                        runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, "Реєстрація успішна. Виконуємо вхід...", Toast.LENGTH_SHORT).show();

                            SupaBaseClient.signUpUser(email, password, new Supabase.ApiCallback() {

                                @Override
                                public void onSuccess(String resultJson) {
                                    runOnUiThread(() -> {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(resultJson);

                                            if (jsonResponse.has("access_token")) {

                                                String accessToken = jsonResponse.getString("access_token");
                                                saveAccessToken(accessToken);

                                                Toast.makeText(MainActivity.this, "Вхід успішний! Перехід на головний екран.", Toast.LENGTH_LONG).show();

                                                Intent intent = new Intent(MainActivity.this, GroupList.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Toast.makeText(MainActivity.this, "Помилка: Токен відсутній після входу.", Toast.LENGTH_LONG).show();
                                            }

                                        } catch (JSONException e) {
                                            Toast.makeText(MainActivity.this, "Критична помилка: Парсинг токена.", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(String errorMessage) {
                                    runOnUiThread(() -> {
                                        Toast.makeText(MainActivity.this, "Помилка автоматичного входу: " + errorMessage, Toast.LENGTH_LONG).show();
                                    });
                                }
                            });
                        });
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        runOnUiThread(() -> {
                            Toast.makeText(MainActivity.this, "Помилка реєстрації: " + errorMessage, Toast.LENGTH_LONG).show();
                        });
                    }
                });
            }
        });
    }
}