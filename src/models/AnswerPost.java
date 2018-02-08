package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AnswerPost extends Post{
    private List<Integer> answersIds;

    public AnswerPost(int id) {
        super(id);
        this.answersIds = new ArrayList<>();
    }

    public AnswerPost(int ownerId, Date date) {
        super(ownerId, date);
        this.answersIds = new ArrayList<>();
    }

    public AnswerPost(int id, Member owner, Date date) {
        super(id, owner, date);
        this.answersIds = new ArrayList<>();
    }
    
    public List<Integer> getAnswersIds(){
        return this.answersIds;
    }
}
