package com.alfatla.mac.userscontrol;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static com.alfatla.mac.userscontrol.UserControl.*;

/**
 * Created by mac on 2/9/18.
 */

public class UsersAdapter extends RecyclerView.Adapter<UserViewHoldr> {
    @Override
    public UserViewHoldr onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_vh,parent,false);
        return new UserViewHoldr(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHoldr holder, int position) {
              holder.setData((String) usersMap.keySet().toArray()[position], ((UserPojo) usersMap.values().toArray()[position]).isAdmin());
             holder.itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     if (holder.onlyName.equals(UserControl.loggedInName)) {
                        // Toast.makeText(view.getContext(), "You can't delete your self!", Toast.LENGTH_LONG).show();
                     } else {
                         usersMap.remove(holder.userName.getText().toString());
                         Toast.makeText(view.getContext(), holder.userName.getText().toString() + " Deleted!", Toast.LENGTH_LONG).show();
                         notifyDataSetChanged();
                     }
                 }
             });

    }

    @Override
    public int getItemCount() {
        return usersMap.size();
    }
}
