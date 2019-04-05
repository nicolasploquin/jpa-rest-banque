package fr.eni.formation.banque;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	
	private String rue;
	
	private String cp;
	
	private String ville;
	
	public Adresse() {

	}

	public Adresse(String rue, String cp, String ville) {
		super();
		this.rue = rue;
		this.cp = cp;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return String.format("Adresse [rue=%s, cp=%s, ville=%s]", rue, cp, ville);
	}
	
	

}
