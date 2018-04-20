package application;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	public void displayEnigme(JSONObject enigme) {		
		eUnivers.setText(enigme.getString("Univers"));	
		eEnonce.setText(enigme.getString("Enonce"));
		ArrayList<ImageView> images = new ArrayList<>();
		images.add(eImage1);
		images.add(eImage2);
		images.add(eImage3);
		images.add(eImage4);
		images.add(eImage5);
		if(enigme.getJSONArray("images").length() != 0) {
			for (int i = 0; i < enigme.getJSONArray("images").length(); i++) {
				ImageView image = images.get(i);
				Image img = new Image(getClass().getResourceAsStream("/"+enigme.getJSONArray("images").getString(i)));
				image.setImage(img);
			}	
				
		} else {
			images.forEach(i -> {
				i.setVisible(false);
			});
		}
		
		ArrayList<Button> btns = new ArrayList<>();
		btns.add(eProposition1);
		btns.add(eProposition2);
		btns.add(eProposition3);
		btns.add(eProposition4);
		if(enigme.getJSONArray("propositions").length() != 0) {
			for (int i = 0; i < enigme.getJSONArray("propositions").length(); i++) {
				Button btn = btns.get(i);
				btn.setText(enigme.getJSONArray("propositions").getString(i));
			}
			eTrouve.setVisible(false);
				
		} else {
			btns.forEach(i -> i.setVisible(false));
		}
		
		eSolution.setText(enigme.getString("reponse"));
		
		eSolution.setVisible(false);
		
	}
	
	public void checkSolution(ActionEvent e) {
	    Node bouton_raw = (Node) e.getSource();
        Stage theStage = (Stage) bouton_raw.getScene().getWindow(); //garder m�me fen�tre
        Button bouton = (Button) bouton_raw;
        String proposition = bouton.getText();
        
        if(eSolution.getText().equals(proposition)) {
            // TODO: Faire une système de coloration du bouton en fonction de la réponse
            //bouton.getStyleClass().add("addBobOk");
            eSolution.setText("TROUVE\n" + eSolution.getText());
        }
        eSolution.setVisible(true);
	}
	
	public void retour(ActionEvent e) {
	    Parent root;
        try {
            //root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuUnivers.fxml"));
            root = loader.load();
            //MenuUniversController controller = loader.<MenuUniversController>getController(); //Recup�re controller fenetre suivante
            
            Scene scene = new Scene(root);
            
            Stage theStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            
            theStage.setTitle("FXML Welcome");
            theStage.setScene(scene);
            theStage.show();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
	}
	
}
