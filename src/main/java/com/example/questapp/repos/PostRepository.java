package com.example.questapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questapp.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
