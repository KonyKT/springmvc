package pl.edu.ug.tud.springjpademo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.ug.tud.springjpademo.domain.Album;
import pl.edu.ug.tud.springjpademo.domain.Autor;
import pl.edu.ug.tud.springjpademo.domain.Piosenka;
import pl.edu.ug.tud.springjpademo.service.AlbumManager;
import pl.edu.ug.tud.springjpademo.service.AutorManager;


import javax.persistence.*;
import javax.persistence.RollbackException;
import javax.transaction.*;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlbumTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

	UserTransaction utx;

	@Autowired
	AlbumManager albummenager;

	private final static String IMIE_2 = "Andrzej";
	private final static String NAZWISKO_2 = "Rusak";
	private final static Date Data_2 = Date.valueOf("1996-02-09");

	private final static String TYTUL_1 = "Raz Dwa";
	private final static Date DATA_PREMIERY_1 = Date.valueOf("1990-01-01");
	private final static String TYTUL_2 = "Raz Trzy";
	private final static Date DATA_PREMIERY_2 = Date.valueOf("2005-01-01");
	private final static String TYTUL_3 = "Raz Cztery";
	private final static Date DATA_PREMIERY_3 = Date.valueOf("2010-01-01");

	@Test
	@Order(1)
	public void checkAdding() {

		Autor person = new Autor(IMIE_2, NAZWISKO_2, Data_2);
		Album person2 = new Album(TYTUL_1, DATA_PREMIERY_1, 1);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();


		em.getTransaction().begin();
		em.persist(person2);
		em.getTransaction().commit();

		List<Album> list = albummenager.getAl();
		assertEquals(1, list.size());


	}

	@Test
	@Order(2)
	public void checkRetrieving() {


		EntityManager em = emf.createEntityManager();

		List<Album> list = albummenager.getAl();
		assertEquals("Raz Dwa", list.get(0).getTytul());

	}


	@Test
	@Order(3)
	public void checkUpdating() {

		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a from Album a");
		List<Album> list = query.getResultList();
		em.getTransaction().begin();
		Album employee = em.find(Album.class, list.get(0).getId_album());
		employee.setTytul("Dwa Dwa");
		em.getTransaction().commit();
		employee = em.find(Album.class, list.get(0).getId_album());
		assertEquals("Dwa Dwa", employee.getTytul());

	}

	@Test
	@Order(4)
	public void checkDeleting() {

		EntityManager em = emf.createEntityManager();
		List<Album> list = albummenager.getAl();
		em.getTransaction().begin();
		Album employee = em.find(Album.class, list.get(0).getId_album());
		em.remove(employee);
		em.getTransaction().commit();
		em.getTransaction().begin();
		list = albummenager.getAl();
		em.getTransaction().commit();
		assertEquals(0, list.size());

	}
	@Test
	@Order(5)
	public void checkNamedQueryAutorAlbum() throws InterruptedException {
		EntityManager em = emf.createEntityManager();
		Album person2 = new Album(TYTUL_1, DATA_PREMIERY_1, 1);
		em.getTransaction().begin();
		em.persist(person2);
		em.getTransaction().commit();

		List<Album> list = albummenager.getAl();
		Query namedQuery = em.createNamedQuery("Album.findAlbumByAutorID");
		namedQuery.setParameter("idautor",list.get(0).getId_autor());
		List<Album> lista = namedQuery.getResultList();
		assertEquals(1, lista.size());



	}

	@Test
	@Order(6)
	public void checkNamedQueryOlderThan() throws InterruptedException {
		EntityManager em = emf.createEntityManager();
		Album person2 = new Album(TYTUL_1, DATA_PREMIERY_1, 1);
		Album person3 = new Album(TYTUL_2, DATA_PREMIERY_2, 1);
		Album person4 = new Album(TYTUL_3, DATA_PREMIERY_3, 1);

		em.getTransaction().begin();
		em.persist(person2);
		em.persist(person3);
		em.persist(person4);

		em.getTransaction().commit();

		List<Album> list = albummenager.getAl();
		Query namedQuery = em.createNamedQuery("Album.findAlbumsOlderThan");
		namedQuery.setParameter("data",Date.valueOf("2000-01-01"));
		List<Album> lista = namedQuery.getResultList();
		assertEquals(2, lista.size());



	}

	@Test
	@Order(7)
	public void checkNamedQueryOlderThanAndYounger() throws InterruptedException {
		EntityManager em = emf.createEntityManager();

		List<Album> list = albummenager.getAl();
		Query namedQuery = em.createNamedQuery("Album.findAlbumsOlderThanAndYounger");
		namedQuery.setParameter("data",Date.valueOf("2000-01-01"));
		namedQuery.setParameter("data2",Date.valueOf("2007-01-01"));
		List<Album> lista = namedQuery.getResultList();
		assertEquals(1, lista.size());



	}

	@Test
	@Order(8)
	public void checkNamedQueryAlbumNameBySong() {

		Autor person = new Autor(IMIE_2, NAZWISKO_2, Data_2);
		Album person2 = new Album(TYTUL_1, DATA_PREMIERY_1, 1);


		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.persist(person2);
		em.getTransaction().commit();

		List<Album> list = albummenager.getAl();
		Piosenka piosenka = new Piosenka("hej ho", list.get(0));
		Piosenka piosenka2 = new Piosenka("Test Test 1", list.get(0));
		Piosenka piosenka3 = new Piosenka("Test Test 2", list.get(2));
		Piosenka piosenka4 = new Piosenka("Test Test 2", list.get(2));


		em.getTransaction().begin();
		em.persist(piosenka);
		em.persist(piosenka2);
		em.persist(piosenka3);
		em.persist(piosenka4);
		em.getTransaction().commit();




		Query namedQuery = em.createNamedQuery("Album.findSongByAlbum");
		namedQuery.setParameter("tytul","Test Test 2");
		List<Album> lista = namedQuery.getResultList();
		assertEquals("Raz Trzy", lista.get(0).getTytul());


	}
}
