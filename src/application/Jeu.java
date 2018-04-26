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
		add("Annees 80");
		add("Temps Modernes");
		add("Siecle des lumieres");
	}};
	private static JSONArray enigmes = null;
	private static int nbEssais = 0;
	
	
	public static int getNbJoueur() {
		return nbJoueur;
	}
	
	
	public static void setNbJoueur(int nbJoueur) {
		Jeu.nbJoueur = nbJoueur;
		
		if(nbJoueur <= 3) {
			nbFigurine = 1;
			nbUnivers = 4;
		}else {
			nbFigurine = 2;
			nbUnivers = 5;
		}
		
		if(nbJoueur == 2) {
			nbItem = 2;
			nbEssais = 3;
		}else if(nbJoueur == 3) {
			nbItem = 8;
			nbEssais = 2;
		}else {
			nbItem = 12;
			nbEssais = 4;
		}
	}
	
	public static int getNbItem() {
		return nbItem;
	}
	
	public static void setNbItem(int nbItem) {
		Jeu.nbItem = nbItem;
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

	public static void setUnivers(ArrayList<String> univers) {
		Jeu.univers = univers;
	}

	public static JSONArray getEnigmes() {
		return enigmes;
	}

	public static void setEnigmes(JSONArray enigmes) {
		Jeu.enigmes = enigmes;
	}

	public static int getnbEssais() {
		return nbEssais;
	}

	public static void setNbEssais(int nbEssais) {
		Jeu.nbEssais = nbEssais;
	}
	
	
	
}
