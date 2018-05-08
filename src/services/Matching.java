/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controller.MySoulMate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import models.Answer;
import models.Choice;
import models.Enumerations;
import models.Enumerations.Importance;
import models.Enumerations.PhotoType;
import models.MatchCard;
import models.Member;
import models.Photo;

/**
 *
 * @author Elyes
 */
public class Matching {
    
    private static Matching instance;
    
    private Matching(){
        
    }
    
    public static Matching getInstance(){
        if (instance == null)
            instance = new Matching();
        return instance;
    }
    
    private int getMatch(Answer A, Answer B){
        if(A.getImportance() == Importance.INDIFFERENT)
            return 1;
        if(A.getAcceptedChoices().contains(B.getSelectedChoice()))
            return 1;
//        for(Choice c : B.getSelectedChoices()){
//            if(A.getAcceptedChoices().contains(c))
//                return 1;
//        }
        return 0;
    }
    
    private float getEnemy(Answer A, Answer B){
        if(A.getImportance() == Importance.INDIFFERENT)
            return 0;
        
        float imp = 1;
        if(A.getImportance() == Importance.SOMEWHAT_IMPORTANT)
            imp = 0.5f;
        if(!A.getAcceptedChoices().contains(B.getSelectedChoice()))
            return imp;
//        for(Choice c : B.getSelectedChoices()){
//            if(!A.getAcceptedChoices().contains(c))
//                return imp;
//        }
        
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
        float sum = 0;
        int count = 0;
        for(Map.Entry<Answer,Answer> e : coupleAnswer(A,B).entrySet()){
            sum += getMatch(e.getKey(),e.getValue());
            count++;
        }
        return (int) (count == 0 ? 0 : (sum / count) * 100);
    }
    
    public int getEnemyTotal(List<Answer> A, List<Answer> B){
        float sum = 0;
        int count = 0;
        for(Map.Entry<Answer,Answer> e : coupleAnswer(A,B).entrySet()){
            sum += getEnemy(e.getKey(),e.getValue());
            count++;
        }
        return ((int) (count == 0 ? 0 : Math.ceil((sum / count) * 100))); //we should show at least 1.
    }
    
    public List<MatchCard> getMatches(Member M, Filter F) throws SQLException{
        Map<Member,Map.Entry<Double,Integer>> map = MemberService.getInstance().getFiltered(F);
        Set<Member> list = map.keySet();
        List<MatchCard> cards = new ArrayList();
        for(Member m : list){
            MatchCard card = new MatchCard();
            card.setMemberId(m.getId());
            card.setAge(m.getAge());
            card.setCity(m.getAddress().getCity());
            card.setLastLogin(map.get(m).getValue());
            card.setDistance(map.get(m).getKey());
            card.setPseudo(m.getPseudo());
            Photo ph = PhotoService.getInstance().getProfilePhoto(m.getId());
            if(ph == null){
                ph = new Photo();
		ph.setPhotoUri("/view/assets/icons/member.jpg");
                card.setPhotoUrl(ph.getPhotoUri());
            }else{
                card.setPhotoUrl(ph.getPhotoUri());
            }
            
            List<Answer> A = AnswerService.getInstance().getAll(new Answer(0, null, null, M.getId(), null));
            List<Answer> B = AnswerService.getInstance().getAll(new Answer(0, null, null, m.getId(), null));
            card.setMatch(getMatchTotal(A,B));
            card.setEnemy(getEnemyTotal(A,B));
            
            cards.add(card);
        }
        cards.sort((a,b) -> b.getMatch() - a.getMatch()); //adding in flowPane makes first element in list last in GUI.
        return cards;
    }
}
