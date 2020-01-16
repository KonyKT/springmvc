package pl.edu.ug.tud.springjpademo.domain;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.sql.Date;
import java.util.List;

@Entity
@NamedQuery(name = "Autor.findAutorByID", query = "Select u from Autor u Where u.id=:id")

public class Autor {

    private int id;
    private String imie;
    private String nazwisko;
    private Date data;

    public Autor() {
    }

    public Autor(String imie, String nazwisko, Date date) {
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data = date;
    }
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImie() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }




}
