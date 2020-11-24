package batailleNavale;

public abstract class Joueur {
	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	public final static int GAMEOVER = 4;
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;

	// constructeur
	public Joueur(int tailleGrille, String nom) {
		this.tailleGrille = tailleGrille;
		this.nom = nom;
	}

	public Joueur(int tailleGrille) {
		this.tailleGrille = tailleGrille;
	}

	// getters
	public int getTailleGrille() {
		return this.tailleGrille;
	}

	public String getNom() {
		return this.nom;
	}

	// méthodes
	public void jouerAvec(Joueur j) {
		this.adversaire = j;
		j.adversaire = this;
		deroulementJeu(this, j);
	}
	
	private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
		int res = 0;
		while (res != GAMEOVER) {
		Coordonnee c = attaquant.choisirAttaque();
		res = defenseur.defendre(c);
		attaquant.retourAttaque(c, res);
		defenseur.retourDefense(c, res);
		Joueur x = attaquant;
		attaquant = defenseur;
		defenseur = x;
		}
		}
		protected abstract void retourAttaque(Coordonnee c, int etat);
		protected abstract void retourDefense(Coordonnee c, int etat);
		public abstract Coordonnee choisirAttaque();
		public abstract int defendre(Coordonnee c);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tailleDesNavires[] = { 2, 3, 4 };
		GrilleNavale g = new GrilleNavale(10, tailleDesNavires);
		GrilleNavale g2 = new GrilleNavale(10, tailleDesNavires);
		JoueurSmart jt1 = new JoueurSmart(g, "Sabrina");
		JoueurSmart jt2 = new JoueurSmart(g2, "Amine");
		jt1.jouerAvec(jt2);
		deroulementJeu(jt1, jt2);
		

	}

}
