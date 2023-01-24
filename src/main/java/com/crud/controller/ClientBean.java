package com.crud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.crud.dao.ClientDAO;
import com.crud.model.Client;

@ManagedBean(name = "ClientBean")
@RequestScoped
public class ClientBean {
	
	public String newClient() {
		Client c= new Client();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("Client", c);
		return  "/faces/newClient.xhtml";
	}
	
	public String save (Client Client) {
		
		ClientDAO ClientDAO= new ClientDAO();
		ClientDAO.save(Client);
		return  "/faces/index.xhtml";
	}

	public List<Client> getClients() {
		ClientDAO clientDAO = new ClientDAO();

		/*
		 * List<Client> listaClients = new ArrayList<>(); Client c1 = new Client();
		 * c1.setId(1L); c1.setNombres("Elivar"); c1.setApellidos("Largo");
		 * c1.setDireccion("Loja");
		 * 
		 * Client c2 = new Client(); c2.setId(1L); c2.setNombres("Elivar1");
		 * c2.setApellidos("Largo1"); c2.setDireccion("Loja1"); listaClients.add(c1);
		 * listaClients.add(c2);
		 * 
		 * return listaClients;
		 */
//		List<Client> listClients = new ArrayList<>();
//		Client c1 = new Client();
//		c1.setAddress("hola");
//		c1.setfName("hamil");
//		c1.setlName("diaz");
//		listClients.add(c1);
//		return listClients;
//		System.out.print(ClientDAO.getClients());
		return clientDAO.getClients();
	}

	public String editById(Long id) {
		ClientDAO clientDAO = new ClientDAO();
		Client c = new Client();
		c = clientDAO.search(id);
		System.out.println("******************************************");
		System.out.println(c);

		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("Client", c);
		return "/faces/edit.xhtml";
	}

	public String edit(Client Client) {
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.edit(Client);
		return "/faces/index.xhtml";
	}

	// delete Client
	public String delete(Long id) {
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.delete(id);
		System.out.println("Client deleted...");
		return "/faces/index.xhtml";
	}

}