package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Enumerations.ReactionType;
import models.Reaction;

public class ReactionService extends Service implements Create<Reaction>, Read<Reaction>, Delete<Reaction> {

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
        String req = "INSERT INTO `post_reaction`(`user_id`, `post_id`, `reaction`) VALUES (?,?,?)";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getMemberId());
	pst.setInt(2, obj.getPostId());
	pst.setInt(3, obj.getReactionType().ordinal());
	pst.executeUpdate();
	return obj;
    }

    @Override
    public Reaction get(Reaction obj) throws SQLException {
        String req = "SELECT * FROM `post_reaction` WHERE user_id = ? and post_id = ?";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getMemberId());
	pst.setInt(2, obj.getPostId());
	ResultSet rs = pst.executeQuery();
	rs.next();
	return new Reaction(rs.getInt("user_id"), rs.getInt("post_id"), ReactionType.values()[rs.getInt("reaction")]);
    }

    @Override
    public List<Reaction> getAll(Reaction obj) throws SQLException {
        String req = "SELECT * FROM `post_reaction` WHERE post_id = " + obj.getPostId();
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	List<Reaction> list = new ArrayList();
	while(rs.next()){
	    list.add(new Reaction(rs.getInt("user_id"), rs.getInt("post_id"), ReactionType.values()[rs.getInt("reaction")]));
	}
	return list;
    }

    @Override
    public void delete(Reaction obj) throws SQLException {
	/*
	* Either delete all reactions related to a post, or delete one reaction of a user on a post.
	*/
	if (obj.getPostId() <= 0){
	    throw new SQLException("Must specifiy at least post_id to complete delete operation");
	}
        String req = "DELETE FROM `post_reaction` WHERE post_id = " + obj.getPostId();
	if (obj.getMemberId() > 0)
	    req += " and user_id = " + obj.getMemberId();
	CONNECTION.createStatement().executeUpdate(req);
    }
}
