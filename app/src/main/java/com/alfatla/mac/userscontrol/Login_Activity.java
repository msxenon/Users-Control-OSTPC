package com.alfatla.mac.userscontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import static com.alfatla.mac.userscontrol.UserControl.*;

/**
 * Created by mac on 2/9/18.
 */

public class Login_Activity extends AppCompatActivity {
    EditText uname,password;
    static {
        usersMap = new HashMap<>();
        UserPojo userPojo = new UserPojo();
        userPojo.setPassword("user");
        userPojo.setAdmin(false);
        usersMap.put("user",userPojo);
        UserPojo userPojo1 = new UserPojo();
        userPojo1.setPassword("admin");
        userPojo1.setAdmin(true);
        usersMap.put("admin",userPojo1);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        uname = findViewById(R.id.uname);
        password = findViewById(R.id.upass);


    }
    public void login (View view){
        if (uname.getText().toString().isEmpty()){
            Toast.makeText(this,"User name is empty",Toast.LENGTH_LONG).show();
            return;
        }
        if (password.getText().toString().isEmpty()){
            Toast.makeText(this,"Password is empty",Toast.LENGTH_LONG).show();
            return;
        }
        if (usersMap.get(uname.getText().toString())!=null){
            UserPojo userPojo = usersMap.get(uname.getText().toString());
            if (password.getText().toString().equals(userPojo.getPassword())){
                //success login
                Intent i = new Intent(Login_Activity.this,UserControl.class);
                i.putExtra("isAdmin",userPojo.isAdmin());
               loggedInName = uname.getText().toString();
                startActivity(i);
                finish();
            }else{
                //wrong password
                Toast.makeText(this,"Password is not correct! did you forgot your password?",Toast.LENGTH_LONG).show();
            }
        }else{
            //user name not found
            Toast.makeText(this,"User not found",Toast.LENGTH_LONG).show();
        }
    }
}

