package com.we2seek.restful_web_services.controller;

import com.we2seek.restful_web_services.dto.Post;
import com.we2seek.restful_web_services.dto.User;
import com.we2seek.restful_web_services.exception.UserNotFoundException;
import com.we2seek.restful_web_services.repo.PostRepository;
import com.we2seek.restful_web_services.repo.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id=" + id));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = userRepository.save(user);
        log.info("Add user: {}", newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User updatedUser) {
        log.info("Update user with id: {}, updated user: {}", id, updatedUser);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id=" + id));
        updatedUser.setId(user.getId());
        User saved = userRepository.save(updatedUser);
        return ResponseEntity.status(200).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        log.info("Delete user with id: {}", id);
        userRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    /// Posts
    @GetMapping("/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        log.info("Get user {} posts...", id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id=" + id));
        return user.getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> addPost(@PathVariable int id, @Valid @RequestBody Post post) {
        log.info("Add post: {}", post);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id=" + id));
        post.setUser(user);
        Post saved = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
