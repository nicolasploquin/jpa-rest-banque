package fr.eni.formation.banque;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
public abstract class Operation {
	
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idOperation;
	
	private LocalDate date;
	
	private String libelle;

	@Column(nullable=true)
	private double montant;
	
	public Operation() {
		
	}

	public Operation(LocalDate date, String libelle, double montant) {
		super();
		this.date = date;
		this.libelle = libelle;
		this.montant = montant;
	}

	public long getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(long idOperation) {
		this.idOperation = idOperation;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		if(montant < 0.0) montant = -montant;
		this.montant = montant;
	}
	
	public abstract double getMontantRelatif();	

	@Override
	public String toString() {
		return String.format("Operation [idOperation=%s, date=%s, libelle=%s, montant=%s]", idOperation, date, libelle,
				montant);
	}

	

}
