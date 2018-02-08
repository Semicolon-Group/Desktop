/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.HashSet;
import models.Enumerations.Importance;

/**
 *
 * @author Elyes
 */
public class Answer {
    private int id;
    private int questionId;
    private Date date;
    private Importance importance;
    private int memberId;
    
    public Answer() {
    }
    
    public Answer(int id){
        this.id = id;
    }

    public Answer(int questionId, Date date, Importance importance, int memberId) {
	this.questionId = questionId;
	this.date = date;
	this.importance = importance;
	this.memberId = memberId;
    }

    public Answer(int id, int questionId, Date date, Importance importance, int memberId) {
	this.id = id;
	this.questionId = questionId;
	this.date = date;
	this.importance = importance;
	this.memberId = memberId;
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

    public void setQuestion(int questionId) {
	this.questionId = questionId;
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

    public int getMember() {
	return memberId;
    }

    public void setMember(int memberId) {
	this.memberId = memberId;
    }
    
}
