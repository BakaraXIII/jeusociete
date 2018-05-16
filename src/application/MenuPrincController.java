package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MenuPrincController {
	
	
	public void jouer(ActionEvent e) {
		System.out.println("Jouer");
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/MenuPath.fxml"));
			Scene scene = new Scene(root);
		    
	        theStage.setTitle("FXML Welcome");
	        theStage.setScene(scene);
	        theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	}
	
	public void histoire(ActionEvent e) {
		System.out.println("Jouer");
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
		Parent root;
		try {
			// root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Histoire.fxml"));
			root = loader.load();
			HistoireController controller = loader.<HistoireController>getController(); // Recupï¿½re controller fenetre
			
			Media m = new Media(getClass().getResource("/multimedia/intro.mp4").toString());
			MediaPlayer mp = new MediaPlayer(m);
			mp.setAutoPlay(true);

			controller.eIntro.setMediaPlayer(mp);

			Scene scene = new Scene(root);

			theStage.setTitle("FXML Welcome");
			theStage.setScene(scene);
			theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		    
	}
	
	public void explication(ActionEvent e) {
		System.out.println("Jouer");
		
		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); //garder même fenêtre
		
		Parent root;
		try {
			// root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Explication.fxml"));
			root = loader.load();

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
