package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PicturePost extends Post{
    private List<String> picturesUrls;
    
    public PicturePost(){
        super();
        picturesUrls = new ArrayList<>();
    }
    
    public PicturePost(int id){
        super(id);
        picturesUrls = new ArrayList<>();
    }
    
    public PicturePost(int ownerId, Timestamp date) {
        super(ownerId, date);
        this.picturesUrls = new ArrayList<>();
    }

    public PicturePost(int id, int ownerId, Timestamp date) {
        super(id, ownerId, date);
        this.picturesUrls = new ArrayList<>();
    }
    
    public List<String> getPictruesIds(){
        return this.picturesUrls;
    }
}
