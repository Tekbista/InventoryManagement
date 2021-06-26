package com.bista.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bista.inventory.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String username);
}
