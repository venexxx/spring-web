package com.example.spotifyplaylistapp.model.entity;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity{


    @NotNull
    @Size(min = 3,max = 20)
    private String performer;

    @NotNull
    @Size(min = 3,max = 20)
    private String title;

    @Positive
    @NotNull
    private Integer duration;

    @Column(name = "release_date")
    @Future
    private LocalDate releaseDate;


    @ManyToOne
    @NotNull
    private Style style;
    @ManyToMany(mappedBy = "playlist")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
