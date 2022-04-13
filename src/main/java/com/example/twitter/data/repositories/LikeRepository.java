package com.example.twitter.data.repositories;

import com.example.twitter.data.entities.Like;
import com.example.twitter.data.entities.Mention;
import com.example.twitter.data.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Integer> {

    Like findById(int id);

    Optional<Like> findByUsersByUserId_IdAndPostsByPostId_Id(String userId, Integer postId);
}