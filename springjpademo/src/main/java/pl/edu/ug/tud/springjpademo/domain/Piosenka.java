package pl.edu.ug.tud.springjpademo.domain;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Piosenka.findSongByAlbum", query = "Select u from Piosenka u LEFT JOIN Album a ON a.id_album = u.album Where a.tytul=:tytul")

public class Piosenka {

  private long id;
  private String tytul;


  private Album album;

  @Id
  @GeneratedValue
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTytul() {
    return tytul;
  }

  public void setTytul(String name) {
    this.tytul = name;
  }

  @ManyToOne
  @JoinColumn(name="id_album", nullable=false)
  public Album getAlbum() {
    return album;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  public Piosenka(){

  }
  public Piosenka(String tytul, Album album) {
    super();
    this.tytul = tytul;
    this.album = album;
  }
}
