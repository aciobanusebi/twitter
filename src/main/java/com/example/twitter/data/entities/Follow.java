package com.example.twitter.data.entities;

import com.example.twitter.data.entities.compositepk.FollowId;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "follows", schema = "twitter")
public class Follow {
    public FollowId getId() {
        return id;
    }

    public void setId(FollowId id) {
        this.id = id;
    }

    @EmbeddedId
    private FollowId id;
    @Basic
    @Column(name = "TIMESTAMP", nullable = false)
    private long timestamp;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "a")
    private User usersByUserId;
    @ManyToOne
    @JoinColumn(name = "FOLLOWING_USER_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @JsonBackReference(value = "b")
    private User usersByFollowingUserId;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return timestamp == follow.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp);
    }

    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public User getUsersByFollowingUserId() {
        return usersByFollowingUserId;
    }

    public void setUsersByFollowingUserId(User usersByFollowingUserId) {
        this.usersByFollowingUserId = usersByFollowingUserId;
    }
}
