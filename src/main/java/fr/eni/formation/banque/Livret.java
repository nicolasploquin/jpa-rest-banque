package fr.eni.formation.banque;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="idCompte")
public class Livret extends Compte {
	
	@Column(nullable=true)
	private double taux;

	public Livret() {
	}

	public Livret(String numero, String intitule, double taux) {
		super(numero, intitule);
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return super.toString() + " (Livret)";
	}
}
