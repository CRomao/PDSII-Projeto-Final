package br.com.pickshow.controller;

import java.io.IOException;

import br.com.pickshow.view.ConversaSelecionadaProfissionalChatView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConversasProfissionalController {

	@FXML
	public Button btnVisualizar;
	
	
	
	@FXML
	public void actionBtnVisualizar() {
		try {
			new ConversaSelecionadaProfissionalChatView().start(new Stage());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
