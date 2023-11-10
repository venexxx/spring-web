package com.likebookapp.model.dtos;

import com.likebookapp.model.entity.Mood;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class PostDTO {

    @Length(min = 2,max = 50,message = "Content length must be between 2 and 50 characters!")
    @NotNull
    private String content;

    @NotNull
    private String mood;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
