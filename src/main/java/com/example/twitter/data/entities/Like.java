package com.example.twitter.data.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "likes", schema = "twitter")
public class Like {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    @JsonBackReference(value = "c")
    private User usersByUserId;
    @ManyToOne
    @JoinColumn(name = "POST_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference(value = "a")
    private Post postsByPostId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return id == like.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public Post getPostsByPostId() {
        return postsByPostId;
    }

    public void setPostsByPostId(Post postsByPostId) {
        this.postsByPostId = postsByPostId;
    }
}
