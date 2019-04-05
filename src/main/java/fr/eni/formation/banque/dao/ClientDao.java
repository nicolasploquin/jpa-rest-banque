package fr.eni.formation.banque.dao;

import java.util.stream.Stream;

import javax.transaction.Transactional;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.Compte;

public interface ClientDao {
	
	@Transactional(Transactional.TxType.REQUIRED)
	Client create(String nom, String prenom);

	@Transactional(Transactional.TxType.SUPPORTS)
	Stream<Client> readAll();

	@Transactional(Transactional.TxType.SUPPORTS)
	Client read(long id);
	
	@Transactional(Transactional.TxType.SUPPORTS)
	Stream<Client> read(String nom);
	
	@Transactional(Transactional.TxType.REQUIRED)
	void delete(Client client);

	@Transactional(Transactional.TxType.REQUIRED)
	void delete(long id);
	
	@Transactional(Transactional.TxType.REQUIRED)
	void addCompte(Client titulaire, Compte compte);

}
