package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class MenuPathController {
	
	@FXML
	public CheckBox path1;
	
	@FXML
	public CheckBox path2;
	
	@FXML
	public CheckBox path3;
	
	
	
	
	public void retour(ActionEvent e) {
		System.out.println("retour");
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder m�me fen�tre
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/MenuPrinc.fxml"));
			Scene scene = new Scene(root);
		    
	        theStage.setTitle("Jeu");
	        theStage.setScene(scene);
	        theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	}
	
	public void valider(ActionEvent e) {
		System.out.println("validerPath");
		
		boolean estVisuel = path1.isSelected();
		boolean estAuditif = path2.isSelected();
		boolean estManuel = path3.isSelected();
		
		ArrayList<Enigme> a = new ArrayList<>();
		
		ArrayList<String> univers = new ArrayList<>();
		
		
		for(int i = 0; i < Jeu.getEnigmes().size(); i++) {
			Enigme j = Jeu.getEnigmes().get(i);
			ArrayList<String> typePatho = j.getTypePatho();
			
			if((!estVisuel || !typePatho.contains("Visuelle")) && (!estAuditif || !typePatho.contains("Auditive")) && (!estManuel || !typePatho.contains("Manuelle"))) {
				a.add(j);
				String u = j.getUnivers();
				if (!univers.contains(u)) {
					univers.add(u);
				}
			}
		}
			System.out.println(a);
		
		Jeu.setUnivers(univers);
		Jeu.setEnigmes(a);
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder m�me fen�tre
		
		
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/MenuNbJoueur.fxml"));
			Scene scene = new Scene(root);
		    
	        theStage.setTitle("Jeu");
	        theStage.setScene(scene);
	        theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	}
}
