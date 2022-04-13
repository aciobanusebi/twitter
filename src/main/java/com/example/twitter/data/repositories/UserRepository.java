package com.example.twitter.data.repositories;

import java.util.List;
import java.util.Optional;

import com.example.twitter.data.entities.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findById(@NotNull String id);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}