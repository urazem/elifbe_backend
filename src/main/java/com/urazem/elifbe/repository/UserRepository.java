package com.urazem.elifbe.repository;

import com.urazem.elifbe.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username AND u.email = :email")
    boolean existsByUsernameAndEmail(@Param("username") String firstName, @Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email AND u.password = :password")
    boolean existsByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query(value = "select u from User u where u.id = :id")
    User findById(@Param("id") int id);
    User findByEmail(String email);
}