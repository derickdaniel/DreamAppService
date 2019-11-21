package com.datingapp.datingapp.dao;

import com.datingapp.datingapp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByPhoneNumber(final String phoneNumber);

    @Query(value = "select u from users as u where u.phoneNumber = ?1")
    List<User> getUserByPhoneNumber(final String phoneNumber);

    @Query(value = "select count(u) from users as u where u.phoneNumber = ?1")
    int getNumUsersWithPhoneNumber(final String phoneNumber);

    @Query(value = "select count(u) from users as u where u.phoneNumber = ?1 and u.apiKey = ?2")
    int getNumUsersWithPhoneNumberAndApiKey(final String phoneNumber, final String apiKey);
}
