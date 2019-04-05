package fr.eni.formation.banque.jpa;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.dao.ClientDao;

@Stateless
public class ClientDaoImpl implements ClientDao {
	
	
	@PersistenceContext(unitName="localhost-mysql-banque-jpa")
	private EntityManager em;
	
	public ClientDaoImpl() {}
	
	@PostConstruct
	private void init() {
		create("Ainslie", "Ben");
		create("Scheidt", "Robert");
	}
	
	
	@Override
	public Client create(String nom, String prenom) {

		Client client = new Client(nom,prenom);
		em.persist(client);
		
		return client;
	}

	@Override
	public Stream<Client> readAll() {
		return em.createQuery("from Client").getResultList().stream();
	}
	
	@Override
	public Client read(long id) {		
		return em.find(Client.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Stream<Client> read(String nom) {
		
		String jpql = "from Client as cli where cli.nom like :nom";
		Query query = em.createQuery(jpql);
		query.setParameter("nom", "%"+nom+"%");
		
		return query.getResultList().stream();
	}

	@Override
	public void delete(Client client) {
		em.remove(client);
	}

	@Override
	public void delete(long id) {
		em.remove(read(id));
	}
	
	@Override
	public void addCompte(Client titulaire, Compte compte) {
		compte.setTitulaire(titulaire);
		em.refresh(titulaire);
	}

}
