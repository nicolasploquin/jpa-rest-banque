package fr.eni.formation.banque;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Compte {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCompte;
	
	@Column(nullable=false, unique=true)
	private String numero;
	
	private String intitule;
	
	@ManyToOne
	@JoinColumn(name="idClient")
	private Client titulaire;
	
//	@ManyToMany(mappedBy="comptes")
//	private Set<Client> titulaire;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idCompte")
	private List<Operation> operations = new ArrayList<>();
	
	@Transient
	private double solde;
	
	public Compte() {
	}
	
	public Compte(String numero, String intitule) {
		super();
		this.numero = numero;
		this.intitule = intitule;
	}

	public long getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(long idCompte) {
		this.idCompte = idCompte;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Client getTitulaire() {
		return titulaire;
	}

	public void setTitulaire(Client titulaire) {
		// Le compte appartient déjà à un client, on lui retire
		if(this.titulaire != null) {
//			this.titulaire.getComptes().remove(this);
		}

		this.titulaire = titulaire;
		
		// Le compte appartient à un nouveau titulaire, on lui ajoute
		if(titulaire != null) {
//			this.titulaire.getComptes().add(this);			
		}
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	/**
	 *  Calcul du solde après chaque select dans la base
	 */
//	@PrePersist 
//	@PreUpdate 
//	@PostPersist 
//	@PostUpdate 
	@PostLoad
	public void majSolde() {
		solde = operations.stream().mapToDouble(ope -> ope.getMontantRelatif()).sum();
	}
	
	

	@Override
	public String toString() {
		return String.format(
				"Compte n°%s(%d) : %10.2f - %-20s - %s", 
				numero, idCompte, solde, intitule, 
				titulaire!=null ? titulaire.getNom() : "inconnu"
		);
	}
	
}
