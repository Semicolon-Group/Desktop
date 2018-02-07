/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashSet;

/**
 *
 * @author Elyes
 */
public class Question {
    private int id;
    private String question;
    private HashSet<String> choices;

    public Question() {
    }

    public Question(int id, String question, HashSet<String> choices) {
	this.id = id;
	this.question = question;
	this.choices = choices;
    }

    public Question(String question, HashSet<String> choices) {
	this.question = question;
	this.choices = choices;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getQuestion() {
	return question;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public HashSet<String> getChoices() {
	return choices;
    }

    public void setChoices(HashSet<String> choices) {
	this.choices = choices;
    }
}
