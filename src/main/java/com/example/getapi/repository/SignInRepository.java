package com.example.getapi.repository;

import com.example.getapi.entity.SignInEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignInRepository extends JpaRepository<SignInEntity, Long> {
}
