package com.alfatla.mac.userscontrol;

/**
 * Created by mac on 2/9/18.
 */

public class UserPojo {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    private String password;
    private boolean isAdmin;
}
