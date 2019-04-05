package fr.eni.formation.banque.dao;

import java.util.stream.Stream;

import fr.eni.formation.banque.Compte;

public interface CompteDao {
	
	void create(Compte compte);
	Compte create(String numero, String intitule);
	
	Stream<Compte> readAll();
	
	Compte read(long id);
	public Compte read(String numero);
	
	void virement(Compte orig, Compte dest, String libelle, double montant);

}
