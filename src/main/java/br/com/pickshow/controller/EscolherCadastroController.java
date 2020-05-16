package br.com.pickshow.controller;

import java.io.IOException;

import br.com.pickshow.padroes.CadastroFactory;
import br.com.pickshow.view.CadastroCliente;
import br.com.pickshow.view.CadastroProfissional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class EscolherCadastroController {

	CadastroFactory cadastroFactory = new CadastroFactory();

	@FXML
	public Button btnCadastrarProfiss;
	@FXML
	public Button btnCadastrarCliente;
	@FXML
	public Button btnVoltar;

	@FXML
	public void btnCadastrarProfiss() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText("Lista de Usuários serializada!");
		alert.showAndWait();
	}

	@FXML
	public void actionBtnVoltar() {
		Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void actionBtnCadastrarProfiss() throws IOException {
		cadastroFactory.criarCadastro(CadastroFactory.PROFISSIONAL);
	}

	@FXML
	public void actionBtnCadastrarCliente() throws IOException {
		cadastroFactory.criarCadastro(CadastroFactory.CLIENTE);
	}

}
