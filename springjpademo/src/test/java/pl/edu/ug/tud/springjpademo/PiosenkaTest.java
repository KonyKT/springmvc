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
import pl.edu.ug.tud.springjpademo.service.PiosenkaManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PiosenkaTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

	UserTransaction utx;

	@Autowired
	AlbumManager albummenager;
	PiosenkaManager piosenkamanager;
	private final static String IMIE_2 = "Andrzej";
	private final static String NAZWISKO_2 = "Rusak";
	private final static Date Data_2 = Date.valueOf("1996-02-09");

	private final static String TYTUL_1 = "Raz Dwa";
	private final static Date DATA_PREMIERY_1 = Date.valueOf("2000-01-01");

	private final static String tyt = "Hej 1";


	@Test
	@Order(1)
	public void checkAdding() {

		Autor person = new Autor(IMIE_2, NAZWISKO_2, Data_2);
		Album person2 = new Album(TYTUL_1, DATA_PREMIERY_1, 1);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.persist(person2);
		em.getTransaction().commit();

		List<Album> list = albummenager.getAl();
		Piosenka piosenka = new Piosenka(tyt, list.get(0));
		em.getTransaction().begin();
		em.persist(piosenka);
		em.getTransaction().commit();
		Query query = em.createQuery("Select a from Piosenka a");
		List<Piosenka> lista = query.getResultList();
		assertEquals(1, lista.size());


	}

	@Test
	@Order(2)
	public void checkRetrieving() {


		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("Select a from Piosenka a");
		List<Piosenka> lista = query.getResultList();
		assertEquals("Hej 1", lista.get(0).getTytul());

	}


	@Test
	@Order(3)
	public void checkUpdating() {

		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a from Piosenka a");
		List<Piosenka> lista = query.getResultList();
		em.getTransaction().begin();
		Piosenka employee = em.find(Piosenka.class, lista.get(0).getId());
		employee.setTytul("Hej 2");
		em.getTransaction().commit();
		employee = em.find(Piosenka.class, lista.get(0).getId());
		assertEquals("Hej 2", employee.getTytul());

	}

	@Test
	@Order(4)
	public void checkDeleting() {

		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("Select a from Piosenka a");
		List<Piosenka> lista = query.getResultList();
		em.getTransaction().begin();
		Piosenka employee = em.find(Piosenka.class, lista.get(0).getId());
		em.remove(employee);
		em.getTransaction().commit();
		em.getTransaction().begin();
		lista = query.getResultList();
		em.getTransaction().commit();
		assertEquals(0, lista.size());

	}

	@Test
	@Order(5)
	public void checkAdding2() {

		Autor person = new Autor(IMIE_2, NAZWISKO_2, Data_2);
		Album person2 = new Album(TYTUL_1, DATA_PREMIERY_1, 1);
		Album person3 = new Album("Test", DATA_PREMIERY_1, 1);


		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(person);
		em.persist(person2);
		em.persist(person3);
		em.getTransaction().commit();

		List<Album> list = albummenager.getAl();
		Piosenka piosenka = new Piosenka(tyt, list.get(0));
		Piosenka piosenka2 = new Piosenka("Test Test 1", list.get(0));
		Piosenka piosenka3 = new Piosenka("Test Test 2", list.get(2));
		Piosenka piosenka4 = new Piosenka("Test Test 2", list.get(2));


		em.getTransaction().begin();
		em.persist(piosenka);
		em.persist(piosenka2);
		em.persist(piosenka3);
		em.persist(piosenka4);
		em.getTransaction().commit();
		Query query = em.createQuery("Select a from Piosenka a");
		List<Piosenka> lista = query.getResultList();
		assertEquals(4, lista.size());



		Query namedQuery = em.createNamedQuery("Piosenka.findSongByAlbum");
		namedQuery.setParameter("tytul","Test");
		lista = namedQuery.getResultList();
		assertEquals(2, lista.size());


	}
}

