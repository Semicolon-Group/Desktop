/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static controller.GlobalViewController.online;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import models.Like;
import models.Member;
import models.PicturePost;
import models.Post;
import models.StatusPost;

/**
 *
 * @author Elyes
 */
public class NewsFeed {
    private Set<Post> feed;
    
    private static NewsFeed instance;
    
    public static NewsFeed getInstance(){
        if (instance == null)
            instance = new NewsFeed();
        return instance;
    }
    
    private NewsFeed(){
        
    }
    
    public Set<Post> getFeed(final Member M) throws SQLException{
        feed = new TreeSet<Post>((a,b) -> b.getDate().compareTo(a.getDate()));
        /*
        * Extracting list of members liked.
        */
        List<Like> likes = LikeService.getInstance().getAll(new Like(M.getId(),0,null));
        List<Member> liked = new ArrayList();
        likes.stream().forEach(l -> {
            try {
                liked.add(MemberService.getInstance().get(new Member(l.getReceiverId())));
            } catch (SQLException ex) {
                Logger.getLogger(NewsFeed.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /*
        * Populating feed.
        */
        liked.stream().forEach(m -> {
            try {
                // Adding recent status posts
                feed.addAll(StatusPostService.getInstance().getAll(new StatusPost(null,m.getId(),null))
                .stream().collect(Collectors.toList()));
                // Adding recent picture posts
                feed.addAll(PicturePostService.getInstance().getAll(new PicturePost(m.getId(),null)));
            } catch (SQLException ex) {
                Logger.getLogger(NewsFeed.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //Adding the connected user's posts
        feed.addAll(StatusPostService.getInstance().getAll(new StatusPost(null,online.getId(),null))
        .stream().collect(Collectors.toList()));
        feed.addAll(PicturePostService.getInstance().getAll(new PicturePost(online.getId(),null)));
        return feed;
    }
}
