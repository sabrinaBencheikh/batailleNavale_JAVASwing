package batailleNavale;

public class Coordonnee {
	private int ligne;
	private int colonne;

	// constructeurs
	public Coordonnee(int ligne, int colonne) {
		if(ligne <0 || colonne <0 )
			throw new IllegalArgumentException("les coordonnées doivent etre dans les limites de la grille");
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public Coordonnee(String s) {
		
		// corriger toUpperCase
		this.colonne = s.toUpperCase().charAt(0) - 'A';
		this.ligne = Integer.parseInt("" + s.charAt(1)) - 1;
		
	}

	// methode toString

	public String toString() {
		String col = Character.toString(65 + this.colonne);
		String lig = "" + (this.ligne + 1);
		return col + lig;
	}

	// accesseurs
	public int getColonne() {
		return this.colonne;
	}

	public int getLigne() {
		return this.ligne;
	}

	//
	public boolean equals(Object obj) {
		
		if (obj instanceof Coordonnee) {
			 Coordonnee obj2 = (Coordonnee)obj;
			if( obj2.colonne == this.colonne && obj2.ligne == this.ligne){
				return true;
			}
		}	
		return false;
	}

	public boolean voisine(Coordonnee c) {
		if (c.colonne == this.colonne + 1 && c.ligne == this.ligne)
			return true;
		if (c.colonne == this.colonne - 1 && c.ligne == this.ligne)
			return true;
		if (c.ligne == this.ligne + 1 && c.colonne == this.colonne)
			return true;
		if (c.ligne == this.ligne - 1 && c.colonne == this.colonne)
			return true;

		return false;
	}

	public int compareTo(Coordonnee c) {
		if (c.ligne == this.ligne) {
			return this.colonne - c.colonne;
		}
		return this.ligne - c.ligne;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Coordonnee essay = new Coordonnee(4, 3);
		Coordonnee chaine = new Coordonnee ("c1");
		Coordonnee essay2 = new Coordonnee(2, 3);
		System.out.println(chaine);
		//System.out.println(chaine);
		// System.out.println(Character.toString(65+1));
		// System.out.println("ligne -----" + essay.getLigne());
		// System.out.println("colonne -----" + essay.getColonne());

		//System.out.println(essay.voisine(essay2));
		//System.out.println(essay.compareTo(essay2));
		//System.out.println(essay.equals(essay2));
	}

}
