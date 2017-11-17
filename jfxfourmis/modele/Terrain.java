package modele;

/**terrain des cellules de la simulation de fourmis
 * @author Emmanuel Adam*/
public class Terrain {
	/** grille a l'instant t*/
	private Cellule [][] grille;
	/** taille de la grille*/
	private int taille;
	/** nombre de fourmis */
	private int nbFourmis = 50;
	/**tableau des fourmis*/
	private Fourmi[] lesFourmis;
	private Robot[] lesRobots;
	private Intru intru;
	private int nbCaisse = 5;
	/**coordonnee x du nid*/
	private int xNid;
	/**coordonnee y du nid*/
	private int yNid;
	/**odeur degagee par le nid*/
	private  final double odeurNid = 40d;

	public Terrain()
	{
		grille = new  Cellule[20][20];
		taille = 20;
	}

	/** constructeur par defaut, initialise la taille, le nombre de cellules initiales,  
    ainsi que les grilles a l'instnat t et t-1*/
	public Terrain(int taille, int _nbFourmis)
	{
		this.taille= taille;
		grille = new Cellule[taille][taille];
		this.nbFourmis = _nbFourmis ;
		init();
		
		xNid = taille/2;
		yNid = taille/2;
		//initNid();
		initRobots(nbFourmis);
		initIntru();
	//	initCaisses(nbCaisse);
	}


	/** 
	 * initialise les grilles a l'instant t et t-1 : ajout de cellules mortes et appel de initHasard
	 */
	private void init()
	{
		for(int i=0; i<taille; i++)
			for(int j=0; j<taille; j++)
				grille[i][j] = new Cellule(grille, i, j);
	}


	/** 
	 * initialise les zones de nourriture :<br>
	 *  dose max de nourriture par case = 20
	 */
	private void initNourriture()
	{
		setNourriture((taille) / 9, taille / 9, 3, 50d);
		setNourriture((9*taille) / 10, taille / 9, 3, 50d);
		setNourriture((taille ) / 10, taille / 2, 3, 50d);
		setNourriture((8*taille ) / 9, (3*taille) / 4, 5, 20d);
		setNourriture((taille ) / 9, (8*taille) / 9, 4, 30d);
	}
	

	/**place une zone de nourriture 
	 * @param xMeat abscice du centre de la zone de nourriture
	 * @param yMeat ordonnee du centre de la zone de nourriture
	 * @param tailleZone taille de la zone
	 * @param meatWeight dose de nourriture par case dans la zone*/
	private void setNourriture(int xMeat, int yMeat, int tailleZone, double meatWeight)
	{
		for(int i=-tailleZone; i<=tailleZone; i++)
		{
			int xx = (xMeat + i + taille) % taille;
			for(int j=-tailleZone; j<=tailleZone; j++)
			{
				int yy = (yMeat + j + taille) % taille;
				if((Math.abs(i)+Math.abs(j))<=tailleZone) 
					grille[xx][yy].setNourriture(meatWeight);
			}
		}
	}
	
	/**place une zone de nid 
	 * @param xNid abscice du centre de la zone de nourriture
	 * @param yNid ordonnee du centre de la zone de nourriture
	 * @param tailleZone taille de la zone*/
	private void setNid(int xNid, int yNid, int tailleZone)
	{
		for(int i=-tailleZone; i<=tailleZone; i++)
		{
			int xx = (xNid + i + taille) % taille;
			for(int j=-tailleZone; j<=tailleZone; j++)
			{
				int yy = (yNid + j + taille) % taille;
				if((Math.abs(i)+Math.abs(j))<=tailleZone) 
					grille[xx][yy].setNid(true);
			}
		}
	}
	
	
	/**pose le nid sur le terrain, d�finit l'odeur du nid dans chaque case du terrain*/
	private void initNid()
	{
		grille[xNid][yNid].setNid(true);
		for(int i=0; i<taille; i++)
			for(int j=0; j<taille; j++)
			{
				Cellule cell = grille[i][j];
				double odeur = odeurNid / (Math.abs(xNid-cell.getX()) + Math.abs(yNid-cell.getY()));
				cell.setOdeurNid(odeur);
			}
		setNid(xNid, yNid, 2);
	}

	/**cree les fourmis
	 * @param nb nombre de fourmis*/
	private void initFourmis(int nb)
	{
		lesFourmis = new Fourmi[nb];
		for(int i=0; i<nb; i++)
		{
			lesFourmis[i] = new Fourmi(xNid, yNid, this);
		}
	}
	
	private void initRobots(int nb)
	{
		lesRobots = new Robot[nb];
		for(int i=0; i<nb; i++)
		{
			lesRobots[i] = new Robot(xNid, yNid, this);
		}
	}
	
	private void initIntru() {
		intru = new Intru(taille/2, taille/2, this);
	}
	
	private void initCaisses(int nbCaisses)
	{
		int i = nbCaisses;
		while (i > 0)
		{		
			int x = (int) Math.random()*taille;
			int y = (int) Math.random()*taille;
			if (grille[x][y].getCaisse() == 0)
			{
				if (i != 1) {grille[x][y].setCaisse(1);}
				else {grille[x][y].setCaisse(2);}
				i--;
			}
		}
	}	
	
	/**  
	 * demande a toutes les cellules de la grille a l'instant t d'evoluer, 
	 */
	public void animGrille()
	{
		for(Robot f:lesRobots) f.evoluer();
		
	}

	/**
	 * @return the grille
	 */
	public Cellule[][] getGrille() {
		return grille;
	}


	/**
	 * @return the taille
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * @return the nbFourmis
	 */
	public int getNbFourmis() {
		return nbFourmis;
	}

	/**
	 * @return the xNid
	 */
	public int getxNid() {
		return xNid;
	}

	/**
	 * @return the yNid
	 */
	public int getyNid() {
		return yNid;
	}

	/**
	 * @return the lesFourmis
	 */
	public Fourmi[] getLesFourmis() {
		return lesFourmis;
	}
	
	public Robot[] getLesRobots() {
		return lesRobots;
	}
	
	public void setIntru(Intru i) {
		this.intru=i;
	}
	
	public Intru getIntru() {
		return this.intru;
	}
}
