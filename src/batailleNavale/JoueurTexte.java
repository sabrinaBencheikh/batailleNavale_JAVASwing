package batailleNavale;

import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille {

	private Scanner sc;

	// constructeurs
	public JoueurTexte(GrilleNavale g, String nom) {
		super(g, nom);
		this.sc = new Scanner(System.in);
	}

	public JoueurTexte(GrilleNavale g) {
		super(g);
		this.sc = new Scanner(System.in);
	}

	// méthodes
	protected void retourAttaque(Coordonnee c, int etat) {

		if (etat == TOUCHE) {
			System.out.println("JoueurTexte: Vous avez touché un navire en " + c);
		}
		if (etat == COULE) {
			System.out.println("JoueurTexte: Vous avez coulé un navire");
		}
		if (etat == A_L_EAU) {
			System.out.println("JoueurTexte: Votre tire est tombé à l'eau");
		}
		if (etat == GAMEOVER) {
			System.out.println("JoueurTexte: Vous avez gagné!");
		}
	}

	protected void retourDefense(Coordonnee c, int etat) {
		if (etat == TOUCHE) {
			System.out.println("JoueurTexte: un de vos navire a été touché en " + c);
		}
		if (etat == COULE) {
			System.out.println("JoueurTexte: un de vos navire a coulé");
		}
		if (etat == A_L_EAU) {
			System.out.println("JoueurTexte: le tire est tombé à l'eau");
		}
		if (etat == GAMEOVER) {
			System.out.println("JoueurTexte: Vous avez perdu...");
		}

	}

	public Coordonnee choisirAttaque() {
		System.out.println("entrez le coordonnee sous forme (A1)");
		String coord = sc.nextLine();
		Coordonnee c = new Coordonnee(coord);
		return c;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
	}

}
