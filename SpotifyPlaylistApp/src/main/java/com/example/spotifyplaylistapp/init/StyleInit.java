package com.example.spotifyplaylistapp.init;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class StyleInit implements CommandLineRunner {
    private final StyleRepository styleRepository;

    public StyleInit(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Style> styles = new ArrayList<>();


        if (!(styleRepository.count() > 0)){
            Arrays.stream(StyleName.values()).forEach(s -> {
                Style style = new Style(s);
                styles.add(style);

            });
            styleRepository.saveAll(styles);
        }

    }
}
