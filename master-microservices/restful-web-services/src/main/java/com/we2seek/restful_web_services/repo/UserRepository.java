package com.we2seek.restful_web_services.repo;

import com.we2seek.restful_web_services.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
