package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.print.DocFlavor.INPUT_STREAM;

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
			Parent root = FXMLLoader.load(getClass().getResource("/MenuPrinc.fxml"));
		    
	        Scene scene = new Scene(root);
	    
	        stage.setTitle("FXML Welcome");
	        stage.setScene(scene);
	        stage.show();
	        
	        URL url = getClass().getResource("./ressources/enigme.txt");
	        InputStream is = Main.class.getResourceAsStream("/enigme.txt");
	        //File file = new File(url.toString()); //lecture du JSON changer le chemin avec celui de chez vou, j'ai mis l'absolu parce que le relatif ne marche pas
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        
	       String st = "";
	       while (true) {
	    	    final String line = br.readLine();
	    	    if (line == null) break;
	    	    
	    	    st += line;
	    	}
	        
	        System.out.println(st);
	        
	        JSONArray a = new JSONArray(st);
	        Jeu.setEnigmes(a);
	        br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
