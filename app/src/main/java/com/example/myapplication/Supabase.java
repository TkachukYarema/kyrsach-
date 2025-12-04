package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class Supabase {

        public interface ApiCallback {
                void onSuccess(String resultJson);
                void onFailure(String errorMessage);
        }

        private String supUrl = "https://xdvqoufyfbqhyqdsthna.supabase.co";
        private String AnonKey = "sb_publishable_pfOqNPwzinU_KL0Usgm1Cg_VoGO2Oex";
        private final OkHttpClient client = new OkHttpClient();

        public Supabase (String supUrl, String AnonKey){
                this.supUrl = supUrl;
                this.AnonKey = AnonKey;
        }
//метод для отримання усіх данних в таблиці у вигляді json
        public void fetchData(String tableName, final ApiCallback callBack){
                String url = supUrl + "/rest/v1/" + tableName + "?select=*";

                Headers headers = new Headers.Builder()
                        .add("apikey", AnonKey)
                        .add("Authorization", "Bearer " + AnonKey)
                        .add("Accept","application/json")
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .get()
                        .build();

                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                callBack.onFailure(e.getMessage());
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                if(response.isSuccessful()){
                                        String responseBody = response.body() != null ? response.body().string() : "[]";
                                        callBack.onSuccess(responseBody);
                                } else {
                                        callBack.onFailure("HTTP Error: " + response.code() + " - " + response.message());
                                }
                        }
                });
        }
//Метод для реєстрації та отримання логів (Працює)
        public void signUpUser(String email, String password, final ApiCallback callBack){
                String url = supUrl + "/auth/v1/signup";

                String jsonbody = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";

                Headers headers = new Headers.Builder()
                        .add("apikey", AnonKey)
                        .add("Authorization", "Bearer " + AnonKey)
                        .add("Content-Type", "application/json")
                        .add("Accept", "application/json")
                        .build();

                okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(jsonbody,
                        okhttp3.MediaType.parse("application/json; charset=utf-8"));

                Request request = new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .post(requestBody)
                        .build();
                Log.d("AUTH_DEBUG", "Sending Supabase request to: " + url);
                client.newCall(request).enqueue(new Callback() {

                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                callBack.onFailure(e.getMessage());
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                if (response.isSuccessful()) {
                                        String responseBody = response.body() != null ? response.body().string() : "{}";
                                        callBack.onSuccess(responseBody);
                                } else {
                                        String responseBody = response.body() != null ? response.body().string() : response.message();
                                        callBack.onFailure("Sign Up Error: " + response.code() + " - " + responseBody);
                                }
                        }
                });
        }
        //Метод для логіну (хз)

        public void signInUser(String email, String password, final ApiCallback callBack){
                String url = supUrl + "/auth/v1/token?grant_type=password";

                String jsonbody = "{\"email\": \"" + email + "\" , \"password\": \"" + password +"\"}";

                Headers headers=new Headers.Builder()
                        .add("apikey",AnonKey)
                        .add("Authorization", "Bearer " + AnonKey)
                        .add("Content-Type", "application/json")
                        .add("Accept", "application/json")
                        .build();

                okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(jsonbody,
                        okhttp3.MediaType.parse("application/json; charset=utf-8"));

                Request request = new Request.Builder()
                        .url(url)
                        .headers(headers)
                        .post(requestBody)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                callBack.onFailure(e.getMessage());
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                if (response.isSuccessful()) {
                                        String responseBody = response.body() != null ? response.body().string() : "{}";
                                        callBack.onSuccess(responseBody);
                                } else {
                                        String responseBody = response.body() != null ? response.body().string() : response.message();
                                        callBack.onFailure("Sign In Error: " + response.code() + " - " + responseBody);
                                }
                        }
                });
        }
}