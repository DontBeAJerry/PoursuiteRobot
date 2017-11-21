package modele;
/**
 * cette classe represente une cellule de la simulation de fourmis
 * @author  Emmanuel Adam
 */
public class Cellule  
{
	/**coordonnee de la cellule dans la grille*/ 
	private int x,y;
	/**nourriture*/
	private double nourriture;
	/** presence : pas de caisse : 0, caisse normal : 1, caisse sensible : 2**/
	private int caisse;
	/**cellule d'un nid*/
	private boolean nid;
	/**presence d'au moins une fourmis*/
	private boolean robot;
	/**odeur du nid*/
	private double odeurNid;
	/**defini la cellule vient d'etre videe de son contenu caisse*/
	private boolean emptyNow;
	/**intrus present dans la cellule**/
	private boolean intrus;
	/** cellule visible**/
	private boolean visible;
	
	/** reference a la grille des cellule*/
	static Cellule [][]grille;

	/**a change recemment*/
	public boolean hasJustChanged;

	/** constructeur par defaut, inutilise*/
	public Cellule(){}

	/** constructeur initialisant la grille, les coordonnees et la nature de la cellule*/
	public Cellule(Cellule [][] grille, int x, int y)
	{
		Cellule.grille = grille;
		this.x = x; this.y = y;
		hasJustChanged = true;
		emptyNow = false;
		nid = false;
		caisse = 0;
		visible = false;
	}

	/** evoluer = diffuser puis evaporer */
	void evoluer()
	{
		if(!nid)
		{
			///diffuser();
			//evaporer();
		}
	}

	

	

	/**diminuer la dose de nourriture dans la cellule
	 * @param dose de nourriture a oter de la cellule*/
	void oterNourriture(double doseNourriture)
	{
		nourriture -= doseNourriture;
		if(nourriture<0){nourriture=0; emptyNow =true;}
		hasJustChanged = true;
		
	}


	/**
	 * @return the hasJustChanged
	 */
	public boolean isHasJustChanged() {
		return hasJustChanged;
	}

	

	/**
	 * @return the nourriture
	 */
	public double getNourriture() {
		return nourriture;
	}

	/**
	 * @param nourriture the nourriture to set
	 */
	public void setNourriture(double nourriture) {
		this.nourriture = nourriture;
	}

	/**
	 * @return the nid
	 */
	public boolean isNid() {
		return nid;
	}

	/**
	 * @param nid the nid to set
	 */
	public void setNid(boolean nid) {
		this.nid = nid;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the fourmis
	 */
	public boolean getRobot() {
		return robot;
	}

	/**
	 * @param fourmis the fourmis to set
	 */
	public void setRobot(boolean robot) {
		this.robot = robot;
		this.hasJustChanged = true;
	}

	/**
	 * @return the odeurNid
	 */
	public double getOdeurNid() {
		return odeurNid;
	}

	/**
	 * @param odeurNid the odeurNid to set
	 */
	public void setOdeurNid(double odeurNid) {
		this.odeurNid = odeurNid;
	}

	/**
	 * @return the emptyNow
	 */
	public boolean isEmptyNow() {
		return emptyNow;
	}

	/**
	 * @param emptyNow the emptyNow to set
	 */
	public void setEmptyNow(boolean emptyNow) {
		this.emptyNow = emptyNow;
	}

	public int getCaisse() {
		return caisse;
	}
	
	public boolean isCaisse(){
		if(getCaisse() == 1 || getCaisse() == 2)
			return true;
		else
			return false;
	}

	public void setCaisse(int caisse) {
		this.caisse = caisse;
	}

	public boolean getVisible()
	{
		return visible;
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
}