package models;

import java.sql.Timestamp;

public class PicturePost extends Post{
    private String url;
    
    public PicturePost(){
        super();
    }
    
    public PicturePost(int id){
        super(id);
    }
    
    public PicturePost(int ownerId, Timestamp date) {
        super(ownerId, date);
    }

    public PicturePost(int id, int ownerId, Timestamp date) {
        super(id, ownerId, date);
    }

    public PicturePost(String url, int id, int ownerId, Timestamp date) {
	super(id, ownerId, date);
	this.url = url;
    }
    
    public String getUrl(){
        return this.url;
    }
}
