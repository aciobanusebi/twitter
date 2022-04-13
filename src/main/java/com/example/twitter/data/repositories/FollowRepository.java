package com.example.twitter.data.repositories;

import com.example.twitter.data.entities.Follow;
import com.example.twitter.data.entities.compositepk.FollowId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FollowRepository extends CrudRepository<Follow, Integer> {

    Optional<Follow> findById(FollowId id);
    void deleteById(FollowId id);
}