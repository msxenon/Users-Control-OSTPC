package com.alfatla.mac.userscontrol;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mac on 2/9/18.
 */

public class UserViewHoldr extends RecyclerView.ViewHolder {
      TextView userName;
      ImageView imageView;
      String onlyName;
    public UserViewHoldr(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.userName);
        imageView = itemView.findViewById(R.id.deleteIV);
    }
    public void setData(String name,boolean isMe){
        onlyName = name;
        if (isMe)
            imageView.setVisibility(View.INVISIBLE);
        else
            imageView.setVisibility(View.VISIBLE);
        String n = name;
        if (UserControl.usersMap.get(n).isAdmin()){
            n += " (Admin)";
        }
        userName.setText(n);
    }
}
