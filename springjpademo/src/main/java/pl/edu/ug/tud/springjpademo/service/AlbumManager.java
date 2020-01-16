package pl.edu.ug.tud.springjpademo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.ug.tud.springjpademo.domain.Album;
import pl.edu.ug.tud.springjpademo.domain.Autor;

import java.util.List;

@Repository
public interface AlbumManager extends JpaRepository<Album, Integer> {
    @Query("select p from Album p")
    List<Album> getAl();

}