package pl.edu.ug.tud.springjpademo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.ug.tud.springjpademo.domain.Autor;
import pl.edu.ug.tud.springjpademo.domain.Piosenka;

import java.util.List;

@Repository
public interface PiosenkaManager  {

    @Query("SELECT u FROM Piosenka u")
    List<Piosenka> allPiosenka();

}
