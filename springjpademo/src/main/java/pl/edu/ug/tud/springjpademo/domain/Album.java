package pl.edu.ug.tud.springjpademo.domain;

import pl.edu.ug.tud.springjpademo.service.AlbumManager;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@NamedQuery(name = "Album.findAlbumByAutorID", query = "Select u from Album u Where u.id_autor=:idautor")
@NamedQuery(name = "Album.findAlbumsOlderThan", query = "Select u from Album u Where u.data_premiery>=:data")
@NamedQuery(name = "Album.findAlbumsOlderThanAndYounger", query = "Select u from Album u Where u.data_premiery>=:data AND u.data_premiery<=:data2")
@NamedQuery(name = "Album.findSongByAlbum", query = "Select u from Album u LEFT JOIN Piosenka a ON u.id_album = a.album Where a.tytul=:tytul")


@Table(name = "ALBUM")
public class Album  {

    private int id_album;
    private String tytul;
    private Date data_premiery;
    private int id_autor;
    @OneToMany(targetEntity=Piosenka.class, mappedBy="song", fetch=FetchType.EAGER)
    private Set<Piosenka> piosenki;

    @Id
    @GeneratedValue
    public int getId_album() {
        return id_album;
    }

    public void setId_album(int id_album) {
        this.id_album = id_album;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public Date getData_premiery() {
        return data_premiery;
    }

    public void setData_premiery(Date data_premiery) {
        this.data_premiery = data_premiery;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public Album() {
    }
    public Album( String tytul, Date data_premiery, int id_autor) {
        super();
        this.tytul = tytul;
        this.data_premiery = data_premiery;
        this.id_autor = id_autor;
    }


}

