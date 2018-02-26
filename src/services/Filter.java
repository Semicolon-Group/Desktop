/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import models.Enumerations.BodyType;
import models.Enumerations.LastLogin;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import models.Enumerations.Religion;
import models.Member;

/**
 *
 * @author Elyes
 */
public class Filter {

    private int ageMin = 18;
    private int ageMax = 90;
    private float heightMin = 100;
    private float heightMax = 300;
    private List<BodyType> bodyType;
    private List<Religion> religion;
    private List<MaritalStatus> maritalStatus;
    private double distance = -1;
    private LastLogin lastLogin = LastLogin.ANNEE;
    private int smokes = -1;
    private int drinks = -1;

    public Filter() {
        bodyType = new ArrayList();
        religion = new ArrayList();
        maritalStatus = new ArrayList();
    }
    
    

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public float getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(float heightMin) {
        this.heightMin = heightMin;
    }

    public float getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(float heightMax) {
        this.heightMax = heightMax;
    }

    public List<BodyType> getBodyType() {
        return bodyType;
    }

    public void setBodyType(List<BodyType> bodyType) {
        this.bodyType = bodyType;
    }

    public List<Religion> getReligion() {
        return religion;
    }

    public void setReligion(List<Religion> religion) {
        this.religion = religion;
    }

    public List<MaritalStatus> getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(List<MaritalStatus> maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public LastLogin getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LastLogin lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getSmokes() {
        return smokes;
    }

    public void setSmokes(int smokes) {
        this.smokes = smokes;
    }

    public int getDrinks() {
        return drinks;
    }

    public void setDrinks(int drinks) {
        this.drinks = drinks;
    }

}
