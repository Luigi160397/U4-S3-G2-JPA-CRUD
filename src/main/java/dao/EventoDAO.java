package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import app.Application;
import entities.Evento;

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
		Application.logger.info("Evento salvato!");

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
			Application.logger.info("Evento con id " + id + " eliminato!");
		}
	}

	public void refresh(UUID id, String testo) {
		Evento found = em.find(Evento.class, id);
		found.setTitolo(testo);
		Application.logger.info("_________ Pre Refresh _________");
		Application.logger.info("" + found);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.refresh(found);
		transaction.commit();
		Application.logger.info("_________ Post Refresh _________ ");
		Application.logger.info("" + found);
	}

}
