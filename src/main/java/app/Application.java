package app;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.EventoDAO;
import entities.Evento;
import entities.TipoEvento;
import lombok.extern.slf4j.Slf4j;
import utils.JpaUtil;

@Slf4j

public class Application {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EventoDAO ed = new EventoDAO(em);

		Evento e1 = new Evento("FestivalBar", LocalDate.of(2000, 8, 15), "bello", TipoEvento.PUBBLICO, 1000);

		ed.save(e1);

		UUID id = UUID.fromString("08157a01-3067-48c2-b9b0-af6dc4be88d4");

		Evento trovato = ed.getById(id);

		ed.delete(UUID.fromString("401faecd-8d23-4345-b6e0-53da813cee43"));

		if (trovato != null) {
			log.info("_________ Evento trovato: _________");
			log.info("" + trovato);
		} else {
			log.info("Evento non trovato con id: " + id);
		}

		ed.refresh(id, "Test");

		em.close();
		emf.close();
	}

}
