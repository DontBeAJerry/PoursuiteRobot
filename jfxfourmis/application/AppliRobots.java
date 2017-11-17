package application;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import modele.Caisse;
import modele.Cellule;
import modele.Direction;
import modele.Intru;
import modele.Robot;
import modele.Terrain;

public class AppliRobots extends Application {
	/**terrain liee a cet objet graphique*/
	private Terrain terrain;
	/**nb de fourmis*/
	int nbFourmis = 30;
	/**vitesse de simulation*/
	double tempo = 50;
	/**taille de la terrain*/
	private int taille;
	/**taille d'une cellule en pixel*/
	private int espace = 10;
	private  static Rectangle [][] environnement; 
	public static Circle[]fourmis;



	@Override
	/**initialisation de l'application graphique*/
	public void start(Stage primaryStage) {
		int tailleTerrain = 70;
		fourmis = new Circle[nbFourmis];
		terrain = new Terrain(tailleTerrain, nbFourmis);
		taille = terrain.getTaille();
		construireScenePourFourmis( primaryStage);

	}



	/**construction du th�atre et de la sc�ne */
	void construireScenePourFourmis(Stage primaryStage) 
	{
		//definir la scene principale
		Group root = new Group();
		Scene scene = new Scene(root, 2*espace + taille*espace, 2*espace + taille*espace, Color.BLACK);
		primaryStage.setTitle("Life...");
		primaryStage.setScene(scene);
		//definir les acteurs et les habiller
		AppliRobots.environnement = new Rectangle[taille][taille];
		dessinEnvironnement( root);

		//afficher le theatre
		primaryStage.show();

		//-----lancer le timer pour faire vivre les fourmis et l'environnement
		Timeline littleCycle = new Timeline(new KeyFrame(Duration.millis(tempo), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				terrain.animGrille();
		//		updateTerrain();
			}
		}));
		littleCycle.setCycleCount(Timeline.INDEFINITE);
		littleCycle.play();
		
		
		Intru intru = terrain.getIntru();
		Circle dessinIntrus = intru.getDessin();
		dessinIntrus.requestFocus();
		dessinIntrus.setOnKeyPressed(e->{
		  System.err.println(e.getCode());
		  switch(e.getCode()) {
		    case UP: intru.setDirection(Direction.NORD); intru.bougerVersDirection(); break;
		    case LEFT: intru.setDirection(Direction.OUEST); intru.bougerVersDirection(); break;
		    case DOWN: intru.setDirection(Direction.SUD); intru.bougerVersDirection(); break;
		    case RIGHT: intru.setDirection(Direction.EST); intru.bougerVersDirection(); break;
		  }
		});
	}



	/** 
	 *creation des cellules et de leurs habits
	 */
	void dessinEnvironnement(Group root)
	{
		Cellule[][] grille = terrain.getGrille();
		// chaque parcelle de l'environnement est verte
		for(int i=0; i<taille; i++)
			for(int j=0; j<taille; j++)
			{
				AppliRobots.environnement[i][j] = new Rectangle((i+1)*(espace), (j+1)*(espace), espace, espace);
				AppliRobots.environnement[i][j].setFill(Color.DARKGREEN);
				root.getChildren().add(AppliRobots.environnement[i][j]);
			}
		//creation des caisses
		/*for(int i=0; i<taille; i++)
			for(int j=0; j<taille; j++)
			{
				if (grille[i][j].getCaisse() == 1)
				{
					Caisse c = new Caisse(i,j,1);
					c.setDessin(new Circle(((taille+3)*espace)/2 , ((taille+3)*espace)/2, espace/2, Color.BLACK));
					root.getChildren().add(c.getDessin());
				}
			}
	/*	for(int i=0; i<taille; i++)
			for(int j=0; j<taille; j++)
			{
				Cellule cell = grille[i][j];
				if (cell.getNourriture()>0) // affichage des zones de nourritures 
				{
					Color colNouriture = Color.DARKGOLDENROD;
					double ratio = cell.getNourriture() / 50d; // pourcentage de pheromone par rapport au max possible (50)
					colNouriture = colNouriture.interpolate(Color.BISQUE, ratio);
					AppliRobots.environnement[i][j] = new Rectangle((i+1)*(espace), (j+1)*(espace), espace, espace);
					AppliRobots.environnement[i][j].setFill(colNouriture);
					// dessin des nourritures au dessus de l'environnement
					root.getChildren().add(AppliRobots.environnement[i][j]);
				}
				else
					if (cell.isNid()) // affichage de la zone du nid
					{
						AppliRobots.environnement[i][j] = new Rectangle((i+1)*(espace), (j+1)*(espace), espace, espace);
						AppliRobots.environnement[i][j].setFill(Color.BROWN);
						// dessin des nids � la place de l'environnement
						root.getChildren().remove(AppliRobots.environnement[i][j] );
						root.getChildren().add(AppliRobots.environnement[i][j]);
					}
			}*/
		//cr�ation des fourmis, rouges tomate
		for(Robot  r : terrain.getLesRobots())
		{
			r.setDessin(new Circle(((taille+3)*espace)/2 , ((taille+3)*espace)/2, espace/2, Color.TOMATO));
			r.setPas(espace);
			
			
			root.getChildren().add(r.getDessin());
		}
		//creation intru
		Intru i = terrain.getIntru();
		i.setDessin(new Circle(((taille+3)*espace)/2 , ((taille+3)*espace)/2, espace/2, Color.BLUE));
		i.setPas(espace);
		root.getChildren().add(i.getDessin());
		
		//petit effet de flou g�n�ral
		root.setEffect(new BoxBlur(2, 2, 5));
	}



	/**modification de la couleur des cellules en fonction de la dose de nourriture et de ph�romones*/
/*	private void updateTerrain()
	{
		Cellule[][] grille = terrain.getGrille();
		for(int i=0; i<taille; i++)
			for(int j=0; j<taille; j++)
			{
				Cellule cell = grille[i][j];
				if (cell.hasJustChanged && cell.getNourriture()>0) // affichage des zones de nourritures 
				{
					Color colNouriture = Color.DARKGOLDENROD;
					double ratio = cell.getNourriture() / 50d; // pourcentage de pheromone par rapport au max possible (50)
					colNouriture = colNouriture.interpolate(Color.BISQUE, ratio);
					AppliRobots.environnement[i][j].setFill(colNouriture);
				}
				if (cell.hasJustChanged && cell.isEmptyNow()  && !cell.isNid())
				{
					AppliRobots.environnement[i][j].setFill(Color.DARKGREEN );
					cell.setEmptyNow(false);
				}
				
			}
	}*/


	/**lancement de l'application*/
	public static void main(String[] args) {
		launch(args);
	}
}