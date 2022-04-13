package com.example.twitter.data.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "replies", schema = "twitter")
@PrimaryKeyJoinColumn(name = "POST_ID")
public class Reply extends Post {
    @Basic
    @Column(name = "PUBLIC", nullable = false)
    private Boolean isPublic;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "POST_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
//    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
//    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    @JsonBackReference
    private Post postsByPostId;
    @ManyToOne
    @JoinColumn(name = "PARENT_POST_ID", referencedColumnName = "ID", nullable = false)
    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true) // otherwise first ref as POJO, others as id
    private Post postsByParentPostId;

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return isPublic == reply.isPublic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPublic);
    }

    public Post getPostsByPostId() {
        return postsByPostId;
    }

    public void setPostsByPostId(Post postsByPostId) {
        this.postsByPostId = postsByPostId;
    }

    public Post getPostsByParentPostId() {
        return postsByParentPostId;
    }

    public void setPostsByParentPostId(Post postsByParentPostId) {
        this.postsByParentPostId = postsByParentPostId;
    }
}
