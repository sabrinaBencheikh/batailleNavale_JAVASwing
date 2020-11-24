package batailleNavale;

public class JoueurSuperSmart extends JoueurSmart{
	private GrilleNavale grille;
	private Coordonnee dernierTir;
	
	
	public JoueurSuperSmart(GrilleNavale g, String nom) {
		super(g,nom);
		this.grille = g;
	}
	public JoueurSuperSmart(GrilleNavale g) {
		super(g);
		this.grille = g;
	}
	
	public Coordonnee coulerNavire() {
		Coordonnee c = new Coordonnee((int) (Math.random() * this.getTailleGrille()),
				(int) (Math.random() * this.getTailleGrille()));
		c = this.choisirAttaque();
		boolean toucheNavire = this.grille.estTouche(c);
		if(toucheNavire) {
			this.dernierTir = c;
		}
		
	}
}
