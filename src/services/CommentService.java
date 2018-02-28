/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import models.Comment;

/**
 *
 * @author Elyes
 */
public class CommentService extends Service implements Create<Comment>, Read<Comment>, Update<Comment>, Delete<Comment>{
    private static CommentService instance;
    
    private CommentService(){
        super();
    }
    
    public static CommentService getInstance(){
        if(instance == null){
            return instance = new CommentService();
        }
        return instance;
    }

    @Override
    public Comment create(Comment obj) throws SQLException {
        String req = "INSERT INTO `comment`(`sender_id`, `receiver_id`, `post_id`, `photo_id`, `content`, `date`) VALUES (?,?,?,?,?,?)";
        PreparedStatement pst = CONNECTION.prepareStatement(req);
	pst.setInt(1, obj.getSenderId());
        pst.setInt(2, obj.getReceiverId());
        pst.setInt(3, obj.getPostId());
        pst.setInt(4, obj.getPhotoId());
	pst.setString(5, obj.getContent());
        pst.setTimestamp(6, obj.getDate());
	pst.executeUpdate();
        
        req = "SELECT MAX(id) max from comment";
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
	rs.next();
	obj.setId(rs.getInt("max"));
        
	return obj;
    }

    @Override
    public Comment get(Comment obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getAll(Comment obj) throws SQLException {
        String req = "SELECT * FROM `comment` WHERE ";
        if(obj.getPostId() != 0)
            req+= "`post_id` = " + obj.getPostId();
        else if(obj.getPhotoId() != 0)
            req+= "`photo_id` = " + obj.getPhotoId();
        if(obj.getSenderId()!= 0 && obj.getReceiverId() != 0)
            req+= " and (`sender_id` = " + obj.getSenderId() + " and `receiver_id` = " + obj.getReceiverId()
                    + " or `sender_id` = " + obj.getReceiverId() + " and `receiver_id` = " + obj.getSenderId() + ")";
	ResultSet rs = CONNECTION.createStatement().executeQuery(req);
        List<Comment> list = new ArrayList();
	while(rs.next()){
            list.add(new Comment(rs.getInt("id"), rs.getInt("sender_id"), rs.getInt("receiver_id"), rs.getInt("post_id"),
                    rs.getInt("photo_id"), rs.getString("content"), rs.getTimestamp("date")));
        }
        list.sort((a,b) -> a.getDate().compareTo(b.getDate()));
	return list;
    }

    @Override
    public void update(Comment obj) throws SQLException {
        String req = "UPDATE `comment` SET `content` = '" + obj.getContent() +
            "' WHERE `id` = " + obj.getId();
        CONNECTION.createStatement().executeUpdate(req);
    }

    @Override
    public void delete(Comment obj) throws SQLException {
        String req = "DELETE FROM `comment` WHERE ";
        if(obj.getId() != 0){
            req+="id = " + obj.getId();
        }else if(obj.getPostId() != 0){
            req+="post_id = "+obj.getPostId();
        }else if(obj.getPostId() != 0){
            req+="photo_id = "+obj.getPhotoId();
        }else{
            return;
        }
        CONNECTION.createStatement().executeUpdate(req);
    }
}
