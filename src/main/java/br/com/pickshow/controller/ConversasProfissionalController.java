package br.com.pickshow.controller;

import java.io.IOException;

import br.com.pickshow.view.ConversaSelecionadaProfissionalChatView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe para apenas abrir a tela de chat do profissional, seria implementado
 * uma solução dinâmica para o chat, mas era um pouco complexo.
 * 
 * @author Cicero Romão e Eduardo Marculino
 * 
 */
public class ConversasProfissionalController {

	@FXML
	public Button btnVisualizar;

	/**
	 * Método para abrir a tela do chat do profissional, quando ele clicar em
	 * 'visualizar' na mensagem do cliente recebida.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnVisualizar() {
		try {
			new ConversaSelecionadaProfissionalChatView().start(new Stage());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
