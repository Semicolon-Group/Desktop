/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.HashSet;
import models.Enumerations.Importance;

/**
 *
 * @author Elyes
 */
public class Answer {
    private int id;
    private Question question;
    private HashSet<String> choices;
    private Date date;
    private Importance importance;
    private HashSet<String> acceptedChoices;
    private Member member;

    public Answer(Question question, HashSet<String> choices, Date date, Importance importance, HashSet<String> acceptedChoices, Member member) {
	this.question = question;
	this.choices = choices;
	this.date = date;
	this.importance = importance;
	this.acceptedChoices = acceptedChoices;
	this.member = member;
    }

    public Answer(int id, Question question, HashSet<String> choices, Date date, Importance importance, HashSet<String> acceptedChoices, Member member) {
	this.id = id;
	this.question = question;
	this.choices = choices;
	this.date = date;
	this.importance = importance;
	this.acceptedChoices = acceptedChoices;
	this.member = member;
    }

    public Answer() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Question getQuestion() {
	return question;
    }

    public void setQuestion(Question question) {
	this.question = question;
    }

    public HashSet<String> getChoices() {
	return choices;
    }

    public void setChoices(HashSet<String> choices) {
	this.choices = choices;
    }

    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public Importance getImportance() {
	return importance;
    }

    public void setImportance(Importance importance) {
	this.importance = importance;
    }

    public HashSet<String> getAcceptedChoices() {
	return acceptedChoices;
    }

    public void setAcceptedChoices(HashSet<String> acceptedChoices) {
	this.acceptedChoices = acceptedChoices;
    }

    public Member getMember() {
	return member;
    }

    public void setMember(Member member) {
	this.member = member;
    }
    
    
}
