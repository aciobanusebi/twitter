package com.example.twitter.data.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "twitter")
public class User {
    @Id
    @Column(name = "ID", nullable = false, length = 255)
    private String id;
    @Basic
    @Column(name = "FIRST_NAME", nullable = false, length = 255)
    private String firstName;
    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 255)
    private String lastName;
    @Basic
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;
    @OneToMany(mappedBy = "usersByUserId",cascade=CascadeType.ALL)
    @JsonManagedReference
    private Collection<Follow> followsById;
    @OneToMany(mappedBy = "usersByFollowingUserId",cascade=CascadeType.ALL)
    @JsonManagedReference
    private Collection<Follow> followsById_0;
    @OneToMany(mappedBy = "usersByUserId",cascade=CascadeType.ALL)
    @JsonManagedReference
    private Collection<Like> likesById;
    @OneToMany(mappedBy = "usersByUserId",cascade=CascadeType.ALL)
    @JsonManagedReference
    private Collection<Mention> mentionsById;
    @OneToMany(mappedBy = "usersByUserId",cascade=CascadeType.ALL)
    @JsonManagedReference
    private Collection<Post> postsById;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password);
    }

    public Collection<Follow> getFollowsById() {
        return followsById;
    }

    public void setFollowsById(Collection<Follow> followsById) {
        this.followsById = followsById;
    }

    public Collection<Follow> getFollowsById_0() {
        return followsById_0;
    }

    public void setFollowsById_0(Collection<Follow> followsById_0) {
        this.followsById_0 = followsById_0;
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

    public Collection<Post> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<Post> postsById) {
        this.postsById = postsById;
    }
}
