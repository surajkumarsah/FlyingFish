package com.example.flyingfish.Model;

public class UserView {

    public String name;
    public String mobile;
    public String email;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String score;


    UserView()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UserView(String name, String mobile, String email, String score) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.score = score;
    }

}
