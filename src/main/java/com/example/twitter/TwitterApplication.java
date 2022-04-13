package com.example.twitter;

import com.example.twitter.data.entities.User;
import com.example.twitter.data.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class TwitterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TwitterApplication.class, args);

//        UserRepository repo = context.getBean(UserRepository.class);
//
//        User user = new User();
//        user.setId("acsebi");
//        user.setFirstName("Sebastian");
//        user.setLastName("Ciobanu");
//        user.setEmail("aciobanusebi@gmail.com");
//        user.setPassword("pass");
//        user = repo.save(user);
//        System.out.println(repo.findAll());
    }

}
