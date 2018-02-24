package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Enumerations.ReactionType;
import models.Reaction;

public class ReactionService extends Service implements Create<Reaction>, Read<Reaction>, Delete<Reaction>, Update<Reaction> {

    private static ReactionService reactionService;
    
    private ReactionService(){
        super();
    }
    
    public static ReactionService getInstance(){
        if(reactionService == null){
            return reactionService = new ReactionService();
        }
        return reactionService;
    }
    
    @Override
    public Reaction create(Reaction obj) throws SQLException {
        String req = "INSERT INTO `post_reaction`(`user_id`, `post_id`, `photo_id`, `answer_id`, `reaction`) VALUES (?,?,?,?,?)";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getMemberId());
	pst.setInt(2, obj.getPostId());
        pst.setInt(3, obj.getPhotoId());
        pst.setInt(4, obj.getAnswerId());
	pst.setInt(5, obj.getReactionType().ordinal());
	pst.executeUpdate();
	return obj;
    }

    @Override
    public Reaction get(Reaction obj) throws SQLException {
        String req = "SELECT * FROM `post_reaction` WHERE user_id = ? and ";
        if (obj.getPostId() != 0)
            req+= "post_id = " + obj.getPostId();
        else if (obj.getPhotoId() != 0)
            req+= "photo_id = " + obj.getPhotoId();
        else if (obj.getAnswerId() != 0)
            req+= "answer_id = " + obj.getAnswerId();
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getMemberId());
	ResultSet rs = pst.executeQuery();
	if(rs.next()){
            return new Reaction(rs.getInt("user_id"), rs.getInt("post_id"), 
                    rs.getInt("photo_id"), rs.getInt("answer_id"), ReactionType.values()[rs.getInt("reaction")]);
        }
	return null;
    }

    @Override
    public List<Reaction> getAll(Reaction obj) throws SQLException {
        String req = "SELECT * FROM `post_reaction` WHERE ";
        if (obj.getPostId() != 0)
            req+= "post_id = " + obj.getPostId();
        else if (obj.getPhotoId() != 0)
            req+= "photo_id = " + obj.getPhotoId();
        else if (obj.getAnswerId() != 0)
            req+= "answer_id = " + obj.getAnswerId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	List<Reaction> list = new ArrayList();
	while(rs.next()){
	    list.add(new Reaction(rs.getInt("user_id"), rs.getInt("post_id"), 
                    rs.getInt("photo_id"), rs.getInt("answer_id"), ReactionType.values()[rs.getInt("reaction")]));
	}
	return list;
    }

    @Override
    public void delete(Reaction obj) throws SQLException {
	/*
	* Either delete all reactions related to a post, or delete one reaction of a user on a post.
	*/
	if (obj.getPostId() <= 0 && obj.getPhotoId() <= 0 && obj.getAnswerId() <= 0){
	    throw new SQLException("Must specifiy a valid reaction reference");
	}
        
        String req = "DELETE FROM `post_reaction` WHERE ";
        if (obj.getPostId() != 0)
            req+= "post_id = " + obj.getPostId();
        else if (obj.getPhotoId() != 0)
            req+= "photo_id = " + obj.getPhotoId();
        else if (obj.getAnswerId() != 0)
            req+= "answer_id = " + obj.getAnswerId();
        
	if (obj.getMemberId() > 0)
	    req += " and user_id = " + obj.getMemberId();
	CONNECTION.createStatement().executeUpdate(req);
    }

    @Override
    public void update(Reaction obj) throws SQLException {
        String req = "UPDATE `post_reaction` SET `reaction` = " + obj.getReactionType().ordinal() +
            " WHERE `user_id` = " + obj.getMemberId() + " and ";
        if (obj.getPostId() != 0)
            req+= "`post_id` = " + obj.getPostId();
        else if (obj.getPhotoId() != 0)
            req+= "`photo_id` = " + obj.getPhotoId();
        else if (obj.getAnswerId() != 0)
            req+= "`answer_id` = " + obj.getAnswerId();
        CONNECTION.createStatement().executeUpdate(req);
    }
}
