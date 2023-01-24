
package com.crud.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.crud.model.Client;
import com.crud.model.JPAUtil;

public class ClientDAO {
	EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

	// save Client
	public void save(Client client) {
		entity.getTransaction().begin();
		entity.persist(client);
		entity.getTransaction().commit();
	}

	// edit Client
	public void edit(Client client) {
		entity.getTransaction().begin();
		System.out.println("edit");
		entity.merge(client);
		System.out.println(client.getlName());
		entity.getTransaction().commit();
	}

	// search Client
	public Client search(Long id) {
		Client c = new Client();
		c = entity.find(Client.class, id);
		return c;
	}

	/// delete Client
	public void delete(Long id) {
		Client c = new Client();
		c = entity.find(Client.class, id);
		entity.getTransaction().begin();
		entity.remove(c);
		entity.getTransaction().commit();
	}

	// get all clients
	public List<Client> getClients() {
		List<Client> listaClients = new ArrayList<>();
		Query q = entity.createQuery("SELECT c FROM Client c");
		listaClients = q.getResultList();
		return listaClients;
	}

}