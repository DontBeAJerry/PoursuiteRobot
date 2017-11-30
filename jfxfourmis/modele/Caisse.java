package modele;

import javafx.scene.shape.Rectangle;

public class Caisse {

	private int x;
	private int y;
	private TypeCaisse classe;
	
	private Rectangle dessin;
	
	
	
	public Caisse(int x, int y, TypeCaisse classe)
	{
		this.x = x;
		this.y = y;
		this.classe = classe;
	}
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public TypeCaisse getClasse() {
		return classe;
	}

	/**
	 * @return the dessin
	 */
	public Rectangle getDessin() {
		return dessin;
	}

	/**
	 * @param dessin the dessin to set
	 */
	public void setDessin(Rectangle dessin) {
		this.dessin = dessin;
	}
	
}
