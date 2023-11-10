package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.enums.StyleName;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    @NotNull
    private StyleName style;

    private String description;

    public Style() {
    }

    public Style(StyleName style) {
        this.style = style;
    }

    public StyleName getStyle() {
        return style;
    }

    public void setStyle(StyleName style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
