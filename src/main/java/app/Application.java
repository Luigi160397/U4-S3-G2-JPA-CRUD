package app;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.EventoDAO;
import entities.Evento;
import entities.TipoEvento;
import utils.JpaUtil;

public class Application {

	public static Logger logger = LoggerFactory.getLogger(Application.class);
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EventoDAO ed = new EventoDAO(em);

		Evento e1 = new Evento("Compleanno", LocalDate.of(2000, 8, 15), "bello", TipoEvento.PRIVATO, 1000);

		ed.save(e1);

		UUID id = UUID.fromString("0485c570-8046-4252-ae66-8c9e57749ac0");

		Evento trovato = ed.getById(id);

		ed.delete(UUID.fromString("43043b8b-f586-4519-8e65-46afb9329e01"));

		if (trovato != null) {

			logger.info("" + trovato);
		} else {
			logger.info("Evento non trovato con id: " + id);
		}

		ed.refresh(id, "Test");

		em.close();
		emf.close();
	}

}
