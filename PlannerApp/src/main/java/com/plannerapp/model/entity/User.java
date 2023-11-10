package com.plannerapp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{


        @Column(nullable = false, unique = true)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false, unique = true)
        @Email
        private String email;


        @OneToMany(mappedBy = "user")
        private Set<Task> tasks;



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

        public Set<Task> getAssignedTasks() {
                return tasks;
        }

        public void setAssignedTasks(Set<Task> tasks) {
                this.tasks = tasks;
        }

}
