package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class GroupList extends AppCompatActivity {
    private EditText groupfind;
    private User loggedInUser;
    private Button creategroup;
    private EditText friendfind;
    private  User usertestgroup = new User("kolya","1234");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        groupfind=findViewById(R.id.etGroupName);
        friendfind=findViewById(R.id.etFriendsUsername);
        creategroup=findViewById(R.id.btnCreateGroup);
        setupClickListeners();
    }

    private void setupClickListeners() {
        Group group1 = new Group("goida");
        Intent intent = new Intent(this, Screen1.class);


        creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!groupfind.getText().toString().equals(group1.getGroupname())&&!friendfind.getText().toString().equals(usertestgroup.getLogin())){
                    //LinkedList<User> abc = new LinkedList<>();
                    //abc.add(usertestgroup);


                    startActivity(intent);
                } else if (friendfind.getText().toString().equals(usertestgroup.getLogin())&&groupfind.getText().toString().equals(group1)) {



                    Toast.makeText(GroupList.this, "lox", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}