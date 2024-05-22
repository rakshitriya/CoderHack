package com.coderhack.week1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.coderhack.week1.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
}
