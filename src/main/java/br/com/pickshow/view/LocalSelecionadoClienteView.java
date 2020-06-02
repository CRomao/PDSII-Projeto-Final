package br.com.pickshow.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe responsável por carregar a tela Local Selecionado
 * 
 * @author Cicero Romão
 * 
 */

public class LocalSelecionadoClienteView extends Application {

	private static Scene scene;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		scene = new Scene(loadFXML("local_selecionado_cliente"));
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Local Selecionado");
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
