package modele;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Caisse {

	private int x;
	private int y;
	private int classe;
	
	private Rectangle dessin;
	
	
	
	public Caisse(int x, int y, int classe)
	{
		this.x = x;
		this.y = y;
		this.classe = classe;
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
