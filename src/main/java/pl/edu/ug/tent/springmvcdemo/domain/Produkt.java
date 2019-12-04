package pl.edu.ug.tent.springmvcdemo.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Produkt implements Serializable {
    private String id;
    private String nazwa;
    private String lokacja;
    private double cena;
    private int ilosc;
    String[] lokacje = new String[]{"Hangar 1","Hangar 2","Skladzik 1p","Skladzik 2p"};
    List<String> list = Arrays.asList(lokacje);



    public Produkt(String nazwa,String lokacja,double cena, int ilosc) {
        this.nazwa = nazwa;
        if (list.contains(lokacja)){
            this.lokacja = lokacja;

        }
        else {
            this.setLokacja("Blad");
        }
        if(cena<0){
            this.cena = 9999;
        }
        else{
            this.cena = cena;
        }
        if(ilosc<0){
            this.ilosc = 9999;
        }
        else{
            this.ilosc = ilosc;
        }
    }

    public Produkt() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getLokacja() {
        return lokacja;
    }

    public void setLokacja(String lokacja) {
        this.lokacja = lokacja;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

}
