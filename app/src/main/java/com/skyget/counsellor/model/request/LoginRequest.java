package com.skyget.counsellor.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    @SerializedName("password")
    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @SerializedName("phone")
    public String phone;
}
