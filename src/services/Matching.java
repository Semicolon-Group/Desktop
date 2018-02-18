/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Answer;
import models.Choice;
import models.Enumerations.Importance;
import models.MatchCard;
import models.Member;
import models.Photo;

/**
 *
 * @author Elyes
 */
public class Matching {
    
    private int getMatch(Answer A, Answer B){
        if(A.getImportance() == Importance.INDIFFERENT)
            return 1;
        for(Choice c : B.getSelectedChoices()){
            if(A.getAcceptedChoices().contains(c))
                return 1;
        }
        return 0;
    }
    
    private float getEnemy(Answer A, Answer B){
        if(A.getImportance() == Importance.INDIFFERENT)
            return 0;
        
        float imp = 1;
        if(A.getImportance() == Importance.UN_PEU_IMPORTANT)
            imp = 0.5f;
        for(Choice c : B.getSelectedChoices()){
            if(!A.getAcceptedChoices().contains(c))
                return imp;
        }
        
        return 0;
    }
    
    private Map<Answer,Answer> coupleAnswer(List<Answer> A, List<Answer> B){
        Map<Answer,Answer> map = new HashMap();
        A.stream().forEach(a -> {
            for(Answer b : B){
                if (b.getQuestionId() == a.getQuestionId())
                    map.put(a, b);
            }
        });
        return map;
    }
    
    public int getMatchTotal(List<Answer> A, List<Answer> B){
        int sum = 0;
        int count = 0;
        for(Map.Entry<Answer,Answer> e : coupleAnswer(A,B).entrySet()){
            sum += getMatch(e.getKey(),e.getValue());
            count++;
        }
        return count == 0 ? 0 : (sum / count) * 100;
    }
    
    public int getEnemyTotal(List<Answer> A, List<Answer> B){
        float sum = 0;
        int count = 0;
        for(Map.Entry<Answer,Answer> e : coupleAnswer(A,B).entrySet()){
            sum += getEnemy(e.getKey(),e.getValue());
            count++;
        }
        return (int) (count == 0 ? 0 : (sum / count) * 100 + 1); //+1 coz if we get just 0.5, we should show at least 1.
    }
    
    public List<MatchCard> getMatches(int MemberId) throws SQLException{
        List<Member> list = MemberService.getInstance().getAll(null);
        list.removeIf(m -> m.getId() == MemberId);
        List<MatchCard> cards = new ArrayList();
        for(Member m : list){
            MatchCard card = new MatchCard();
            card.setAge(m.getAge());
            card.setCity(m.getAddress().getCity());
            card.setLastLogin(m.getLastLogin());
            card.setPseudo(m.getPseudo());
            card.setPhotoUrl(PhotoService.getInstance().get(new Photo(m.getId())).getUrl());
            
            List<Answer> A = AnswerService.getInstance().getAll(new Answer(0, null, null, MemberId));
            List<Answer> B = AnswerService.getInstance().getAll(new Answer(0, null, null, m.getId()));
            card.setMatch(getMatchTotal(A,B));
            card.setEnemy(getEnemyTotal(A,B));
            
            cards.add(card);
        }
        cards.sort((a,b) -> a.getMatch() - b.getMatch());
        return cards;
    }
}
