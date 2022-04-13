package com.example.twitter.data.entities.compositepk;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FollowId implements Serializable {

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowingUserId() {
        return followingUserId;
    }

    public void setFollowingUserId(String followingUserId) {
        this.followingUserId = followingUserId;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false)
    private String userId;
    @Basic
    @Column(name = "FOLLOWING_USER_ID", nullable = false)
    private String followingUserId;
}

