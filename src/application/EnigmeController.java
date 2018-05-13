package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnigmeController {
	
	@FXML
	public Label eUnivers;
	
	@FXML
	public Label eEnonce;
	
	@FXML
	public ImageView eImage1;
	
	@FXML
	public ImageView eImage2;
	
	@FXML
	public ImageView eImage3;
	
	@FXML
	public ImageView eImage4;
	
	@FXML
	public ImageView eImage5;
	
	@FXML
	public MediaView eMedia;
	
	@FXML
	public Button eProposition1;
	
	@FXML
	public Button eProposition2;
	
	@FXML
	public Button eProposition3;
	
	@FXML
	public Button eProposition4;
	
	@FXML
	public Label eSolution;
	
	@FXML
	public Button eTrouve;
	
	@FXML
	public Button eRetour;
	
	@FXML
	public Label eNbEssais;
	
	@FXML
	public Button eAbandon;
	
	@FXML
	public GridPane eGrid;
	
	
	public void displayEnigme(Enigme enigme) {
	    
	    Enigme.changerPriorite(Jeu.getEnigmes(), enigme);
	    
		eUnivers.setText(enigme.getUnivers());	
		eEnonce.setText(enigme.getEnonce());
		ArrayList<ImageView> images = new ArrayList<>();
		images.add(eImage1);
		images.add(eImage2);
		images.add(eImage3);
		images.add(eImage4);
		images.add(eImage5);
		if(!enigme.getImages().isEmpty()) {
			for (int i = 0; i < enigme.getImages().size(); i++) {
				ImageView image = images.get(i);
				Image img = new Image(getClass().getResourceAsStream("/"+enigme.getImages().get(i)));
				image.setImage(img);
			}	
				
		} else {
			images.forEach(i -> {
				i.setVisible(false);
			});
		}
		
		if(enigme.getMedia().length() != 0) {
			Media m = new Media(getClass().getResource("/"+enigme.getMedia()).toString());
			MediaPlayer mp = new MediaPlayer(m);
			mp.setAutoPlay(true);
			eMedia.setMediaPlayer(mp);
		}
		
		if(enigme.getImages().isEmpty() && enigme.getMedia().length() == 0) {
			eGrid.getRowConstraints().get(2).setMaxHeight(0);
		}
		
		ArrayList<Button> btns = new ArrayList<>();
		btns.add(eProposition1);
		btns.add(eProposition2);
		btns.add(eProposition3);
		btns.add(eProposition4);
		if(!enigme.getPropositions().isEmpty()) {
			for (int i = 0; i < enigme.getPropositions().size(); i++) {
				Button btn = btns.get(i);
				btn.setText(enigme.getPropositions().get(i));
			}
			eTrouve.setVisible(false);
			eAbandon.setVisible(false);	
		} else {
			btns.forEach(i -> i.setVisible(false));
		}
		
		eSolution.setText(enigme.getReponse());
		
		eSolution.setVisible(false);
		eRetour.setVisible(false);
		
		// Affiche le nombre d'essais
		eNbEssais.setText(Integer.toString(Jeu.getnbEssais()));
		
	}
	
	public void checkSolution(ActionEvent e) {
	    Node bouton_raw = (Node) e.getSource();
        Stage theStage = (Stage) bouton_raw.getScene().getWindow(); //garder m�me fen�tre
        Button bouton = (Button) bouton_raw;
        String proposition = bouton.getText();
        boolean trouve = false;
        
        if(eSolution.getText().equals(proposition)) {
            // TODO: Faire une système de coloration du bouton en fonction de la réponse
            //bouton.getStyleClass().add("addBobOk");
            eSolution.setText("TROUVE !\n" + eSolution.getText());
            Jeu.setNbItem(Jeu.getNbItem()-1);
            trouve = true;
        } else {
        	eSolution.setText("PERDU !\n" + eSolution.getText());
        	Jeu.setNbEssais(Jeu.getnbEssais()-1);
        }
        eSolution.setVisible(true);
        
        eProposition1.setDisable(true);
        eProposition2.setDisable(true);
        eProposition3.setDisable(true);
        eProposition4.setDisable(true);
        if(eMedia.getMediaPlayer() != null) {
        	eMedia.getMediaPlayer().stop();
        }
        eRetour.setVisible(true);
        
        if(trouve) {
        	generationEvenement();
        }
	}
	
	public void abandon(ActionEvent e) {
	    Parent root;
	    Jeu.setNbEssais(Jeu.getnbEssais()-1);
	    try {
            //root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));
            if(Integer.parseInt(eNbEssais.getText()) < 1){
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Perdu.fxml"));
                root = loader.load();
                //MenuUniversController controller = loader.<MenuUniversController>getController(); //Recup�re controller fenetre suivante
                
                Scene scene = new Scene(root);
                
                Stage theStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                
                theStage.setTitle("FXML Welcome");
                theStage.setScene(scene);
                theStage.show();          
        	}else {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuUnivers.fxml"));
	            root = loader.load();
	            //MenuUniversController controller = loader.<MenuUniversController>getController(); //Recup�re controller fenetre suivante
	            
	            Scene scene = new Scene(root);
	            
	            Stage theStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	            
	            theStage.setTitle("FXML Welcome");
	            theStage.setScene(scene);
	            theStage.show();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	
	}
	
	public void trouve(ActionEvent e) {
		Jeu.setNbItem(Jeu.getNbItem()-1);
		generationEvenement();
		retour(e);
	}
	
	public void retour(ActionEvent e) {
	    Parent root;
        try {
            //root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));
            if(Integer.parseInt(eNbEssais.getText()) < 1){
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Perdu.fxml"));
                root = loader.load();
                //MenuUniversController controller = loader.<MenuUniversController>getController(); //Recup�re controller fenetre suivante
                
                Scene scene = new Scene(root);
                
                Stage theStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                
                theStage.setTitle("FXML Welcome");
                theStage.setScene(scene);
                theStage.show();
            
            }else if(Jeu.getNbItem() == 0){
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Transition.fxml"));
	            root = loader.load();
	            //MenuUniversController controller = loader.<MenuUniversController>getController(); //Recup�re controller fenetre suivante
	            
	            Scene scene = new Scene(root);
	            
	            Stage theStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	            
	            theStage.setTitle("FXML Welcome");
	            theStage.setScene(scene);
	            theStage.show();
            
        	}else {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuUnivers.fxml"));
	            root = loader.load();
	            //MenuUniversController controller = loader.<MenuUniversController>getController(); //Recup�re controller fenetre suivante
	            
	            Scene scene = new Scene(root);
	            
	            Stage theStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
	            
	            theStage.setTitle("FXML Welcome");
	            theStage.setScene(scene);
	            theStage.show();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	
	public void generationEvenement() {
		Random rand = new Random();
		
		double nb = 0.5+((1.0/Jeu.getNbItem())-(1.0/8.0));
		
		if(nb == Double.POSITIVE_INFINITY) {
			return;
		}
		
		double alea = rand.nextDouble();
		
		System.out.println(alea+" "+nb);

		if(alea < 0.5+((1.0/Jeu.getNbItem())-(1.0/8.0))) {
			
			int numUnivers = rand.nextInt(Jeu.getUnivers().size()+1);
			
			String univers = "";
			
			if(numUnivers < Jeu.getUnivers().size()) {
				univers = Jeu.getUnivers().get(numUnivers);
			}
			
			List<String> msgs = Jeu.getEvenements().get(univers);
			Collections.shuffle(msgs);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ev�nement");
			alert.setHeaderText(null);
			alert.setContentText(msgs.get(0));
			alert.initModality(Modality.NONE);

			alert.showAndWait();
		}
	}
	
}
