package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AnswerPost extends Post{
    private List<Integer> answersIds;

    public AnswerPost(int id) {
        super(id);
        this.answersIds = new ArrayList<>();
    }

    public AnswerPost(int ownerId, Timestamp date) {
        super(ownerId, date);
        this.answersIds = new ArrayList<>();
    }

    public AnswerPost(int id, Member owner, Timestamp date) {
        super(id, owner, date);
        this.answersIds = new ArrayList<>();
    }
    
    public List<Integer> getAnswersIds(){
        return this.answersIds;
    }
}
