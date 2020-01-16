package pl.edu.ug.tud.springjpademo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.ug.tud.springjpademo.domain.Album;
import pl.edu.ug.tud.springjpademo.domain.Autor;
import pl.edu.ug.tud.springjpademo.service.*;


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
class AutorTest {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

	@Autowired
	AutorManager autormanager;

	UserTransaction utx;
	private final static String IMIE_2 = "Andrzej";
	private final static String NAZWISKO_2 = "Rusak";
	private final static Date Data_2 = Date.valueOf("1996-02-09");

	@Test
	@Order(1)
	public void checkAdding() {

		Autor person = new Autor(IMIE_2, NAZWISKO_2, Data_2);
		//	utx.begin();
		EntityManager em = emf.createEntityManager();
//		Query q3 = em.createQuery("DELETE FROM Autor");
//		Query q4 = em.createQuery("SELECT COUNT(*) FROM Autor");
//
//		q3.executeUpdate();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();


		em.getTransaction().begin();


		List<Autor> lista = autormanager.allAutors();
		em.getTransaction().commit();
		assertEquals(1, lista.size());



	}

	@Test
	@Order(2)
	public void checkRetrieving() {


		Autor person = new Autor(IMIE_2, NAZWISKO_2, Data_2);
		//	utx.begin();
		EntityManager em = emf.createEntityManager();
//		Query q3 = em.createQuery("DELETE FROM Autor");
//		Query q4 = em.createQuery("SELECT COUNT(*) FROM Autor");
//
//		q3.executeUpdate();
		em.getTransaction().begin();
		em.persist(person);
		em.getTransaction().commit();


		em.getTransaction().begin();
		Autor employee = em.find(Autor.class, 1);
		em.getTransaction().commit();
		assertEquals("Andrzej",employee.getImie());

	}

	@Test
	@Order(3)
	public void checkUpdating() {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Autor employee = em.find(Autor.class, 1);
		employee.setImie("Janusz");
		em.getTransaction().commit();
		employee = em.find(Autor.class, 1);
		assertEquals("Janusz",employee.getImie());

	}

	@Test
	@Order(4)
	public void checkDeleting() {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Autor employee = em.find(Autor.class, 1);
		em.remove(employee);
		em.getTransaction().commit();
		em.getTransaction().begin();
		List<Autor> list = autormanager.allAutors();
		em.getTransaction().commit();
		assertEquals(1, list.size());

	}

	@Test
	@Order(5)
	public void checkNamedQueryAutorAlbum() throws InterruptedException {
		EntityManager em = emf.createEntityManager();

		List<Autor> list = autormanager.allAutors();
		Query namedQuery = em.createNamedQuery("Autor.findAutorByID");
		namedQuery.setParameter("id",list.get(0).getId());
		List<Album> lista = namedQuery.getResultList();
		assertEquals(1, lista.size());



	}
}
