package modele;

import javafx.scene.shape.Circle;

public class Caisse {

	private int x;
	private int y;
	private int classe;
	
	private Circle dessin;
	
	
	
	public Caisse(int x, int y, int classe)
	{
		this.x = x;
		this.y = y;
		this.classe = classe;
	}
	
	/**
	 * @return the dessin
	 */
	public Circle getDessin() {
		return dessin;
	}

	/**
	 * @param dessin the dessin to set
	 */
	public void setDessin(Circle dessin) {
		this.dessin = dessin;
	}
}
