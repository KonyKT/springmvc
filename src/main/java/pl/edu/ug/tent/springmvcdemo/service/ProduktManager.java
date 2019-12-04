package pl.edu.ug.tent.springmvcdemo.service;

import pl.edu.ug.tent.springmvcdemo.domain.Produkt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProduktManager {

  void addProdukt(Produkt produkt);

  Produkt findById(String id);

  List<Produkt> getAllPersons();
  List<Produkt> getH1();
  List<Produkt> getH2();
  List<Produkt> getS1();
  List<Produkt> getS2();

  void save() throws IOException;
  void load() throws IOException, ClassNotFoundException;

  void remove(String id);

  void update(String id, Produkt produkt);

  public void zmiana(Produkt produkt,Produkt produkt2);

  List<Produkt> findByFirstName(String firstName);

}
