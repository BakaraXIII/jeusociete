package application;

import java.util.ArrayList;

import org.json.JSONArray;


public class Jeu {
	private static int nbJoueur = 0;
	private static int nbFigurine = 0;
	private static int nbUnivers = 0;
	private static int nbItem = 0;
	private static ArrayList<String> univers = new ArrayList<String>() {{
		add("Moyen age");
		add("Renaissance");
		add("Prehistoire");
		add("Antiquite");
		add("Blind tests");
	}};
	private static JSONArray enigmes = null;
	
	
	public static int getNbJoueur() {
		return nbJoueur;
	}
	
	
	public static void setNbJoueur(int nbJoueur) {
		Jeu.nbJoueur = nbJoueur;
		
		if(nbJoueur <= 3) {
			nbFigurine = 1;
		}else {
			nbFigurine = 2;
		}
		
		if(nbJoueur <= 3) {
			nbUnivers = 4;
		}else {
			nbUnivers = 5;
		}
		
		if(nbJoueur == 2) {
			nbItem	 = 4;
		}else if(nbJoueur == 3) {
			nbItem = 5;
		}else {
			nbItem = 6;
		}
	}
	
	public static int getNbItem() {
		return nbItem;
	}
	public static int getNbFigurine() {
		return nbFigurine;
	}
	public static int getNbUnivers() {
		return nbUnivers;
	}
	public static ArrayList<String> getUnivers() {
		return univers;
	}

	public static JSONArray getEnigmes() {
		return enigmes;
	}

	public static void setEnigmes(JSONArray enigmes) {
		Jeu.enigmes = enigmes;
	}
	
	
	
}
