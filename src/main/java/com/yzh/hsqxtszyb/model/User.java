package com.yzh.hsqxtszyb.model;

public class User {
    private String ID;
    public User(String ID, String password) {
        this.ID = ID;
        Password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCnty() {
        return Cnty;
    }

    public void setCnty(String cnty) {
        Cnty = cnty;
    }

    public int getUser_levl() {
        return User_levl;
    }

    public void setUser_levl(int user_levl) {
        User_levl = user_levl;
    }

    public int getUser_Type() {
        return User_Type;
    }

    public void setUser_Type(int user_Type) {
        User_Type = user_Type;
    }

    private String Name;
    private String Password;
    private String Province;
    private String City;
    private String Cnty;
    private int User_levl;
    private int User_Type;


}
