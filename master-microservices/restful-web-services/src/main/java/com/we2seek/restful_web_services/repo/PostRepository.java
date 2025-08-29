package com.we2seek.restful_web_services.repo;

import com.we2seek.restful_web_services.dto.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer>{
}
