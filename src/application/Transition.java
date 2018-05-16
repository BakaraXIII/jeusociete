package application;

import java.io.IOException;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Transition {
	
	@FXML
	public MediaView eTransition;

	public void suivant(ActionEvent e) {
		
		eTransition.getMediaPlayer().stop();
		Jeu.setNbEssais(0);
		Collections.shuffle(Jeu.getUnivers());
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/Enigme2.fxml"));
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
