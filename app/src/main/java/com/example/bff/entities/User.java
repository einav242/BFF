package com.example.bff.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String name;
    private String email;
    private String username;
    private String flag;
    private String id;
    private String phone;

    public User() {
    }

    public User(String name, String email, String username, String flag, String id, String phone) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.flag = flag;
        this.id = id;
        this.phone = phone;
    }

    public User(String name, String country, double weight, String somthing, String somthin2) {
    }

    public User(String phone, String email,String username) {
        this.phone=phone;
        this.email=email;
        this.username=username;
    }

    public User(String phone, String email, String username, String id) {
        this.phone=phone;
        this.email=email;
        this.username=username;
        this.id=id;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        username = in.readString();
        flag = in.readString();
        id = in.readString();
        phone = in.readString();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(flag);
        parcel.writeString(id);
        parcel.writeString(phone);
    }
}