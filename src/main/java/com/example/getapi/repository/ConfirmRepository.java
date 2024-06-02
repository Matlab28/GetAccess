package com.example.getapi.repository;

import com.example.getapi.entity.ConfirmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmRepository extends JpaRepository<ConfirmEntity, Long> {
}
