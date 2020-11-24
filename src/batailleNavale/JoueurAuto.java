package batailleNavale;

public class JoueurAuto extends JoueurAvecGrille{

	//constructeurs
	public JoueurAuto(GrilleNavale g, String nom) {
		super(g, nom);
		
	}
	public JoueurAuto(GrilleNavale g) {
		super(g);
		
	}
	 //méthodes
	protected void retourAttaque(Coordonnee c, int etat) {
		if (etat == TOUCHE) {
			System.out.println(this.getNom() +" Vous avez touché un navire en " + c);
		}
		if (etat == COULE) {
			System.out.println(this.getNom() +" Vous avez coulé un navire");
		}
		if (etat == A_L_EAU) {
			System.out.println(this.getNom() +" Votre tire est tombé à l'eau");
		}
		if (etat == GAMEOVER) {
			System.out.println(this.getNom() +" Vous avez gagné!");
		}
	}
	protected void retourDefense(Coordonnee c, int etat) {
		if (etat == TOUCHE) {
			System.out.println(this.getNom() + " un de vos navire a été touché en " + c);
		}
		if (etat == COULE) {
			System.out.println(this.getNom() +" un de vos navire a coulé");
		}
		if (etat == A_L_EAU) {
			System.out.println(this.getNom() +" le tire est tombé à l'eau");
		}
		if (etat == GAMEOVER) {
			System.out.println(this.getNom() +" Vous avez perdu...");
		}
	}
	public Coordonnee choisirAttaque() {
		Coordonnee c = new Coordonnee((int) (Math.random() * this.getTailleGrille()),
				(int) (Math.random() * this.getTailleGrille()));
		return c;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
