package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class HistoireController {

	@FXML
	public MediaView eIntro;
	
	public void retour(ActionEvent e) {	
		eIntro.getMediaPlayer().stop();
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
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
}
