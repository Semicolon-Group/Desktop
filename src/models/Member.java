/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import models.Enumerations.BodyType;
import models.Enumerations.Importance;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import models.Enumerations.Religion;
import models.Enumerations.RelationType;

/**
 *
 * @author Elyes
 */
public class Member extends User{
    private Date birthDate;
    private boolean gender;
    private float height;
    private BodyType bodyType;
    private int childrenNumber;
    private Religion religion;
    private Importance religionImportance;
    private boolean smoker;
    private boolean drinker;
    private int minAge;
    private int maxAge;
    private Proximity proximity;
    private Date lastLogin;
    private short locked;
    private Address address;
    private List<String> photos;
    private List<RelationType> preferedRelations;
    private List<MaritalStatus> preferedStatuses;

    public Member() {
        photos = new ArrayList<>();
        preferedRelations = new ArrayList<>();
        preferedStatuses = new ArrayList<>();
    }
    
    public Member(int id){
        super(id);
        photos = new ArrayList<>();
        preferedRelations = new ArrayList<>();
        preferedStatuses = new ArrayList<>();
    }

    public Member(Date birthDate, boolean gender, float height, BodyType bodyType, int childrenNumber, Religion religion, Importance religionImportance, boolean smoker, boolean drinker, int minAge, int maxAge, Proximity proximity, Date lastLogin, short locked, Address address, String pseudo, String nom, String prenom, String email, String password) {
        super(pseudo, nom, prenom, email, password);
        photos = new ArrayList<>();
        this.birthDate = birthDate;
        this.gender = gender;
        this.height = height;
        this.bodyType = bodyType;
        this.childrenNumber = childrenNumber;
        this.religion = religion;
        this.religionImportance = religionImportance;
        this.smoker = smoker;
        this.drinker = drinker;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.proximity = proximity;
        this.lastLogin = lastLogin;
        this.locked = locked;
        this.address = address;
        preferedRelations = new ArrayList<>();
        preferedStatuses = new ArrayList<>();
    }

    public Member(Date birthDate, boolean gender, float height, BodyType bodyType, int childrenNumber, Religion religion, Importance religionImportance, boolean smoker, boolean drinker, int minAge, int maxAge, Proximity proximity, Date lastLogin, short locked, Address address, int id, String pseudo, String nom, String prenom, String email, String password) {
        super(id, pseudo, nom, prenom, email, password);
        this.birthDate = birthDate;
        this.gender = gender;
        this.height = height;
        this.bodyType = bodyType;
        this.childrenNumber = childrenNumber;
        this.religion = religion;
        this.religionImportance = religionImportance;
        this.smoker = smoker;
        this.drinker = drinker;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.proximity = proximity;
        this.lastLogin = lastLogin;
        this.locked = locked;
        this.address = address;
        preferedRelations = new ArrayList<>();
        preferedStatuses = new ArrayList<>();
    }

    public Date getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    public boolean isGender() {
	return gender;
    }

    public void setGender(boolean gender) {
	this.gender = gender;
    }

    public float getHeight() {
	return height;
    }

    public void setHeight(float height) {
	this.height = height;
    }

    public BodyType getBodyType() {
	return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
	this.bodyType = bodyType;
    }

    public int getChildrenNumber() {
	return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
	this.childrenNumber = childrenNumber;
    }

    public Religion getReligion() {
	return religion;
    }

    public void setReligion(Religion religion) {
	this.religion = religion;
    }

    public Importance getReligionImportance() {
	return religionImportance;
    }

    public void setReligionImportance(Importance religionImportance) {
	this.religionImportance = religionImportance;
    }

    public boolean isSmoker() {
	return smoker;
    }

    public void setSmoker(boolean smoker) {
	this.smoker = smoker;
    }

    public boolean isDrinker() {
	return drinker;
    }

    public void setDrinker(boolean drinker) {
	this.drinker = drinker;
    }

    public int getMinAge() {
	return minAge;
    }

    public void setMinAge(int minAge) {
	this.minAge = minAge;
    }

    public int getMaxAge() {
	return maxAge;
    }

    public void setMaxAge(int maxAge) {
	this.maxAge = maxAge;
    }

    public Proximity getProximity() {
	return proximity;
    }

    public void setProximity(Proximity proximity) {
	this.proximity = proximity;
    }

    public Date getLastLogin() {
	return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
	this.lastLogin = lastLogin;
    }

    public short getLocked() {
        return locked;
    }

    public void setLocked(short locked) {
        this.locked = locked;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public List<RelationType> getPreferedRelations() {
        return preferedRelations;
    }

    public List<MaritalStatus> getPreferedStatuses() {
        return preferedStatuses;
    }
}
