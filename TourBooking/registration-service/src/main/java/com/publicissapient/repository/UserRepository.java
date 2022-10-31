package com.publicissapient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.publicissapient.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>  {

    public User findByEmail(String email);
    public User findByUsernameOrEmail(String username, String email);
    public User findByUsername(String username);
    public Boolean existsByUsername(String username);
    public Boolean existsByEmail(String email);
}
