package batailleNavale;

public class JoueurSmart extends JoueurAuto{
	private Coordonnee []tirEmis;
	private int nbTirEmis;
	
	public JoueurSmart(GrilleNavale g, String nom) {
		super(g, nom);
		this.tirEmis = new Coordonnee[this.getTailleGrille()*this.getTailleGrille()];
		this.nbTirEmis=0;
		
	}
	public JoueurSmart(GrilleNavale g) {
		super(g);
		this.tirEmis = new Coordonnee[this.getTailleGrille()*this.getTailleGrille()];
		this.nbTirEmis=0;
	}
	
	public Coordonnee choisirAttaque() {
		Coordonnee c = new Coordonnee((int) (Math.random() * this.getTailleGrille()),
				(int) (Math.random() * this.getTailleGrille()));
		c = verifierCoordonnee(c);
		this.tirEmis[this.nbTirEmis] =c;
		this.nbTirEmis++;
		return c;
	}
	
	public Coordonnee verifierCoordonnee(Coordonnee c) {
		boolean estDejaTouche = false;
		for(int i =0; i<this.nbTirEmis; i++) {
			if(this.tirEmis[i].equals(c)) {
				estDejaTouche = true;
				break;
			}				
		}
		while(estDejaTouche) {
			c = new Coordonnee((int) (Math.random() * this.getTailleGrille()),
					(int) (Math.random() * this.getTailleGrille()));
			estDejaTouche = false;
			for(int i =0; i<this.nbTirEmis; i++) {
				if(this.tirEmis[i].equals(c)) {
					estDejaTouche = true;
					break;
				}
					
			}
		}
		return c;
	}

}
