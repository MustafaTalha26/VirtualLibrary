package com.example.demo.bean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepo extends JpaRepository<Terms, Integer>{

}
