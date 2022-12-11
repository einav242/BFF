package com.example.bff;

public class Customer {
    private String phone;
    private String email;
    private String id;
    private String name;
    private String username;
    private int flag;


    public Customer(String phone,String email,String id,String name,String username,int flag){
        this.email=email;
        this.flag=flag;
        this.id=id;
        this.name=name;
        this.username=username;
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
