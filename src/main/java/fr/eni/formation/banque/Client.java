package fr.eni.formation.banque;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client implements Serializable {
	
	private static final long serialVersionUID = 7165583267648276045L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idClient;
	
	private String nom;
	
	private String prenom;
	
	@Embedded
	private Adresse adresse;
	
//	@OneToMany(mappedBy="titulaire", cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
//	private Set<Compte> comptes = new HashSet<>();

//	@ManyToMany
//	@JoinTable(
//		name="jointure_client_compte",
//		joinColumns= {@JoinColumn(name="idClient")},
//		inverseJoinColumns= {@JoinColumn(name="idCompte")}
//	)
//	private Set<Compte> comptes = new HashSet<>();
	
	public Client() {
		super();
	}
	
	public Client(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public long getIdClient() {
		return idClient;
	}
	
	public void setIdClient(long id) {
		this.idClient = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

//	public Set<Compte> getComptes() {
//		return comptes;
//	}
//
//	public void setComptes(Set<Compte> comptes) {
//		this.comptes = comptes;
//	}
	
	public void addCompte(Compte compte) {
		compte.setTitulaire(this);
	}
	public void removeCompte(Compte compte) {
		compte.setTitulaire(null);
	}

	@Override
	public String toString() {
		return String.format("Client %-10s %-10s (%3d - %2d comptes)", nom, prenom, idClient, 0 /*comptes.size()*/);
	}

	
	
	
}
