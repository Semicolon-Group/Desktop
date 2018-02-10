package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AnswerPost extends Post{
    private Answer answer;

    public AnswerPost(int id) {
        super(id);
    }

    public AnswerPost(int ownerId, Timestamp date) {
        super(ownerId, date);
    }

    public AnswerPost(Answer answer, int id, int ownerId, Timestamp date) {
	super(id, ownerId, date);
	this.answer = answer;
    }
    

    public AnswerPost(int id, int ownerId, Timestamp date) {
        super(id, ownerId, date);
    }

    public Answer getAnswer() {
	return answer;
    }

    public void setAnswer(Answer answer) {
	this.answer = answer;
    }
    
    
}
