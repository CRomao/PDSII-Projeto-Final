package br.com.pickshow.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe responsável por carregar a tela da Conversa que o Profissional
 * selecionou para visualizar.
 * 
 * @author Cicero Romão
 * 
 */

public class ConversaSelecionadaProfissionalChatView extends Application {

	private static Scene scene;

	@Override
	public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
		scene = new Scene(loadFXML("conversa_selecionada_profissional_chat"));
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Conversa Selecionada Chat");
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
