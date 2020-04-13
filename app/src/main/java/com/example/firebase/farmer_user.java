package com.example.firebase;

public class farmer_user {
    public String Name,Email,phone_no,lan,state,dis,taluka;

    public farmer_user()
    {

    }

    public farmer_user(String name, String email, String phone, String lan, String state, String dis, String taluka) {
        this.Name = name;
        this.Email = email;
        this.phone_no = phone;
        this.lan = lan;
        this.state = state;
        this.dis = dis;
        this.taluka = taluka;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return phone_no;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPhone(String phone) {
        this.phone_no = phone;
    }
}
