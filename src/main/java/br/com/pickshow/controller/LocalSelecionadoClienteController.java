package br.com.pickshow.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.controller.VisualizarLocaisController.Local;
import br.com.pickshow.view.LocalSelecionadoClienteChatView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class LocalSelecionadoClienteController implements Initializable {

	@FXML
	public Label lblNomeLocal;
	@FXML
	public Label lblRuaLocal;
	@FXML
	public Label lblTelefoneContato;
	@FXML
	public TextArea txtAreaInformacoes;
	@FXML
	public Label lblAbrirChat;
	@FXML
	public Button btnCancelar;

	@FXML
	public void actionBtnCancelar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void actionLblAbrirChat() {
		try {
			new LocalSelecionadoClienteChatView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) lblAbrirChat.getScene().getWindow();
		stage.close();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Local local = new VisualizarLocaisController().localSelecionado;
		lblNomeLocal.setText(local.getNomeLocal());
		lblTelefoneContato.setText(local.getTelefoneLocal());
		lblRuaLocal.setText(local.getRuaLocal());

	}

}
