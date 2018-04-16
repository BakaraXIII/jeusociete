package application;

import java.util.ArrayList;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
			btns.forEach(i -> {
				i.setVisible(false);
			});
			eSolution.setVisible(false);
		}
		
		eSolution.setText(enigme.getString("reponse"));
		
		
		
	}
}
