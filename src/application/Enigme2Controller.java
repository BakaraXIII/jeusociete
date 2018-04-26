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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Enigme2Controller {

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
	public Button eSuivant;

	public void displayEnigme(JSONObject enigme) {
		eUnivers.setText(enigme.getString("Univers"));
		eEnonce.setText(enigme.getString("Enonce"));
		ArrayList<ImageView> images = new ArrayList<>();
		images.add(eImage1);
		images.add(eImage2);
		images.add(eImage3);
		images.add(eImage4);
		images.add(eImage5);
		if (enigme.getJSONArray("images").length() != 0) {
			for (int i = 0; i < enigme.getJSONArray("images").length(); i++) {
				ImageView image = images.get(i);
				Image img = new Image(getClass().getResourceAsStream("/" + enigme.getJSONArray("images").getString(i)));
				image.setImage(img);
			}

		} else {
			images.forEach(i -> {
				i.setVisible(false);
			});
		}

		if (enigme.getString("Media").length() != 0) {
			Media m = new Media(getClass().getResource("/" + enigme.getString("Media")).toString());
			MediaPlayer mp = new MediaPlayer(m);
			mp.setAutoPlay(true);
			eMedia.setMediaPlayer(mp);
		}

		ArrayList<Button> btns = new ArrayList<>();
		btns.add(eProposition1);
		btns.add(eProposition2);
		btns.add(eProposition3);
		btns.add(eProposition4);
		if (enigme.getJSONArray("propositions").length() != 0) {
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

		eSuivant.setVisible(false);
	}

	public void abandon(ActionEvent e) {
		Parent root;
		try {
			// root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Perdu.fxml"));
			root = loader.load();
			// MenuUniversController controller =
			// loader.<MenuUniversController>getController(); //Recupï¿½re controller
			// fenetre suivante

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

	public void checkSolution(ActionEvent e) {
		Node bouton_raw = (Node) e.getSource();
		Stage theStage = (Stage) bouton_raw.getScene().getWindow(); // garder mï¿½me fenï¿½tre
		Button bouton = (Button) bouton_raw;
		String proposition = bouton.getText();

		if (eSolution.getText().equals(proposition)) {
			// TODO: Faire une systÃ¨me de coloration du bouton en fonction de la rÃ©ponse
			// bouton.getStyleClass().add("addBobOk");
			eSolution.setText("TROUVE !\n" + eSolution.getText());
		} else {
			eSolution.setText("PERDU !\n" + eSolution.getText());
			Jeu.setNbEssais(Jeu.getnbEssais()-1);
		}
		eSolution.setVisible(true);

		eProposition1.setDisable(true);
		eProposition2.setDisable(true);
		eProposition3.setDisable(true);
		eProposition4.setDisable(true);
		if (eMedia.getMediaPlayer() != null) {
			eMedia.getMediaPlayer().stop();
		}

		eSuivant.setVisible(true);
	}

	public void suivant(ActionEvent e) {

		Node bouton = (Node) e.getSource();
		Stage theStage = (Stage) bouton.getScene().getWindow(); // garder même fenêtre

		Parent root;
		try {

			if (Jeu.getUnivers().size() == 0) {
				root = FXMLLoader.load(getClass().getResource("/Gagne.fxml"));
				Scene scene = new Scene(root);

				theStage.setTitle("FXML Welcome");
				theStage.setScene(scene);
				theStage.show();
			}else if(Jeu.getnbEssais() < 0){
				root = FXMLLoader.load(getClass().getResource("/Perdu.fxml"));
				Scene scene = new Scene(root);

				theStage.setTitle("FXML Welcome");
				theStage.setScene(scene);
				theStage.show();
			
			}else {
				root = FXMLLoader.load(getClass().getResource("/Enigme2.fxml"));
				Scene scene = new Scene(root);

				theStage.setTitle("FXML Welcome");
				theStage.setScene(scene);
				theStage.show();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@FXML
	protected void initialize() {
		String univers = Jeu.getUnivers().remove(0);

		ArrayList<JSONObject> enigme_univers = new ArrayList<>();

		for (int i = 0; i < Jeu.getEnigmes().length(); i++) {
			if (Jeu.getEnigmes().getJSONObject(i).getString("Univers").contains(univers)) {
				enigme_univers.add(Jeu.getEnigmes().getJSONObject(i));
			}
		}

		Collections.shuffle(enigme_univers);
		JSONObject enigmeCourante = enigme_univers.get(0);

		displayEnigme(enigmeCourante);
	}

}
