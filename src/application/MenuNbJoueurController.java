package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuNbJoueurController {
	
	public void retour(ActionEvent e) {
		System.out.println("retour");
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ihm/MenuPath.fxml"));
			Scene scene = new Scene(root);
		    
	        theStage.setTitle("FXML Welcome");
	        theStage.setScene(scene);
	        theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	}
	
	
	public void nombre(ActionEvent e) {
		
		Node bouton_raw = (Node) e.getSource();
		Stage theStage = (Stage) bouton_raw.getScene().getWindow(); //garder même fenêtre
		Button bouton = (Button) bouton_raw;
		String nb_raw = bouton.getText();
		int nb_joueurs = Integer.parseInt(nb_raw);
		Jeu.setNbJoueur(nb_joueurs);
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/ihm/MenuUnivers.fxml"));
			
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
