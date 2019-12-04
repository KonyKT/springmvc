package pl.edu.ug.tent.springmvcdemo.service;

import org.springframework.stereotype.Service;
import pl.edu.ug.tent.springmvcdemo.domain.Produkt;

import java.io.*;
import java.util.*;

@Service
public class ProduktService implements ProduktManager {
    String[] lokacje = new String[]{"Hangar 1","Hangar 2","Skladzik 1","Skladzik 2"};
    List<String> list = Arrays.asList(lokacje);

    private static List<Produkt> produkty = new ArrayList<>();

    public void addProdukt(Produkt person) {
        person.setId(UUID.randomUUID().toString());
        if(list.contains(person.getLokacja())){

        }
        else {
            person.setLokacja("Blad");
        }
        produkty.add(person);
    }

    @Override
    public Produkt findById(String id) {
        for (Produkt person : produkty) {
            if (person.getId().equals(id)) {
                return person;
            }
        }
        return null;
    }

    public List<Produkt> getAllPersons() {
        return produkty;
    }

    public List<Produkt> getH1() {
        List<Produkt> lista = new ArrayList<>();
        for(int i = 0; i< produkty.size(); i++){
            if(produkty.get(i).getLokacja().equals("Hangar 1")){
                lista.add(produkty.get(i));
            }
        }
        return lista;
    }

    public List<Produkt> getH2() {
        List<Produkt> lista = new ArrayList<>();
        for(int i = 0; i< produkty.size(); i++){
            if(produkty.get(i).getLokacja().equals("Hangar 2")){
                lista.add(produkty.get(i));
            }
        }
        return lista;
    }

    public List<Produkt> getS1() {
        List<Produkt> lista = new ArrayList<>();
        for(int i = 0; i< produkty.size(); i++){
            if(produkty.get(i).getLokacja().equals("Skladzik 1")){
                lista.add(produkty.get(i));
            }
        }
        return lista;
    }

    public List<Produkt> getS2() {
        List<Produkt> lista = new ArrayList<>();
        for(int i = 0; i< produkty.size(); i++){
            if(produkty.get(i).getLokacja().equals("Skladzik 2")){
                lista.add(produkty.get(i));
            }
        }
        return lista;
    }

    @Override
    public void save() throws IOException {

        FileOutputStream fos = new FileOutputStream("t.tmp");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(produkty);
        oos.close();
    }

    @Override
    public void load() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream("t.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        produkty = (List<Produkt>) ois.readObject();
        ois.close();
    }

    @Override
    public void remove(String id) {

        Produkt toRemove = null;
        for (Produkt person : produkty) {
            if (person.getId().equals(id)) {
                toRemove = person;
                break;
            }
        }
        if (toRemove != null) {
            produkty.remove(toRemove);
        }
    }

    @Override
    public void update(String id, Produkt produkt) {

        Produkt toRemove = findById(id);
        if (toRemove != null) {
            zmiana(toRemove,produkt);
        }
    }

    @Override
    public void zmiana(Produkt produkt,Produkt produkt2) {

        produkt.setNazwa(produkt2.getNazwa());
        if(list.contains(produkt2.getLokacja())){
            produkt.setLokacja(produkt2.getLokacja());
        }
        else{
            produkt.setLokacja("Blad");
        }
        if (produkt2.getCena()<0){
            produkt.setCena(9999);
        }
        else{
            produkt.setCena(produkt2.getCena());
        }
        if (produkt2.getIlosc()<0){
            produkt.setIlosc(9999);
        }
        else{
            produkt.setIlosc(produkt2.getIlosc());
        }

    }

    @Override
    public List<Produkt> findByFirstName(String firstName) {
        List<Produkt> result = new ArrayList<>();
        for (Produkt produkt : produkty) {
            if (firstName.equals(produkt.getNazwa())) {
                result.add(produkt);
            }
        }
        return result;
    }
}
