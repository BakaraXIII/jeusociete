package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import application.Enigme;
import org.json.JSONArray;
import org.json.JSONObject;


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
	private static ArrayList<Enigme> enigmes = null;
	private static int nbEssais = 0;
	private static HashMap<String, List<String>> evenements = new HashMap<>();
	
	
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
		
		ArrayList<String> universASupprimer = new ArrayList<>();
		
		
		for(String key : Jeu.evenements.keySet()) {
			if(!key.equals("") && !Jeu.univers.contains(key)) {
				universASupprimer.add(key);
			}
		}
		
		for(String u : universASupprimer) {
			Jeu.evenements.remove(u);
		}
	}

	public static List<Enigme> getEnigmes() {
		return enigmes;
	}

	public static void setEnigmes(JSONArray enigmes) {
		Jeu.enigmes = new ArrayList<>(Enigme.getEnigmes(enigmes));
		Collections.shuffle(Jeu.enigmes);
	}
	
	public static void setEnigmes(ArrayList<Enigme> enigmes) {
        Jeu.enigmes = enigmes;
        Collections.shuffle(Jeu.enigmes);
    }

	public static int getnbEssais() {
		return nbEssais;
	}

	public static void setNbEssais(int nbEssais) {
		Jeu.nbEssais = nbEssais;
	}


	public static HashMap<String, List<String>> getEvenements() {
		return evenements;
	}


	public static void setEvenements(JSONArray evenements) {
		for(int i=0; i < evenements.length(); i++) {
			JSONObject data = evenements.getJSONObject(i);
			if(Jeu.evenements.containsKey(data.getString("univers"))) {
				Jeu.evenements.get(data.getString("univers")).add(data.getString("message"));
			} else {
				ArrayList<String> messages = new ArrayList<>();
				messages.add(data.getString("message"));
				Jeu.evenements.put(data.getString("univers"), messages);
			}
		}
	}
	
	
	
}
