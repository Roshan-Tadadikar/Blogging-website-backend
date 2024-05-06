package com.example.demo.repositories;

import com.example.demo.Models.LoggerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<LoggerModel, Integer> {

}
