package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import models.AnswerPost;
import models.Enumerations;
import models.Enumerations.PostType;

public class AnswerPostService extends Service implements Create<AnswerPost>,Delete<AnswerPost>,Read<AnswerPost>,Update<AnswerPost>{

    private static AnswerPostService answerPostService;
    
    private AnswerPostService(){
        super();
    }
    
    public static AnswerPostService getInstance(){
        if(answerPostService == null){
            return answerPostService = new AnswerPostService();
        }
        return answerPostService;
    }

    @Override
    public AnswerPost create(AnswerPost obj) throws SQLException {
	/*
	* Insertion into "post" table.
	*/
	String req = "INSERT INTO `post`(`date`, `content`, `type`, `user_id`) VALUES (?,?,?,?)";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setTimestamp(1, obj.getDate());
	pst.setString(2, "");
	pst.setInt(3, PostType.ANSWER.ordinal());
	pst.setInt(4, obj.getId());
	pst.executeUpdate();
	/*
	* Insertion into "answerpost" table.
	*/
	req = "INSERT INTO `post_answer`(`post_id`, `answer_id`) VALUES (?,?)";
	pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getId());
	pst.setInt(2, obj.getAnswerId());
	pst.executeUpdate();
	/*
	* Retrieval of new AnswerPost id.
	*/
	req = "SELECT MAX(id) FROM `post_answer`";
	pst = CONNECTION.prepareStatement(req);
	ResultSet rs = pst.executeQuery();
	rs.next();
	obj.setId(rs.getInt("id"));
	return obj;
    }

    @Override
    public void delete(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnswerPost get(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerPost> getAll(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnswerPost update(AnswerPost obj) throws SQLException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
