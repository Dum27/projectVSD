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
    private String whatDoYouDo;
    private String scoreDoYouNeed;
    private String englishLevel;
    private String additionalScreenLanguage;
    private String howManyTries;
    private String lastScore;
    private boolean takenTestBefore;

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

    public String getWhatDoYouDo() {
        return whatDoYouDo;
    }

    public void setWhatDoYouDo(String whatDoYouDo) {
        this.whatDoYouDo = whatDoYouDo;
    }

    public String getScoreDoYouNeed() {
        return scoreDoYouNeed;
    }

    public void setScoreDoYouNeed(String scoreDoYouNeed) {
        this.scoreDoYouNeed = scoreDoYouNeed;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getAdditionalScreenLanguage() {
        return additionalScreenLanguage;
    }

    public void setAdditionalScreenLanguage(String additionalScreenLanguage) {
        this.additionalScreenLanguage = additionalScreenLanguage;
    }

    public String getHowManyTries() {
        return howManyTries;
    }

    public void setHowManyTries(String howManyTries) {
        this.howManyTries = howManyTries;
    }

    public String getLastScore() {
        return lastScore;
    }

    public void setLastScore(String lastScore) {
        this.lastScore = lastScore;
    }

    public boolean isTakenTestBefore() {
        return takenTestBefore;
    }

    public void setTakenTestBefore(boolean takenTestBefore) {
        this.takenTestBefore = takenTestBefore;
    }
}
