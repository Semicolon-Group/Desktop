package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.StatusPost;

public class StatusPostService extends Service implements Create<StatusPost>,Delete<StatusPost>,Read<StatusPost>,Update<StatusPost>{

    private static StatusPostService statusPostService;
    
    private StatusPostService(){
        super();
    }
    
    public static StatusPostService getInstance(){
        if(statusPostService == null){
            return statusPostService = new StatusPostService();
        }
        return statusPostService;
    }

    @Override
    public StatusPost create(StatusPost obj) throws SQLException {
	String req = "INSERT INTO `post`(`date`, `content`, `user_id`) VALUES (?,?,?)";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setTimestamp(1, obj.getDate());
	pst.setString(2, obj.getContent());
	pst.setInt(3,obj.getOwnerId());
	pst.executeUpdate();
	return obj;
    }

    @Override
    public void delete(StatusPost obj) throws SQLException {
	String req = "DELETE FROM `post` WHERE id = " + obj.getId();
	Statement st = CONNECTION.createStatement();
	st.executeUpdate(req);
    }

    @Override
    public StatusPost get(StatusPost obj) throws SQLException {
	String req = "SELECT * FROM `post` WHERE id = " + obj.getId();
	Statement st = CONNECTION.createStatement();
	ResultSet rs = st.executeQuery(req);
	if(rs.next()){
            return new StatusPost(rs.getString("content"), rs.getInt("id"), rs.getInt("user_id"), rs.getTimestamp("date"));
        }
        return null;
    }

    @Override
    public List<StatusPost> getAll(StatusPost obj) throws SQLException {
	String req = "SELECT * FROM `post` WHERE user_id = " + obj.getOwnerId();
	Statement st = CONNECTION.createStatement();
	ResultSet rs = st.executeQuery(req);
	List<StatusPost> list = new ArrayList<>();
	while(rs.next()){
	    list.add(new StatusPost(rs.getString("content"), rs.getInt("id"), rs.getInt("user_id"), rs.getTimestamp("date")));
	}
	return list;
    }

    @Override
    public void update(StatusPost obj) throws SQLException {
	String req = "UPDATE `post` SET `date`=?,`content`=?,`user_id`=? WHERE id = ?";
	PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setTimestamp(1, obj.getDate());
	pst.setString(2, obj.getContent());
	pst.setInt(3,obj.getOwnerId());
	pst.setInt(4, obj.getId());
	pst.executeUpdate();
    }

    
}
