package com.itlize.repository;


import com.itlize.entity.test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testRepository extends JpaRepository<test,Integer> {
}
