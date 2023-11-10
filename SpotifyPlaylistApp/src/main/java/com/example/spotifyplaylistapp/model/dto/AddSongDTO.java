package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.Style;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AddSongDTO {


    @NotNull
    @Size(min = 3,max = 20)
    private String performer;

    @NotNull
    @Size(min = 3,max = 20)
    private String title;

    @Positive
    @NotNull
    private Integer duration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate releaseDate;



    @NotNull
    private String style;

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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
