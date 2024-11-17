package com.example.demo.repository;

import com.example.demo.model.BorrowerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends JpaRepository<BorrowerModel, Long> {

}