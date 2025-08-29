package com.we2seek.restful_web_services.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Id should be greater than 0")
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @Past(message = "Birth date should be in past")
    private LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;

    public User() {

    }

    public User(Integer id, String name, LocalDate birthDate, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("birthDate=" + birthDate)
                .add("posts=" + posts)
                .toString();
    }
}
