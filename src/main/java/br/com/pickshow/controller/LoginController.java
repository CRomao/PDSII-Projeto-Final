package br.com.pickshow.controller;

import java.io.IOException;

import br.com.pickshow.view.EscolherCadastro;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class LoginController {

	@FXML
	public TextField txtEmail;
	@FXML
	public TextField txtSenha;
	@FXML
	public Button btnEntrar;
	@FXML
	public Label lblCadastrar;
	@FXML
	public ComboBox comboBoxEscolha;

	@FXML
	public void actionComboBoxEscolha() {
		String[] tipo = {"Profissional", "Cliente"};
		comboBoxEscolha.getItems().removeAll(tipo);
		comboBoxEscolha.getItems().addAll(tipo);

	}

	@FXML
	public void actionBtnEntrar() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText("Lista de Usuários serializada!");
		alert.showAndWait();
	}

	@FXML
	public void onMouseClickedLblCadastrar() {
		try {
			new EscolherCadastro().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
