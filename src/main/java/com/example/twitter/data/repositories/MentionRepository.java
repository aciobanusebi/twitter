package com.example.twitter.data.repositories;

import com.example.twitter.data.entities.Mention;
import com.example.twitter.data.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MentionRepository extends CrudRepository<Mention, Integer> {

    Mention findById(int id);
}