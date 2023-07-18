package com.rk.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rk.blog.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
