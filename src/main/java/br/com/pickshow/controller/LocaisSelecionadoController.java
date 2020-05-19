package br.com.pickshow.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.controller.LocaisController.Local;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class LocaisSelecionadoController implements Initializable {

	@FXML
	public Label lblNomeLocal;
	@FXML
	public Label lblRuaLocal;
	@FXML
	public Label lblTelefoneContato;
	@FXML
	public TextArea txtAreaInformacoes;
	@FXML
	public Button btnCadastrarLocal;
	@FXML
	public Button btnCancelar;

	@FXML
	public void actionBtnCancelar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void actionBtnCadastrarLocal() {
		String msg = "";
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText(msg);
		alert.showAndWait();

		if (msg.equals("Cadastro realizado com sucesso!")) {
			Stage stage = (Stage) btnCadastrarLocal.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Local local = new LocaisController().localSelecionado;
		lblNomeLocal.setText(local.getNomeLocal());
		lblTelefoneContato.setText(local.getTelefoneLocal());
		lblRuaLocal.setText(local.getRuaLocal());
		
		
	}

}
