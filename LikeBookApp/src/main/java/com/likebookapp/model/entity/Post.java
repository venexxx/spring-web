package com.likebookapp.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    @Column(nullable = false)
    @Length(min = 2,max = 50)
    private String content;


    @ManyToOne
    private User user;

    @ManyToMany
    private Set<User> userLikes;


    @ManyToOne
    private Mood mood;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Set<User> userLikes) {
        this.userLikes = userLikes;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }
}
