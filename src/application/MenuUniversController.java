package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuUniversController{
	
	@FXML
	public Button btn1;
	
	@FXML
	public Button btn2;
	
	@FXML
	public Button btn3;
	
	@FXML
	public Button btn4;
	
	@FXML
	public Button btn5;
	
	public void enigme(ActionEvent e) {
		Node bouton_raw = (Node) e.getSource();
		Stage theStage = (Stage) bouton_raw.getScene().getWindow(); //garder même fenêtre
		Button bouton = (Button) bouton_raw;
		String en_raw = bouton.getText();
		
		ArrayList<JSONObject> enigme_univers = new ArrayList<>();
		
		for(int i = 0; i < Jeu.getEnigmes().length(); i++) {
			if(Jeu.getEnigmes().getJSONObject(i).getString("Univers").contains(en_raw)) {
				enigme_univers.add(Jeu.getEnigmes().getJSONObject(i));
			}
		}
		
		Collections.shuffle(enigme_univers);
		JSONObject enigmeCourante = enigme_univers.get(0);
		
		Parent root;
		try {
			//root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Enigme.fxml"));
			root = loader.load();
			EnigmeController controller = loader.<EnigmeController>getController(); //Recupère controller fenetre suivante
			
			controller.displayEnigme(enigmeCourante);
			
			Scene scene = new Scene(root);
		    
	        theStage.setTitle("FXML Welcome");
	        theStage.setScene(scene);
	        theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	@FXML
	protected void initialize() {
		System.out.println(Jeu.getNbUnivers());
		if(Jeu.getNbUnivers() == 4) {
			btn5.setVisible(false);
		}else {
			btn5.setVisible(true);
		}
		List<String> univers = Jeu.getUnivers();
		Collections.shuffle(univers);
		btn1.setText(univers.get(0));
		btn2.setText(univers.get(1));
		btn3.setText(univers.get(2));
		btn4.setText(univers.get(3));
		if(Jeu.getNbUnivers() == 5)
			btn5.setText(univers.get(4));
	}
	
}
