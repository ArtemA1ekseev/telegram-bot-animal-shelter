package com.skypro.telegrambotanimalshelter.repository;

import com.skypro.telegrambotanimalshelter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
