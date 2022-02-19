package com.example.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.CommentRepository;
import com.example.questapp.requests.CommentCreateRequest;
import com.example.questapp.requests.CommentUpdateRequest;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;
	
	public CommentService(UserService userService, PostService postService,CommentRepository commentRepository) {
		this.userService = userService;
		this.postService = postService;
		this.commentRepository = commentRepository;
	}

	public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
		if (userId.isPresent()) // isPresent yani userid gelmişse.
			return commentRepository.findByUserId(userId.get());
		else if(postId.isPresent())
			return commentRepository.findByPostId(postId.get());
		else if(postId.isPresent() && userId.isPresent())
			return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		else
		return commentRepository.findAll();
	}
	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}
	public Comment createOneComment(CommentCreateRequest request) {
		User user=userService.getOneUserById(request.getUserId());
		Post post=postService.getOnePostId(request.getPostId());
		if(user!=null && post !=null) {
			Comment commentToSave=new Comment();
			commentToSave.setId(request.getId());
			commentToSave.setPost(post);
			commentToSave.setUser(user);
			commentToSave.setText(request.getText());
			return commentRepository.save(commentToSave);
		}
		return null;
	}

	public Comment updateOneCommentById(Long commentId,CommentUpdateRequest request) {
		Optional<Comment> comment=commentRepository.findById(commentId);
		if(comment.isPresent()) {
			Comment commentToUpdate = comment.get();
			commentToUpdate.setText(request.getText());
			return commentRepository.save(commentToUpdate);
		}else
			return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}
	
	

}
