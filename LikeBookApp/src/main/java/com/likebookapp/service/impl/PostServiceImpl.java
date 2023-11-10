package com.likebookapp.service.impl;

import com.likebookapp.model.dtos.PostDTO;
import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.model.enums.MoodName;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.PostService;
import com.likebookapp.util.LoggedUser;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LoggedUser loggedUser;

    private final UserRepository userRepository;

    private final MoodRepository moodRepository;

    public PostServiceImpl(PostRepository postRepository, LoggedUser loggedUser, UserRepository userRepository, MoodRepository moodRepository) {
        this.postRepository = postRepository;
        this.loggedUser = loggedUser;
        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
    }

    @Override
    public void addPost(PostDTO postDTO) {
        User user = userRepository.getUserByUsername(loggedUser.getUsername());
        Mood mood = moodRepository.findByMoodName(MoodName.valueOf(postDTO.getMood().toString()));
        Post post = new Post();
        post.setContent(postDTO.getContent());
        post.setMood(mood);
        post.setUser(user);
        postRepository.save(post);
    }
}
