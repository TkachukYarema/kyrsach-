package com.example.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.android.material.internal.ParcelableSparseArray;

import java.io.Serializable;
public class User implements Parcelable {
  private    String login;



    private String password;
    private String name;

public User(String login,String password){
    this.login=login;
    this.password=password;

}

    protected User(Parcel in) {
        login = in.readString();
        password = in.readString();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(login);
dest.writeString(password);
dest.writeString(name);
    }
}

