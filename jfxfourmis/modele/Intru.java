package modele;

import java.awt.Point;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * classe representant une fourmi evoluant a la recherche de nourriture a l'aide
 * de pheromones
 */
public class Intru {
	/** position du Intru */
	Point p;
	/** direction du Intru */
	// private Direction direction;
	/** etat du Intru */
	private EtatIntru etat;
	/** lien vers le terrain dans lequel se trouve la fourmi */
	private Terrain terrain;
	/** taille du terrain */
	private int taille;
	/** taille d'un pas */
	private int pas;
	/** Direction de l'intru */
	private Direction dir;

	/** objet graphique associe a la fourmi */
	private Circle dessin;
	private Circle dessinVue;

	public Intru() {
	}

	/**
	 * construit un Intru
	 * 
	 * @param _x
	 *            coordonee x initiale du Intru
	 * @param _y
	 *            coordonee y initiale du Intru
	 * @param _terrain
	 *            terrain ou se trouve le Intru
	 */
	public Intru(int _x, int _y, Terrain _terrain) {
		p = new Point(_x, _y);
		terrain = _terrain;
		taille = terrain.getTaille();
		etat = EtatIntru.CACHER;
	}

	/** active les actions du Intru selon son etat */
	public void evoluer() {
		Cellule[][] grille = terrain.getGrille();
		int x = p.x;
		int y = p.y;
		switch (etat) {
		case TROUVER:

		case CACHER:

		}
	}

	/**
	 * fait avancer la fourmi dans sa direction si la case devant existe et est
	 * non occupee
	 */
	public void bougerVersDirection() {
		try {
			Cellule cell = getNextCellule(dir);

			if (cell != null && cell.getCaisse() != 1) {
				Cellule[][] grille = terrain.getGrille();
				grille[p.x][p.y].setRobot(false);
				p.x = cell.getX();
				p.y = cell.getY();
				dessin.setCenterX((p.x + 1.5) * pas);
				dessin.setCenterY((p.y + 1.5) * pas);
				dessinVue.setCenterX((p.x + 1.5) * pas);
				dessinVue.setCenterY((p.y + 1.5) * pas);
				// cell.setFourmis(true);
			} else if (cell.getCaisse() == 2) {

				Cellule[][] grille = terrain.getGrille();
				System.out.println(grille[p.x][p.y].getCaisse());
				// grille[p.x][p.y].setFourmis(false);
				p.x = cell.getX();
				p.y = cell.getY();
				dessin.setCenterX((p.x + 1.5) * pas);
				dessin.setCenterY((p.y + 1.5) * pas);
				dessin.setFill(Color.RED);
				dessinVue.setCenterX((p.x + 1.5) * pas);
				dessinVue.setCenterY((p.y + 1.5) * pas);
				System.out.println(grille[p.x][p.y].getCaisse());
				grille[p.x][p.y].setEmptyNow(true);
				grille[p.x][p.y].setCaisse(0);

			}
		} catch (NullPointerException e) {
			
		}
	}
	
	/**
	 * donne la prochaine case dans la direction donn�e
	 * 
	 * @param dir
	 *            la direction
	 * @return la cellule voisine dans la direction donn�e, null si aucune
	 *         cellule
	 */
	private Cellule getNextCellule(Direction dir) {
		Cellule cell = null;
		Point newPoint = Direction.getNextPoint(p, dir);
		if ((newPoint.x >= 0 && newPoint.x < taille) && (newPoint.y >= 0 && newPoint.y < taille)) {
			Cellule[][] grille = terrain.getGrille();
			cell = grille[newPoint.x][newPoint.y];
		}
		return cell;
	}

	/**
	 * @return the dessin
	 */
	public Circle getDessin() {
		return dessin;
	}

	/**
	 * @param dessin
	 *            the dessin to set
	 */
	public void setDessin(Circle dessin) {
		this.dessin = dessin;
	}

	public Circle getDessinVue() {
		return dessinVue;
	}

	/**
	 * @param dessin
	 *            the dessin to set
	 */
	public void setDessinVue(Circle dessin) {
		this.dessinVue = dessin;
	}

	/**
	 * @param pas
	 *            the pas to set
	 */
	public void setPas(int pas) {
		this.pas = pas;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public Point getPoint(){
		return p;
	}

}
