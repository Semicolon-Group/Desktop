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
    private Choice selectedChoice;
    private HashSet<Choice> acceptedChoices;
    
    public Answer() {
        acceptedChoices = new HashSet<>();
    }
    
    public Answer(int id){
        this.id = id;
        acceptedChoices = new HashSet<>();
    }

    public Answer(int questionId, Timestamp date, Importance importance, int memberId, Choice selectedChoice) {
	this.questionId = questionId;
	this.date = date;
	this.importance = importance;
	this.memberId = memberId;
        this.selectedChoice = selectedChoice;
        acceptedChoices = new HashSet<>();
    }

    public Answer(int id, int questionId, Timestamp date, Importance importance, int memberId, Choice selectedChoice) {
	this.id = id;
	this.questionId = questionId;
	this.date = date;
	this.importance = importance;
	this.memberId = memberId;
        this.selectedChoice = selectedChoice;
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

    public Choice getSelectedChoice() {
        return selectedChoice;
    }
    
    public void setSelectedChoice(Choice selectedChoice){
        this.selectedChoice = selectedChoice;
    }

    public HashSet<Choice> getAcceptedChoices() {
        return acceptedChoices;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Answer other = (Answer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id + ", questionId=" + questionId + ", date=" + date + ", importance=" + importance + ", memberId=" + memberId + ", selectedChoices=" + selectedChoice + ", acceptedChoices=" + acceptedChoices + "}\n";
    }
    
}
