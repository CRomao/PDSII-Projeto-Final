package br.com.pickshow.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe responsável por carregar a tela de Escolher o tipo de Cadastro
 * 
 * @author Eduardo Marculino
 * 
 */

public class EscolherCadastroView extends Application {

	private static Scene scene;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		scene = new Scene(loadFXML("escolher_cadastro"));
		stage.setScene(scene);
		stage.setTitle("Escolher tipo de Cadastro");
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
}
