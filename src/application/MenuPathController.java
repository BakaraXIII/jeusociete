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
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ihm/MenuPrinc.fxml"));
			Scene scene = new Scene(root);
		    
	        theStage.setTitle("FXML Welcome");
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
		
		JSONArray a = new JSONArray();
		
		ArrayList<String> univers = new ArrayList<>();
		
		
		for(int i = 0; i < Jeu.getEnigmes().length(); i++) {
			JSONObject j = Jeu.getEnigmes().getJSONObject(i);
			JSONArray typePatho = j.getJSONArray("typePatho");
			
			if((!estVisuel || !typePatho.toList().contains("Visuelle")) && (!estAuditif || !typePatho.toList().contains("Auditive")) && (!estManuel || !typePatho.toList().contains("Manuelle"))) {
				a.put(j);
				String u = j.getString("Univers");
				if (!univers.contains(u)) {
					univers.add(u);
				}
			}
		}
			System.out.println(a);
		
			Jeu.setUnivers(univers);
		Jeu.setEnigmes(a);
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
		
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ihm/MenuNbJoueur.fxml"));
			Scene scene = new Scene(root);
		    
	        theStage.setTitle("FXML Welcome");
	        theStage.setScene(scene);
	        theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	}
}
