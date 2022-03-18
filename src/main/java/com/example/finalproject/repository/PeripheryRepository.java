package com.example.finalproject.repository;

import com.example.finalproject.model.entity.PeripheryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeripheryRepository extends JpaRepository<PeripheryEntity,Long> {

    List<PeripheryEntity> findAll();
}
