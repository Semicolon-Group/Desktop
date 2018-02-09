/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import models.Enumerations.Importance;

/**
 *
 * @author Elyes
 */
public class Answer {
    private int id;
    private int questionId;
    private Timestamp date;
    private Importance importance;
    private int memberId;
    private HashSet<Choice> selectedChoices;
    private HashSet<Choice> acceptedChoices;
    
    public Answer() {
        selectedChoices = new HashSet<>();
        acceptedChoices = new HashSet<>();
    }
    
    public Answer(int id){
        this.id = id;
        selectedChoices = new HashSet<>();
        acceptedChoices = new HashSet<>();
    }

    public Answer(int questionId, Timestamp date, Importance importance, int memberId) {
	this.questionId = questionId;
	this.date = date;
	this.importance = importance;
	this.memberId = memberId;
        selectedChoices = new HashSet<>();
        acceptedChoices = new HashSet<>();
    }

    public Answer(int id, int questionId, Timestamp date, Importance importance, int memberId) {
	this.id = id;
	this.questionId = questionId;
	this.date = date;
	this.importance = importance;
	this.memberId = memberId;
        selectedChoices = new HashSet<>();
        acceptedChoices = new HashSet<>();
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getQuestionId() {
	return questionId;
    }

    public void setQuestionId(int questionId) {
	this.questionId = questionId;
    }

    public Timestamp getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
    }

    public Importance getImportance() {
	return importance;
    }

    public void setImportance(Importance importance) {
	this.importance = importance;
    }

    public int getMemberId() {
	return memberId;
    }

    public void setMemberId(int memberId) {
	this.memberId = memberId;
    }

    public HashSet<Choice> getSelectedChoices() {
        return selectedChoices;
    }

    public HashSet<Choice> getAcceptedChoices() {
        return acceptedChoices;
    }
}
