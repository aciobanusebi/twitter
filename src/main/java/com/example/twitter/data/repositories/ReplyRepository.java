package com.example.twitter.data.repositories;

import com.example.twitter.data.entities.Like;
import com.example.twitter.data.entities.Reply;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReplyRepository extends CrudRepository<Reply, Integer> {

    Reply findById(int id);
}