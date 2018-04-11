package application;

import java.util.Collections;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
		btn5.setText(univers.get(4));
	}
	
}
