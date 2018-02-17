package services;

import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Like;
import models.Photo;
import models.PicturePost;

public class PicturePostService extends Service implements Read<PicturePost>{

    private static PicturePostService picturePostService;
    
    private PicturePostService(){
        super();
    }
    
    public static PicturePostService getInstance(){
        if(picturePostService == null){
            return picturePostService = new PicturePostService();
        }
        return picturePostService;
    }

    @Override
    public PicturePost get(PicturePost obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PicturePost> getAll(PicturePost obj) throws SQLException {
        List<PicturePost> list = new ArrayList();
        try {
            list.addAll(PhotoService.getInstance().getAll(new Photo(0,obj.getOwnerId(),null,obj.getDate(),false))
            .stream().map(p -> new PicturePost(p.getUrl(),0,p.getUserId(),p.getDate()))
            .collect(Collectors.toList()));
        } catch (SQLException ex) {
            Logger.getLogger(PicturePostService.class.getName()).log(Level.SEVERE, null, ex);
        };
        return list;
    }
}
