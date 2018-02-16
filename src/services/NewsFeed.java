/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.ArrayList;
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
    private List<Post> feed;
    
    private static NewsFeed instance;
    
    public static NewsFeed getInstance(){
        if (instance == null)
            instance = new NewsFeed();
        return instance;
    }
    
    private NewsFeed(){
        //feed = new TreeSet<Post>((a,b) -> a.getDate().compareTo(b.getDate()));
        feed = new ArrayList();
    }
    
    public List<Post> getFeed(final Member M) throws SQLException{
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
                .stream().filter(A -> A.getDate().compareTo(M.getLastLogin()) > 0)
                .collect(Collectors.toList()));
                // Adding recent picture posts
                feed.addAll(PicturePostService.getInstance().getAll(new PicturePost(m.getId(),M.getLastLogin())));
            } catch (SQLException ex) {
                Logger.getLogger(NewsFeed.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return feed;
    }
}
