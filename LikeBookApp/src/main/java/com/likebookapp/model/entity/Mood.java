package com.likebookapp.model.entity;

import com.likebookapp.model.entity.BaseEntity;
import com.likebookapp.model.enums.MoodName;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Table(name = "moods")
@Entity
public class Mood extends BaseEntity {

    public Mood() {
    }

    public Mood(MoodName moodName) {
       setMoodName(moodName);
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private MoodName moodName;


    private String description;

    public MoodName getMoodName() {
        return moodName;
    }

    public void setMoodName(MoodName moodName) {
        this.moodName = moodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
