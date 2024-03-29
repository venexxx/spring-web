package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
        public User() {
                this.playlist = new HashSet<>();
        }

        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false, unique = true)
        @Email
        private String email;


        @ManyToMany
        private Set<Song> playlist;

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public Set<Song> getPlaylist() {
                return playlist;
        }

        public void setPlaylist(Set<Song> playlist) {
                this.playlist = playlist;
        }
}
