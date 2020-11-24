package batailleNavale;

import java.util.Random;

public class GrilleNavale {
	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	// Constructeurs
	public GrilleNavale(int taille, int[] taillesNavires) {
		if (taille <= 0 || taille > 26) {
			throw new IllegalArgumentException("taille doit etre compris entre 1 et 26");
		} else {
			this.taille = taille;
		}
		this.nbNavires = 0;
		this.navires = new Navire[taillesNavires.length];
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[taille * taille];
		placementAuto(taillesNavires);

	}

	public GrilleNavale(int taille, int nbNavires) {
		if (taille <= 0 || taille > 26) {
			throw new IllegalArgumentException("taille doit etre compris entre 1 et 26");
		} else {
			this.taille = taille;
		}
		if (this.nbNavires > taille * taille / 2) {
			throw new IllegalArgumentException("la grille ne peut acceuillir autant de navires!");
		}
		this.navires = new Navire[nbNavires];
		this.nbNavires = nbNavires;
		this.nbTirsRecus = 0;
		this.tirsRecus = new Coordonnee[taille * taille];
	}

	// méthodes

	public String toString() {
		String grille = new String();

		for (int i = 0; i <= this.taille; i++) {
			System.out.println();
			for (int j = 0; j <= this.taille; j++) {

				if (i == 0 && j == 0) {
					System.out.print("   ");
				} else if (i == 0) {
					System.out.print(Character.toString(64 + j) + " ");
				} else if (j == 0) {
					if (i < 10)
						System.out.print(" ");
					System.out.print(i);
				} else {
					Coordonnee c2 = new Coordonnee(i - 1, j - 1);
					boolean estUnNavire = false;
					for (int k = 0; k < this.nbNavires; k++) {
						if (this.navires[k].contient(c2)) {
							estUnNavire = true;
							break;
						}
					}
					if (estUnNavire) {
						if(this.estTouche(c2)){
							System.out.print(" X");
						}else
						System.out.print(" #");	
					} else if (this.estALEau(c2)) {
						System.out.print(" O");
					} else
						System.out.print(" .");

				}

			}

		}

		return grille;
	}

	public int getTaille() {
		return this.taille;
	}

	public boolean ajouteNavire(Navire n) {
		if (n.getFin().getColonne() > this.taille || n.getFin().getLigne() > this.taille)
			return false;

		if (n.getDebut().getLigne() < 0 || n.getDebut().getColonne() < 0)
			return false;

		for (int i = 0; i < this.nbNavires; i++) {
			if (this.navires[i].touche(n) || this.navires[i].chevauche(n))
				return false;
		}
		navires[this.nbNavires] = n;
		this.nbNavires++;
		return true;
	}

	public void placementAuto(int[] taillesNavires) {
		// placer les navires automatiquement:
		for (int i = 0; i < taillesNavires.length; i++) {
			Coordonnee debut = new Coordonnee((int) (Math.random() * (this.getTaille() - taillesNavires[i]) + 1),
					(int) (Math.random() * (this.getTaille() - taillesNavires[i]) + 1));
			// Coordonnee debut2 = new Coordonnee(debut.getLigne()+1, debut.getColonne()+1);
			int longueur = taillesNavires[i];
			Random rd = new Random();
			boolean sens = rd.nextBoolean();
			if (!(this.ajouteNavire(new Navire(debut, longueur, sens)))) {
				i--;
			}
			;
		}
	}

	private boolean estDansGrille(Coordonnee c) {
		if (c.getColonne() > this.taille || c.getLigne() > this.taille) {
			return false;
		}
		return true;
	}

	private boolean estDansTirsRecus(Coordonnee c) {
		if (!estDansGrille(c)) {
			return false;
		}
		// a completer
		for (int i = 0; i < nbTirsRecus; i++) {
			if (tirsRecus[i].equals(c))
				return true;
		}
		return false;
	}

	private boolean ajouteDansTirsRecus(Coordonnee c) {
		if (this.estDansTirsRecus(c))
			return false;
		this.tirsRecus[this.nbTirsRecus] = c;
		this.nbTirsRecus++;
		return true;
	}

	public boolean recoitTir(Coordonnee c) {
		if (this.ajouteDansTirsRecus(c)) {
			for (int i = 0; i < this.nbNavires; i++) {
				if (this.navires[i].recoitTir(c)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean estTouche(Coordonnee c) {
		for (int i = 0; i < this.nbNavires; i++) {
			if (this.navires[i].estTouche(c)) {
				return true;
			}
		}
		return false;
	}

	public boolean estALEau(Coordonnee c) {
		if (this.estDansGrille(c)) {
			if (!(estTouche(c)) && this.estDansTirsRecus(c)) {
				return true;
			}
		}
		return false;
	}

	public boolean estCoule(Coordonnee c) {
		for (int i = 0; i < this.nbNavires; i++) {
			if (this.navires[i].estTouche(c) && navires[i].estCoule())
				return true;
		}
		return false;
	}

	public boolean perdu() {
		for (int i = 0; i < this.nbNavires; i++) {
			if (!(navires[i].estCoule()))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tailleDesNavires[] = { 2, 3, 4 };
		GrilleNavale gn = new GrilleNavale(10, tailleDesNavires);
		// GrilleNavale gn = new GrilleNavale(10, 5);
		Coordonnee c1 = new Coordonnee(5, 6);
		Coordonnee c2 = new Coordonnee(1, 2);
		System.out.println("Tir c1 "+c1);
		System.out.println("Tir c2 "+c2);
		gn.recoitTir(c1);
		gn.recoitTir(c2);
		System.out.println(gn);

		for (int i = 0; i < tailleDesNavires.length; i++) {
			System.out.println("navire " + i + " " + gn.navires[i]);
		}

	}

}
