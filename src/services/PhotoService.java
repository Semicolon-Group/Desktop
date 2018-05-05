/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.semicolon.javavichuploaderapi.Uploader;
import controller.MySoulMate;
import iservice.Create;
import iservice.Delete;
import iservice.Read;
import iservice.Update;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Enumerations;
import models.Enumerations.PhotoType;
import models.Member;
import models.Photo;
import org.apache.http.conn.ConnectionRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.FileUploader;
import util.HTTPConnector;

/**
 *
 * @author Elyes
 */
public class PhotoService{
private Photo photo;
    private List<Photo> photos;
    private static PhotoService instance;
    
    public static PhotoService getInstance(){
        if (instance == null)
            instance = new PhotoService();
        return instance;
    }
    private PhotoService(){}
    
    public Photo getPhoto(int photoId){
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/seif/getPhoto/"+photoId;
        String content = HTTPConnector.connect(url);
        if(content != null && !content.isEmpty()){
            try {
                JSONParser j = new JSONParser();
                
                JSONObject photoMap = (JSONObject)j.parse(new StringReader(content.toString()));
                if(photoMap.isEmpty())
                    return null;
                photo = parsePhoto(photoMap);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return photo;
    }
    
    public List<Photo> getRegularPhotos(int userId){
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/seif/getPhotos/"+userId;
        String content = HTTPConnector.connect(url);
        if(content!=null && !content.isEmpty()){
            try {
                photos = new ArrayList<>();
                JSONParser j = new JSONParser();
                
                JSONArray likeMap = (JSONArray)j.parse(new StringReader(content.toString()));
                //JSONArray likeList = (JSONArray) likeMap.get("root");
                if(likeMap != null && !likeMap.isEmpty()){
                    for(int i=0; i<likeMap.size(); i++){
                        photos.add(parsePhoto((JSONObject)likeMap.get(i)));
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return photos;
    }
    
    public Photo getProfilePhoto(int userId){
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/seif/getProfilePhoto/"+userId;
        String content = HTTPConnector.connect(url);
        if(content!=null && !content.isEmpty()){
             try {
                JSONParser j = new JSONParser();
                JSONObject photoMap = (JSONObject)j.parse(new StringReader(content.toString()));
                
                if(photoMap.isEmpty()){
                    photo = null;
                }else{
                    photo = parsePhoto(photoMap);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            photo = null;
        }
        return photo;
    }
    
    public Photo getCoverPhoto(int userId){
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/seif/getCoverPhoto/"+userId;
        String content = HTTPConnector.connect(url);
        if(content != null && !content.isEmpty()){
            try {
                JSONParser j = new JSONParser();
                JSONObject photoMap = (JSONObject)j.parse(new StringReader(content.toString()));
                
                if(photoMap.isEmpty()){
                    photo = null;
                }else{
                    photo = parsePhoto(photoMap);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            photo = null;
        }
        return photo;
    }
    
    public Photo addPhoto(String filePath){
        try {
            Photo p = new Photo("BaseBundle", "imageFile", "http://localhost/mysoulmate/web/app_dev.php/service/seif/uploadPhoto", filePath, MySoulMate.MEMBER_ID);
            Uploader.upload(p);
	    p.setType(Enumerations.PhotoType.REGULAR);
	    return p;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private Photo parsePhoto(JSONObject photoMap){
        Photo p = new Photo();
        p.setId(((Long)photoMap.get("id")).intValue());
        p.setPhotoUri((String)photoMap.get("image"));
        p.setUpdateDate(new java.util.Date((((Long)((Map<String, Object>)photoMap.get("date")).get("timestamp")).longValue()*1000)));
        p.setType(Enumerations.PhotoType.values()[((Long)photoMap.get("type")).intValue()]);
        p.setUserId(((Long)((Map<String, Object>)photoMap.get("user")).get("id")).intValue());
        return p;
    }
    
    public void deletePhoto(int photoId){
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/seif/deletePhoto/"+photoId;
        String content = HTTPConnector.connect(url);
    }
    
    public void setAsProfilePhoto(int photoId){
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/seif/setAsProfilePhoto/"+photoId;
        String content = HTTPConnector.connect(url);
    }
    
    public void setAsCoverPhoto(int photoId){
        String url = "http://localhost/mysoulmate/web/app_dev.php/service/seif/setAsCoverPhoto/"+photoId;
        String content = HTTPConnector.connect(url);
    }
    
    /*public ImageViewer EmakeImageViewer(String url){
        ImageViewer img = new ImageViewer();
        EncodedImage encodedImage = EncodedImage.createFromImage(theme.getImage("round.png"),false);
	URLImage uRLImage;
	uRLImage = URLImage.createToStorage(encodedImage, (new Random()).nextInt()+"", "http://localhost" + url);
        img.setImage(uRLImage);
        return img;
    }
    
    public Label EmakeImageViewerBig(String url){
        EncodedImage encodedImage = EncodedImage.createFromImage(theme.getImage("loading_post.png"),false);
	URLImage uRLImage;
	uRLImage = URLImage.createToStorage(encodedImage, (new Random()).nextInt()+"", "http://localhost" + url);
        Label img = new Label(uRLImage);
        return img;
    }*/
}
