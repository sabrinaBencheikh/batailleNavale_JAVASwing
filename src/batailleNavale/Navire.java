package batailleNavale;

public class Navire {
	// attributs
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	// constructeurs

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		if (longueur > 26 || longueur <= 0)
			throw new IllegalArgumentException();

		this.debut = debut;
		if (estVertical) {
			this.fin = new Coordonnee((this.debut.getLigne() + longueur - 1), this.debut.getColonne());
		} else {
			this.fin = new Coordonnee(this.debut.getLigne(), (this.debut.getColonne() + longueur - 1));
		}
		this.nbTouchees = 0;
		this.partiesTouchees = new Coordonnee[longueur];

	}

	// methodes

	public String toString() {
		String sens = "";
		int longueur = 0;
		if (this.debut.getLigne() == this.fin.getLigne()) {
			sens = "Horizontal";
			longueur = this.fin.getColonne() - this.debut.getColonne() + 1;
		} else if (this.debut.getColonne() == this.fin.getColonne()) {
			sens = "Vertical";
			longueur = this.fin.getLigne() - this.debut.getLigne() + 1;

		}

		return "Navire (" + this.debut + " , " + longueur + " , " + sens + ")";
	}

	// Accesseur
	public Coordonnee getDebut() {
		return this.debut;
	}

	public Coordonnee getFin() {
		return this.fin;
	}

	public boolean contient(Coordonnee c) {

		if (this.debut.getLigne() == this.fin.getLigne()) {
			for (int i = this.debut.getColonne(); i <= this.fin.getColonne(); i++) {
				Coordonnee cn = new Coordonnee(this.debut.getLigne(), i);
				if (cn.equals(c)) {
					return true;
				}
			}
		}
		if (this.debut.getColonne() == this.fin.getColonne()) {
			for (int i = this.debut.getLigne(); i <= this.fin.getLigne(); i++) {
				Coordonnee cn = new Coordonnee(i, this.debut.getColonne());
				if (cn.equals(c)) {
					return true;
				}
			}
		}
		return false;
		
	}

	public boolean touche(Navire n) {
		int c1Colonne = this.debut.getColonne();
		int c1Ligne = this.debut.getLigne();
		Coordonnee c1 = new Coordonnee(c1Ligne, c1Colonne);

		if (this.chevauche(n))
			return false;

		if (this.debut.getLigne() == this.fin.getLigne()) {
			while (c1Colonne <= this.fin.getColonne()) {
				int c2Colonne = n.getDebut().getColonne();
				int c2Ligne = n.getDebut().getLigne();
				Coordonnee c2 = new Coordonnee(c2Ligne, c2Colonne);

				if (n.getDebut().getLigne() == n.getFin().getLigne()) {
					while (c2Colonne <= n.getFin().getColonne()) {
						if (c1.voisine(c2)) {
							return true;
						}
						c2Colonne++;
						c2 = new Coordonnee(c2Ligne, c2Colonne);
					}
				}
				if (n.getDebut().getColonne() == n.getFin().getColonne()) {
					while (c2Ligne <= n.getFin().getLigne()) {
						if (c1.voisine(c2)) {
							return true;
						}
						c2Ligne++;
						c2 = new Coordonnee(c2Ligne, c2Colonne);
					}
				}
				c1Colonne++;
				c1 = new Coordonnee(c1Ligne, c1Colonne);
			}
		}
		if (this.debut.getColonne() == this.fin.getColonne()) {
			while (c1Ligne <= this.getFin().getLigne()) {
				int c2Colonne = n.getDebut().getColonne();
				int c2Ligne = n.getDebut().getLigne();
				Coordonnee c2 = new Coordonnee(c2Ligne, c2Colonne);

				if (n.getDebut().getLigne() == n.getFin().getLigne()) {
					while (c2Colonne <= n.getFin().getColonne()) {
						if (c1.voisine(c2)) {
							return true;
						}
						c2Colonne++;
						c2 = new Coordonnee(c2Ligne, c2Colonne);
					}
				}
				if (n.getDebut().getColonne() == n.getFin().getColonne()) {
					while (c2Ligne <= n.getFin().getLigne()) {
						if (c1.voisine(c2)) {
							return true;
						}
						c2Ligne++;
						c2 = new Coordonnee(c2Ligne, c2Colonne);
					}
				}
				c1Ligne++;
				c1 = new Coordonnee(c1Ligne, c1Colonne);
			}
		}
		return false;
	}

	public boolean chevauche(Navire n) {
		if (this.debut.getLigne() == this.fin.getLigne()) {
			for (int i = this.debut.getColonne(); i <= this.fin.getColonne(); i++) {
				if (n.getDebut().getLigne() == n.getFin().getLigne()) {
					for (int j = n.getDebut().getColonne(); j <= n.getFin().getColonne(); j++) {
						Coordonnee cn = new Coordonnee(n.getDebut().getLigne(), j);
						if (this.contient(cn)) {
							return true;
						}
					}
				} else if (n.getDebut().getColonne() == n.getFin().getColonne()) {
					for (int j = n.getDebut().getLigne(); j <= n.getFin().getLigne(); j++) {
						Coordonnee cn = new Coordonnee(j, n.getDebut().getColonne());
						if (this.contient(cn))
							return true;
					}
				}
			}
		} else if (this.debut.getColonne() == this.fin.getColonne()) {
			for (int i = this.debut.getLigne(); i <= this.fin.getLigne(); i++) {
				if (n.getDebut().getLigne() == n.getFin().getLigne()) {
					for (int j = n.getDebut().getColonne(); j <= n.getFin().getColonne(); j++) {
						Coordonnee cn = new Coordonnee(n.getDebut().getLigne(), j);
						if (this.contient(cn)) {
							return true;
						}
					}
				} else if (n.getDebut().getColonne() == n.getFin().getColonne()) {
					for (int j = n.getDebut().getLigne(); j <= n.getFin().getLigne(); j++) {
						Coordonnee cn = new Coordonnee(j, n.getDebut().getColonne());
						if (this.contient(cn))
							return true;
					}
				}
			}
		}
		return false;
	}

	public boolean recoitTir(Coordonnee c) {

		if (this.contient(c)) {
				if(this.estTouche(c))
					return true;
				this.partiesTouchees[this.nbTouchees] = c;
				this.nbTouchees++;
				return true;
		}
		return false;
	}

	public boolean estTouche(Coordonnee c) {
		for (int i = 0; i < this.nbTouchees; i++) {
			if (this.partiesTouchees[i].equals(c)) {
				return true;
			}
		}
		return false;
	}

	public boolean estTouche() {
		if (this.nbTouchees > 0) {
			return true;
		}
		return false;
	}

	public boolean estCoule() {
		if(this.nbTouchees == this.partiesTouchees.length)
			return true;

		return false;
	}

	public static void main(String[] args) {

		// Navire n1 = new Navire(new Coordonnee(1, 5), 6, false);
		Navire n2 = new Navire(new Coordonnee(1, 1), 3, false);
		Coordonnee c = new Coordonnee(1, 1);
		Coordonnee c2 = new Coordonnee(1, 2);
		Coordonnee c3 = new Coordonnee(1, 3);
		/*
		 * System.out.println("n1" + n1); System.out.println("debut n1 " +
		 * n1.getDebut()); System.out.println("fin n1 " + n1.getFin());
		 */
		System.out.println("n2" + n2);
		System.out.println("debut n2 " + n2.getDebut());
		System.out.println("fin n2 " + n2.getFin());

		System.out.println("n contient c: " + n2.contient(c));
		/*
		 * System.out.println("n1 touche n2: " + n1.touche(n2));
		 * System.out.println("n1 chevauche n2: "+ n1.chevauche(n2));
		 */
		System.out.println("n2 recoitTir en c: " + n2.recoitTir(c));
		System.out.println("n2 recoitTir en c2: " + n2.recoitTir(c2));
		System.out.println("n2 recoitTir en c3: "+ n2.recoitTir(c3));
		System.out.println("n est touché: " + n2.estTouche());
		System.out.println("n est touché en c: " + n2.estTouche(c));
		System.out.println("n est touché en c2: " + n2.estTouche(c2));
		System.out.println("n est touché en c3: "+ n2.estTouche(c3));
		System.out.println("n est coulé: " + n2.estCoule());
	}

}
