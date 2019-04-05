package fr.eni.formation.banque;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Credit")
public class Credit extends Operation {

	public Credit() {
		
	}

	public Credit(LocalDate date, String libelle, double montant) {
		super(date, libelle, montant);
	}

	@Override
	public double getMontantRelatif() {
		return getMontant();
	}
	
}
