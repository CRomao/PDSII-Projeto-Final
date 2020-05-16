package br.com.pickshow.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class CadastroClienteController {

	@FXML
	public Button btnCadastrarProfiss;
	@FXML
	public Button btnCadastrarCliente;
	@FXML
	public Button btnCancelar;

	@FXML
	public void actionBtnCancelar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void btnCadastrarProfiss() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText("Lista de Usuários serializada!");
		alert.showAndWait();
	}

}
