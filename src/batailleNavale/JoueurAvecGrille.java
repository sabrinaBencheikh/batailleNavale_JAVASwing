package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur{
	private GrilleNavale grille;
	
	//constructeurs
	public JoueurAvecGrille(GrilleNavale g, String nom) {
		super(g.getTaille(), nom);
		this.grille = g;	
	}
	public JoueurAvecGrille(GrilleNavale g) {
		super(g.getTaille());
		this.grille = g;
	}
	
	 //méthodes
	public int defendre(Coordonnee c) {
		
		this.grille.recoitTir(c);
		System.out.println(this.grille);
		int res =0 ;
		if(this.grille.estTouche(c)) {
			res = TOUCHE;
		}if (this.grille.estCoule(c)) {
			res = COULE;
		}if (this.grille.estALEau(c)) {
			res = A_L_EAU;
		}if (this.grille.perdu()){
			res = GAMEOVER;
		}
		return res;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
