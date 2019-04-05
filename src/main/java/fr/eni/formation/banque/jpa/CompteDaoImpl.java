package fr.eni.formation.banque.jpa;

import java.time.LocalDate;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fr.eni.formation.banque.Compte;
import fr.eni.formation.banque.Credit;
import fr.eni.formation.banque.Debit;
import fr.eni.formation.banque.Operation;
import fr.eni.formation.banque.dao.CompteDao;


public class CompteDaoImpl implements CompteDao {
	private EntityManager em = JpaUtil.getEM();

	@Override
	public void create(Compte compte) {
				
		em.getTransaction().begin();

		em.persist(compte);
		
		em.getTransaction().commit();
	}

	@Override
	public Compte create(String numero, String intitule) {
		
		Compte compte = new Compte(numero,intitule);
		create(compte);
		
		return compte;
	}
	
	@Override
	public Stream<Compte> readAll() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Compte> query = builder.createQuery(Compte.class);
		
		Root<Compte> cpt = query.from(Compte.class);
		query.select(cpt);

		return em.createQuery(query).getResultStream();
	}

	@Override
	public Compte read(long id) {		
		return em.find(Compte.class, id);
	}
	
	@Override
	public Compte read(String numero) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Compte> query = builder.createQuery(Compte.class);
		
		Root<Compte> cpt = query.from(Compte.class);
		query.where(builder.equal(cpt.get("numero"), builder.parameter(String.class,"numero"))).select(cpt);
		
		// String jpql = "from Compte as cpt where cpt.numero like :numero";
		TypedQuery<Compte> jpql = em.createQuery(query);
		jpql.setParameter("numero", numero);
		return jpql.getSingleResult();
	}

	
	@Override
	public void virement(Compte orig, Compte dest, String libelle, double montant) {
		
		Operation debit = new Debit(LocalDate.now(), libelle, montant);
		Operation credit = new Credit(LocalDate.now(), libelle, montant);
		
		em.getTransaction().begin();

		orig.getOperations().add(debit);
		dest.getOperations().add(credit);

		em.getTransaction().commit();
		
//		em.refresh(orig);
		em.refresh(dest);
	}

	
}
