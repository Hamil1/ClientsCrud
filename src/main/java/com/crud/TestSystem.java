package com.crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.crud.model.*;


public class TestSystem {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("clientsCrud");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addClient("Jossias", "Velasquez", "8494032354");
		ENTITY_MANAGER_FACTORY.close();

	}

	public static void addClient(String fName, String lName, String address) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();
			Client client = new Client();
			client.setfName(fName);
			client.setlName(lName);
			client.setAddress(address);
			em.persist(client);
			et.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void getClient(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c from Customer c WHERE c.id = :client_id";
		TypedQuery<Client> tq = em.createQuery(query, Client.class);
		tq.setParameter("client_id", id);
		Client client = null;
		try {
			client = tq.getSingleResult();
			System.out.println(client.getfName() + " " + client.getlName());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	
	public static void getClients() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c from CUSTOMER c WHERE c.id IS NOT NULL";
		TypedQuery<Client> tq = em.createQuery(query, Client.class);
		List<Client> clients;
		try {
			clients = tq.getResultList();
			clients.stream().forEach(client -> System.out.println(client.getfName() + " " + client.getlName()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void changeFName(int id, String fName) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Client client = null;
		try {
			et = em.getTransaction();
			et.begin();
			client = em.find(Client.class, id);
			client.setfName(fName);
			em.persist(client);
			et.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void deleteClient(int id, String fName) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Client client = null;
		try {
			et = em.getTransaction();
			et.begin();
			client = em.find(Client.class, id);
			em.remove(client);
			em.persist(client);
			et.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (et != null) {
				et.rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
