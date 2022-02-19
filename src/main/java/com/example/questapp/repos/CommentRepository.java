package com.example.questapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByUserId(Long userId);

	List<Comment> findByPostId(Long postId);

	List<Comment> findByUserIdAndPostId(Long userId, Long postId);
}
