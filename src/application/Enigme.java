package application;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Enigme implements Comparable<Enigme>{
    private ArrayList<String> typePatho;
    private String categorie;
    private String univers;
    private String enonce;
    private ArrayList<String> images;
    private String media;
    private ArrayList<String> propositions;
    private String reponse;
    private int priorite;
    
    public static List<Enigme> getEnigmes(JSONArray a) {
        ArrayList<Enigme> enigmes = new ArrayList<>();
        
        for(int i = 0; i < a.length(); i++) {
            enigmes.add(new Enigme(a.getJSONObject(i)));
        }
        
        return enigmes;
    }
    
    private Enigme(JSONObject o) {
        typePatho = new ArrayList<>();
        o.getJSONArray("typePatho").forEach(i -> 
            typePatho.add((String) i)
        ); 
        categorie = o.getString("categorie");
        univers = o.getString("Univers");
        enonce = o.getString("Enonce");
        images = new ArrayList<>();
        o.getJSONArray("images").forEach(i -> 
            images.add((String) i)
        ); 
        media = o.getString("Media");
        propositions = new ArrayList<>();
        o.getJSONArray("propositions").forEach(i -> 
            propositions.add((String) i)
        );
        reponse = o.getString("reponse");
        priorite = 0;
    }

    public ArrayList<String> getTypePatho() {
        return typePatho;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getUnivers() {
        return univers;
    }

    public String getEnonce() {
        return enonce;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public String getMedia() {
        return media;
    }

    public ArrayList<String> getPropositions() {
        return propositions;
    }

    public String getReponse() {
        return reponse;
    }
    
    public String toString() {
        return this.categorie;
    }
    
    public int getPriorite() {
        return this.priorite;
    }
    
    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }
    
    public static void changerPriorite(List<Enigme> enigmes,Enigme enigmeTire) {
        enigmes.forEach(e -> e.setPriorite(e.getPriorite() + 1));
        enigmeTire.setPriorite(0);
    }

    @Override
    public int compareTo(Enigme o) {
        // TODO Auto-generated method stub
        return  Integer.compare(o.getPriorite(), this.getPriorite());
    }
    
    
}
