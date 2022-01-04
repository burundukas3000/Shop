package com.project.backend.repositories;

import com.project.backend.models.User;
import com.project.backend.models.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    User getUserByUserNameAndEmail(String username, String email);

    @Query("SELECT new com.project.backend.models.UserActivity(u, SUM(p.productCosts), COUNT(u.userName)) FROM User u\n" +
            "JOIN Purchase p ON u.id=p.user.id GROUP BY u.id ORDER BY SUM(p.productCosts) DESC")
    List<UserActivity> getByMoneySpent();

    @Query("SELECT new com.project.backend.models.UserActivity(u, SUM(p.productCosts), COUNT(u.userName)) FROM User u\n" +
            "JOIN Purchase p ON u.id=p.user.id GROUP BY u.id ORDER BY COUNT(u.id) DESC")
    List<UserActivity> getByPurchaseFreq();

    User getUserByEmail(String email);
}