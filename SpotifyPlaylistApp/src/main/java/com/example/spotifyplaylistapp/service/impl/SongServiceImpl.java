package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.dto.AddSongDTO;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.enums.StyleName;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {
    private final StyleRepository styleRepository;
    private final SongRepository songRepository;

    public SongServiceImpl(StyleRepository styleRepository, SongRepository songRepository) {
        this.styleRepository = styleRepository;
        this.songRepository = songRepository;
    }


    @Override
    public void addSong(AddSongDTO addSongDTO) {
        Song song = new Song();
        Style style = styleRepository.findByStyle(StyleName.valueOf(addSongDTO.getStyle()));
        song.setDuration(addSongDTO.getDuration());
        song.setPerformer(addSongDTO.getPerformer());
        song.setTitle(addSongDTO.getTitle());
        song.setStyle(style);
        song.setReleaseDate(addSongDTO.getReleaseDate());

        songRepository.save(song);
    }
}
