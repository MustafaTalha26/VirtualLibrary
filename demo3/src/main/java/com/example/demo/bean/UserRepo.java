package com.example.demo.bean;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Blog, Integer> {
	List<Blog> findByTitleContainingOrContentContaining(String text, String textAgain);
	List<Blog> findByTitle(String text);
}
