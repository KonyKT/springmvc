package pl.edu.ug.tud.springjpademo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.ug.tud.springjpademo.domain.Autor;

import java.util.List;

@Repository
public interface AutorManager extends JpaRepository<Autor,Integer> {

    @Query("SELECT u FROM Autor u")
    List<Autor> allAutors();

}
