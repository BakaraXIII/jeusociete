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
import javafx.stage.Stage;

public class MenuUniversController {

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
	
	@FXML
	public Label eNbItems;

	@FXML
	public Label eNbEssais;

	public void enigme(ActionEvent e) {
		Node bouton_raw = (Node) e.getSource();
		Stage theStage = (Stage) bouton_raw.getScene().getWindow(); // garder m�me fen�tre
		Button bouton = (Button) bouton_raw;
		String en_raw = bouton.getText();

		ArrayList<Enigme> enigme_univers = new ArrayList<>();

		for (int i = 0; i < Jeu.getEnigmes().size(); i++) {
			if (Jeu.getEnigmes().get(i).getUnivers().contains(en_raw)) {
				enigme_univers.add(Jeu.getEnigmes().get(i));
			}
		}

		Collections.sort(enigme_univers);
		Enigme enigmeCourante = enigme_univers.get(0);

		Parent root;
		try {
			// root = FXMLLoader.load(getClass().getResource("/Enigme.fxml"));

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Enigme.fxml"));
			root = loader.load();
			EnigmeController controller = loader.<EnigmeController>getController(); // Recup�re controller fenetre
																					// suivante

			controller.displayEnigme(enigmeCourante);

			Scene scene = new Scene(root);

			theStage.setTitle("Jeu");
			theStage.setScene(scene);
			theStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void generateUnivers() {
		System.out.println("Génération univers !");
		ArrayList<String> univers = Jeu.getUnivers();
		Collections.shuffle(univers);
		affectationUnivers(univers);

	}

	private void affectationUnivers(ArrayList<String> univers) {
		Jeu.setUnivers(univers);
		btn1.setText(univers.get(0));
		btn2.setText(univers.get(1));
		btn3.setText(univers.get(2));
		btn4.setText(univers.get(3));
		if (Jeu.getNbUnivers() == 5) {
			btn5.setText(univers.get(4));
			Jeu.setUnivers(new ArrayList<>(Jeu.getUnivers().subList(0, 5)));
		} else {
			Jeu.setUnivers(new ArrayList<>(Jeu.getUnivers().subList(0, 4)));
		}
	}

	@FXML
	protected void initialize() {
		System.out.println(Jeu.getNbUnivers());
		if (Jeu.getNbUnivers() == 4) {
			btn5.setVisible(false);
		} else {
			btn5.setVisible(true);
		}
		ArrayList<String> univers = Jeu.getUnivers();
		affectationUnivers(univers);
		eNbEssais.setText(Integer.toString(Jeu.getnbEssais()));
		eNbItems.setText(Integer.toString(Jeu.getNbItem()));
	}

}
