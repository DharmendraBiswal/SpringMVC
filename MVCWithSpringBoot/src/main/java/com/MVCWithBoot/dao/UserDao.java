package com.MVCWithBoot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.MVCWithBoot.model.User;

@Repository
public class UserDao {
	
	@PersistenceContext
	EntityManager entityManager;

	public Integer addUser(User user) {
		entityManager.persist(user);
		return user.getUserId();
	}

	public User validateUser(String userName, String password) {
		List<User> users = entityManager
				.createQuery("from User where userName = '" + userName + "' and password = '" + password + "'")
				.getResultList();
		return users.size() > 0 ? users.get(0) : null;
	}

}
