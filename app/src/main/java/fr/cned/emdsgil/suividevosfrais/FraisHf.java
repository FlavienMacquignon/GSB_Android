package fr.cned.emdsgil.suividevosfrais;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe métier contenant la description d'un frais hors forfait
 *
 */
class FraisHf  implements Serializable {

	private final Float montant ;
	private final String motif ;
	private final Integer jour ;
	
	public FraisHf(Float montant, String motif, Integer jour) {
		this.montant = montant ;
		this.motif = motif ;
		this.jour = jour ;
	}

	public Float getMontant() {
		return montant;
	}

	public String getMotif() {
		return motif;
	}

	public Integer getJour() {
		return jour;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FraisHf fraisHf = (FraisHf) o;
		return Objects.equals(montant, fraisHf.montant) &&
				Objects.equals(motif, fraisHf.motif) &&
				Objects.equals(jour, fraisHf.jour);
	}

	@Override
	public int hashCode() {
		return Objects.hash(montant, motif, jour);
	}
}
