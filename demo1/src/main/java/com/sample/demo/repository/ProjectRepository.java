package com.sample.demo.repository;

import com.sample.demo.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findByProjectCode(BigInteger code);
}
