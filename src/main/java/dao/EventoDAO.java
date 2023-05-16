package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Evento;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class EventoDAO {
	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		log.info("Evento salvato!");

	}

	public Evento getById(UUID id) {

		Evento found = em.find(Evento.class, id);
		return found;
	}

	public void delete(UUID id) {
		Evento found = em.find(Evento.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			log.info("Evento con id " + id + " eliminato!");
		} else {
			log.info("Evento con id " + id + " non trovato!");
		}
	}

	public void refresh(UUID id, String testo) {
		Evento found = em.find(Evento.class, id);
		found.setTitolo(testo);
		log.info("_________ Pre Refresh _________");
		log.info("" + found);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.refresh(found);
		transaction.commit();
		log.info("_________ Post Refresh _________ ");
		log.info("" + found);
	}

}
