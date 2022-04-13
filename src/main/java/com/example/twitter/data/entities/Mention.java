package com.example.twitter.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mentions", schema = "twitter")
public class Mention {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference(value = "d")
    private User usersByUserId;
    @ManyToOne
    @JoinColumn(name = "POST_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference(value="2")
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
        Mention mention = (Mention) o;
        return id == mention.id;
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
