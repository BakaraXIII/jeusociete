package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/ihm/MenuPrinc.fxml"));
		    
	        Scene scene = new Scene(root);
	    
	        stage.setTitle("FXML Welcome");
	        stage.setScene(scene);
	        stage.show();
	        
	        File file = new File("E:\\Git\\jeusociete\\src\\ressources\\enigme.json"); //lecture du JSON changer le chemin avec celui de chez vou, j'ai mis l'absolu parce que le relatif ne marche pas
	        
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        
	       String st = "";
	       while (true) {
	    	    final String line = br.readLine();
	    	    if (line == null) break;
	    	    
	    	    st += line;
	    	}
	        
	        System.out.println(st);
	        
	        JSONArray a = new JSONArray(st);
	        br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
