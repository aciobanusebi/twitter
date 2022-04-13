package com.example.twitter.data.repositories;

import com.example.twitter.data.entities.Follow;
import com.example.twitter.data.entities.Post;
import com.example.twitter.data.entities.compositepk.FollowId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {

    Optional<Post> findById(int id);

    List<Post> findByUsersByUserId_IdAndTimestampGreaterThan(String id, long minTimestamp);
}