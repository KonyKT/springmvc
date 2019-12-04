package pl.edu.ug.tent.springmvcdemo.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.ug.tent.springmvcdemo.domain.Produkt;
import pl.edu.ug.tent.springmvcdemo.service.ProduktManager;

import java.io.IOException;

@Controller("personwebcontroller")
public class ProduktController {

  private ProduktManager pm;

  @Autowired
  public ProduktController(ProduktManager pm) {
    this.pm = pm;
  }

  @GetMapping("/person")
  public String home(Model model){
    model.addAttribute("persons", pm.getAllPersons());
    return "person-all";
  }

  @GetMapping("/person/save")
  public String save(Model model) throws IOException {
    pm.save();
    model.addAttribute("persons", pm.getAllPersons());
    return "person-all";
  }

  @GetMapping("/person/load")
  public String load(Model model) throws IOException, ClassNotFoundException {
    pm.load();
    model.addAttribute("persons", pm.getAllPersons());
    return "person-all";
  }

  @GetMapping("/person/listH1")
  public String listH1(Model model){
    model.addAttribute("persons", pm.getH1());
    return "person-lokacja";
  }

  @GetMapping("/person/listH2")
  public String listH2(Model model){
    model.addAttribute("persons", pm.getH2());
    return "person-lokacja2";
  }

  @GetMapping("/person/listS1")
  public String listS1(Model model){
    model.addAttribute("persons", pm.getS1());
    return "person-lokacja3";
  }

  @GetMapping("/person/listS2")
  public String listS2(Model model){
    model.addAttribute("persons", pm.getS2());
    return "person-lokacja4";
  }

  @GetMapping("/person/new")
  public String newPerson(Model model){
    model.addAttribute("person", new Produkt());
    return "person-add";
  }

  @PostMapping("/person/upd")
  public String upDATE(Produkt person,Model model){
    pm.update(person.getId(),person);
    model.addAttribute("persons", pm.getAllPersons());
    return "person-all";
  }

  @GetMapping("/person/edit/{id}")
  public String updatePerson(@PathVariable("id") String id, Model model){
    Produkt x = new Produkt(pm.findById(id).getNazwa(),pm.findById(id).getLokacja(),pm.findById(id).getCena(),pm.findById(id).getIlosc());
    x.setId(id);
    model.addAttribute("person",x );

    return "person-update";
  }

  @GetMapping("/person/delete/{id}")
  public String deletePerson(@PathVariable("id") String id, Model model) {
    pm.remove(id);
    model.addAttribute("persons", pm.getAllPersons());
    return "person-all";
  }

  @PostMapping("/person/add")
  public String addPerson(Produkt person, Model model) {
    pm.addProdukt(person);
    model.addAttribute("persons", pm.getAllPersons());
    return "person-all";
  }

}
