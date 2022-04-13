package com.example.twitter.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "posts", schema = "twitter")
@Inheritance(strategy = InheritanceType.JOINED)
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "MESSAGE", nullable = false, length = 255)
    private String message;
    @Basic
    @Column(name = "TIMESTAMP", nullable = false)
    private long timestamp;
    @OneToMany(mappedBy = "postsByPostId",cascade=CascadeType.ALL)
    private Collection<Like> likesById;
    @OneToMany(mappedBy = "postsByPostId",cascade=CascadeType.ALL)
    @JsonBackReference(value="3")
    private Collection<Mention> mentionsById;
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @JsonBackReference(value = "e")
    private User usersByUserId;
//    @Column(name = "USER_ID")
//    private String userId;
    @OneToOne(mappedBy = "postsByPostId",cascade=CascadeType.ALL)
    @JsonBackReference(value="4")
    private Reply repliesById;
    @OneToMany(mappedBy = "postsByParentPostId",cascade=CascadeType.ALL)
//    @JsonBackReference
    private Collection<Reply> repliesById_0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
        Post post = (Post) o;
        return id == post.id && timestamp == post.timestamp && Objects.equals(message, post.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, timestamp);
    }

    public Collection<Like> getLikesById() {
        return likesById;
    }

    public void setLikesById(Collection<Like> likesById) {
        this.likesById = likesById;
    }

    public Collection<Mention> getMentionsById() {
        return mentionsById;
    }

    public void setMentionsById(Collection<Mention> mentionsById) {
        this.mentionsById = mentionsById;
    }

    public User getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(User usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    public Reply getRepliesById() {
        return repliesById;
    }

    public void setRepliesById(Reply repliesById) {
        this.repliesById = repliesById;
    }

    public Collection<Reply> getRepliesById_0() {
        return repliesById_0;
    }

    public void setRepliesById_0(Collection<Reply> repliesById_0) {
        this.repliesById_0 = repliesById_0;
    }
}
