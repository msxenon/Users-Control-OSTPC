package com.alfatla.mac.userscontrol;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by mac on 2/9/18.
 */

public class UserControl extends AppCompatActivity {
    public static Map<String,UserPojo> usersMap ;
    public static String loggedInName;
    RecyclerView recyclerView;
    TextView add;
    UsersAdapter usersAdapter;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!getIntent().getBooleanExtra("isAdmin",false)){
            setContentView(R.layout.hello_user);
            TextView textView = findViewById(R.id.helloTv);
            textView.setText("Hello User, Welcome back");
return;
        }
        setContentView(R.layout.user_control);
        recyclerView = findViewById(R.id.users_rv);
        add = findViewById(R.id.add);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        usersAdapter = new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
        context = this;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Add User details");

                View viewInflated = LayoutInflater.from(context).inflate(R.layout.add_user, (ViewGroup) view.getRootView(), false);
                final EditText username,pass;
                username = viewInflated.findViewById(R.id.adduname);
                pass = viewInflated.findViewById(R.id.addupass);
                final CheckBox checkBox = viewInflated.findViewById(R.id.addisadmin);
                builder.setView(viewInflated);

                 builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String u = username.getText().toString();
                        String p = pass.getText().toString();

                        if (u.isEmpty() || p.isEmpty()){
                            Toast.makeText(view.getContext(),"username and password must not be empty!",Toast.LENGTH_LONG).show();

                            return;
                        }

                        if (usersMap.get(u) == null){
                            //add user
                            UserPojo n = new UserPojo();
                            n.setAdmin(checkBox.isChecked());
                            n.setPassword(p);
                            usersMap.put(u,n);
                            Toast.makeText(view.getContext(),"New user added successfully!",Toast.LENGTH_LONG).show();

                        }else{
                            //user already used
                            Toast.makeText(view.getContext(),"username already in use",Toast.LENGTH_LONG).show();

                        }
                     }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        });
    }

}
