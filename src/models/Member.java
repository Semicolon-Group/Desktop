/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import models.Enumerations.BodyType;
import models.Enumerations.Importance;
import models.Enumerations.MaritalStatus;
import models.Enumerations.Proximity;
import models.Enumerations.Religion;

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
    private Address address;
    private Importance religionImportance;
    private boolean smoker;
    private boolean drinker;
    private int minAge;
    private int maxAge;
    private Proximity proximity;
    private HashSet<MaritalStatus> preferedStatus;
    private List<String> photos;
    private Date lastLogin;
    private HashSet<Like> likes;
    private HashSet<Block> blocks;
    private TreeSet<Post> posts;
    private HashSet<Answer> answers;
    private TreeSet<Signal> signals;
    private TreeSet<Feedback> feedbacks;
    private TreeSet<Notification> notifications;

    public Member() {
    }

    public Member(Date birthDate, boolean gender, float height, BodyType bodyType, int childrenNumber, Religion religion, Address address, Importance religionImportance, boolean smoker, boolean drinker, int minAge, int maxAge, Proximity proximity, HashSet<MaritalStatus> preferedStatus, List<String> photos, Date lastLogin, HashSet<Like> likes, HashSet<Block> blocks, TreeSet<Post> posts, HashSet<Answer> answers, TreeSet<Signal> signals, TreeSet<Feedback> feedbacks, TreeSet<Notification> notifications, int id, String pseudo, String nom, String prenom, String email, String password) {
	super(id, pseudo, nom, prenom, email, password);
	this.birthDate = birthDate;
	this.gender = gender;
	this.height = height;
	this.bodyType = bodyType;
	this.childrenNumber = childrenNumber;
	this.religion = religion;
	this.address = address;
	this.religionImportance = religionImportance;
	this.smoker = smoker;
	this.drinker = drinker;
	this.minAge = minAge;
	this.maxAge = maxAge;
	this.proximity = proximity;
	this.preferedStatus = preferedStatus;
	this.photos = photos;
	this.lastLogin = lastLogin;
	this.likes = likes;
	this.blocks = blocks;
	this.posts = posts;
	this.answers = answers;
	this.signals = signals;
	this.feedbacks = feedbacks;
	this.notifications = notifications;
    }

    public Member(Date birthDate, boolean gender, float height, BodyType bodyType, int childrenNumber, Religion religion, Address address, Importance religionImportance, boolean smoker, boolean drinker, int minAge, int maxAge, Proximity proximity, HashSet<MaritalStatus> preferedStatus, List<String> photos, Date lastLogin, HashSet<Like> likes, HashSet<Block> blocks, TreeSet<Post> posts, HashSet<Answer> answers, TreeSet<Signal> signals, TreeSet<Feedback> feedbacks, TreeSet<Notification> notifications, String pseudo, String nom, String prenom, String email, String password) {
	super(pseudo, nom, prenom, email, password);
	this.birthDate = birthDate;
	this.gender = gender;
	this.height = height;
	this.bodyType = bodyType;
	this.childrenNumber = childrenNumber;
	this.religion = religion;
	this.address = address;
	this.religionImportance = religionImportance;
	this.smoker = smoker;
	this.drinker = drinker;
	this.minAge = minAge;
	this.maxAge = maxAge;
	this.proximity = proximity;
	this.preferedStatus = preferedStatus;
	this.photos = photos;
	this.lastLogin = lastLogin;
	this.likes = likes;
	this.blocks = blocks;
	this.posts = posts;
	this.answers = answers;
	this.signals = signals;
	this.feedbacks = feedbacks;
	this.notifications = notifications;
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

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
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

    public HashSet<MaritalStatus> getPreferedStatus() {
	return preferedStatus;
    }

    public void setPreferedStatus(HashSet<MaritalStatus> preferedStatus) {
	this.preferedStatus = preferedStatus;
    }

    public List<String> getPhotos() {
	return photos;
    }

    public void setPhotos(List<String> photos) {
	this.photos = photos;
    }

    public Date getLastLogin() {
	return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
	this.lastLogin = lastLogin;
    }

    public HashSet<Like> getLikes() {
	return likes;
    }

    public void setLiked(HashSet<Like> likes) {
	this.likes = likes;
    }

    public HashSet<Block> getBlocks() {
	return blocks;
    }

    public void setBlocked(HashSet<Block> blocks) {
	this.blocks = blocks;
    }

    public TreeSet<Post> getPosts() {
	return posts;
    }

    public void setPosts(TreeSet<Post> posts) {
	this.posts = posts;
    }

    public HashSet<Answer> getAnswers() {
	return answers;
    }

    public void setAnswers(HashSet<Answer> answers) {
	this.answers = answers;
    }

    public TreeSet<Signal> getSignals() {
	return signals;
    }

    public void setSignals(TreeSet<Signal> signals) {
	this.signals = signals;
    }

    public TreeSet<Feedback> getFeedbacks() {
	return feedbacks;
    }

    public void setFeedbacks(TreeSet<Feedback> feedbacks) {
	this.feedbacks = feedbacks;
    }

    public TreeSet<Notification> getNotifications() {
	return notifications;
    }

    public void setNotifications(TreeSet<Notification> notifications) {
	this.notifications = notifications;
    }
    
}
