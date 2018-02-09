package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AnswerPost extends Post{
    private int answerId;

    public AnswerPost(int id) {
        super(id);
    }

    public AnswerPost(int ownerId, Timestamp date) {
        super(ownerId, date);
    }

    public AnswerPost(int answerId, int id, int ownerId, Timestamp date) {
	super(id, ownerId, date);
	this.answerId = answerId;
    }
    

    public AnswerPost(int id, int ownerId, Timestamp date) {
        super(id, ownerId, date);
    }

    public int getAnswerId() {
	return answerId;
    }

    public void setAnswerId(int answerId) {
	this.answerId = answerId;
    }
    
    
}
