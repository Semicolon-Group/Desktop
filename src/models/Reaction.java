package models;

import models.Enumerations.ReactionType;

public class Reaction {
    private int memberId;
    private int postId;
    private ReactionType reactionType;

    public Reaction(int memberId, int postId) {
        this.memberId = memberId;
        this.postId = postId;
    }
    
    public Reaction(int memberId, int postId, ReactionType reactionType) {
        this.memberId = memberId;
        this.postId = postId;
        this.reactionType = reactionType;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}
