package com.ielts.mcpp.ielts.model;

import java.io.Serializable;

/**
 * Created by Jack on 4/17/2015.
 */
public class RegistrationForm implements Serializable{

    private String firstName;
    private String lastName;
    private String password;
    private String nationality;
    private String dateOfBirth;
    private String email;
    private String promoCode;
    private String proffesion;
    private String workOrStudy;
    public RegistrationForm() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getProffesion() {
        return proffesion;
    }

    public void setProffesion(String proffesion) {
        this.proffesion = proffesion;
    }

    public String getWorkOrStudy() {
        return workOrStudy;
    }

    public void setWorkOrStudy(String workOrStudy) {
        this.workOrStudy = workOrStudy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
