package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.entity.Song;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {


//    @Query(
//            value = "SELECT * FROM songs s left JOIN users_playlist AS up ON s.id = up.playlist_id left JOIN users AS u ON u.id = up.user_id where up.user_id :#{#id}",
//            nativeQuery = true)
//    Set<Song> findAllByPlaylistIsNotIn(@Param("id") Long id);



    @Query("select s, u from Song s join s.users u on u.id = :id")
    Set<Song> findAllByUserId(@Param("id") Long id);
}
