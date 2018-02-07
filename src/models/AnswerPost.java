/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import java.util.TreeSet;

/**
 *
 * @author Elyes
 */
public class AnswerPost extends Post{
    private TreeSet<Answer> answers;

    public AnswerPost(TreeSet<Answer> answers) {
	this.answers = answers;
    }

    public AnswerPost() {
    }

    public AnswerPost(TreeSet<Answer> answers, Member owner, Date date, Enumerations.Reaction reaction) {
	super(owner, date, reaction);
	this.answers = answers;
    }

    public AnswerPost(TreeSet<Answer> answers, int id, Member owner, Date date, Enumerations.Reaction reaction) {
	super(id, owner, date, reaction);
	this.answers = answers;
    }

    public TreeSet<Answer> getAnswers() {
	return answers;
    }

    public void setAnswers(TreeSet<Answer> answers) {
	this.answers = answers;
    }
    
}
