package com.maro.kontripeople.util;

import com.google.gson.annotations.SerializedName;

public class People {

    @SerializedName("name")
    String name;
    @SerializedName("gender")
    String gender;
    @SerializedName("occupation")
    String occupation;
    @SerializedName("age")
    int age;
    @SerializedName("employmentStatus")
    String employmentStatus;
    @SerializedName("citizen")
    boolean citizen;

    public People(String name, String gender, String occupation, int age, String employmentStatus, boolean citizen) {
        this.name = name;
        this.gender = gender;
        this.occupation = occupation;
        this.age = age;
        this.employmentStatus = employmentStatus;
        this.citizen = citizen;
    }

    public People() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public boolean isCitizen() {
        return citizen;
    }

    public void setCitizen(boolean citizen) {
        this.citizen = citizen;
    }
}
