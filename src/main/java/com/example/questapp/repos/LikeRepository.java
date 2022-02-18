package com.example.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like,Long> {

}
