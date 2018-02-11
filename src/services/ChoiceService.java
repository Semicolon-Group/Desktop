package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Choice;

public class ChoiceService extends Service implements Create<Choice>, Read<Choice>, Delete<Choice>{

    private static ChoiceService choiceService;
    
    private ChoiceService(){
        super();
    }
    
    public static ChoiceService getInstance(){
        if(choiceService == null){
            return choiceService = new ChoiceService();
        }
        return choiceService;
    }
    
    @Override
    public Choice create(Choice obj) throws SQLException {
        String req = "INSERT INTO `choice`(`question_id`, `choice`) VALUES (?,?)";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getQuestionId());
	pst.setString(2, obj.getChoice());
	pst.executeUpdate();
	return obj;
    }

    @Override
    public Choice get(Choice obj) throws SQLException {
        String req = "SELECT * FROM `choice` WHERE id = " + obj.getId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	if(rs.next()){
            return new Choice(rs.getInt("id"), rs.getInt("question_id"), rs.getString("choice"));
        }
	return null;
    }

    @Override
    public List<Choice> getAll(Choice obj) throws SQLException {
        String req = "SELECT * FROM `choice` WHERE question_id = " + obj.getQuestionId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	List<Choice> list = new ArrayList();
	while(rs.next()){
	    list.add(new Choice(rs.getInt("id"), rs.getInt("question_id"), rs.getString("choice")));
	}
	return list;
    }

    @Override
    public void delete(Choice obj) throws SQLException {
        String req = "DELETE FROM `choice` WHERE id = " + obj.getId();
	CONNECTION.createStatement().executeUpdate(req);
    }
}
