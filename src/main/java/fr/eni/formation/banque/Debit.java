package fr.eni.formation.banque;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Debit")
public class Debit extends Operation {

	public Debit() {
		
	}

	public Debit(LocalDate date, String libelle, double montant) {
		super(date, libelle, montant);
	}

	@Override
	public double getMontantRelatif() {
		return - getMontant();
	}
	
}
