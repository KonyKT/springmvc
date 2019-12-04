package pl.edu.ug.tent.springmvcdemo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.ug.tent.springmvcdemo.domain.Produkt;
import pl.edu.ug.tent.springmvcdemo.service.ProduktManager;

import java.util.List;

@RestController
public class ProduktController {

  @Autowired
  ProduktManager pm;

  @GetMapping("/api/person")
  public List<Produkt> getPersons() {
    return pm.getAllPersons();
  }

  @PostMapping("/api/person")
  Produkt addPerson(@RequestBody Produkt person) {
    Produkt personToAdd = new Produkt(person.getNazwa(),person.getLokacja(),person.getCena(),person.getIlosc());
    pm.addProdukt(personToAdd);
    return personToAdd;
  }

  @GetMapping("/api/person/{id}")
  Produkt getPerson(@PathVariable String id) {
    return pm.findById(id);
  }

  @PutMapping("/api/person/{id}")
  void replacePerson(@RequestBody Produkt person, @PathVariable String id) {

    pm.findById(id).setNazwa(person.getNazwa());
    pm.findById(id).setLokacja(person.getLokacja());
    pm.findById(id).setCena(person.getCena());
    pm.findById(id).setIlosc(person.getIlosc());
  }

  @DeleteMapping("/api/person/{id}")
  void deletePerson(@PathVariable String id) {
    pm.remove(id);
  }

}
