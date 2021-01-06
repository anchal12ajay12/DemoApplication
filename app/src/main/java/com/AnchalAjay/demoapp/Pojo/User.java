package com.AnchalAjay.demoapp.Pojo;


public class User {
    String first_name;
    String last_name;
    String dob;
    String gender;
    String country, state, home_town, phone_number;
    Long timeStamp;
    String profile_image;



    public User(){}

    public User(String first_name, String last_name, String dob, String gender,
                String country, String state, String home_town,
                String phone_number, Long timeStamp) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.dob = dob;
        if(gender.trim().equalsIgnoreCase("M")){
            this.gender = "Male";
        }
        if(gender.trim().equalsIgnoreCase("F")){
            this.gender = "Male";
        }
        this.country = country;
        this.state = state;
        this.home_town = home_town;
        this.phone_number = phone_number;
        this.timeStamp = timeStamp;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHome_town() {
        return home_town;
    }

    public void setHome_town(String home_town) {
        this.home_town = home_town;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
