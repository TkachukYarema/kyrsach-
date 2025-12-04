package com.example.myapplication;
import java.io.Serializable;
public class User implements Serializable {
  private    String username;



    private String password;
    private String name;

public User(String username,String password){
    this.username=username;
    this.password=password;

}
    public String getName(){return name;}
    public String getLogin() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

