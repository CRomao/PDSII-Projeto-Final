package br.com.pickshow.controller;

import java.io.IOException;

import br.com.pickshow.view.CadastroMeusLocais;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class MeusLocaisController {

	public Button btnCadastrar;
	@FXML
	public Button btnAlterar;
	@FXML
	public Button btnDeletar;
	@FXML
	public Button btnVoltar;
	@FXML
	public ComboBox comboBoxPesquisa;

	@FXML
	public void actionBtnVoltar() {
		Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void actionBtnCadastrar() {
		try {
			new CadastroMeusLocais().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void actioncomboBoxPesquisa() {
		String[] areas = {"ID", "Nome local", "Rua local"};
		comboBoxPesquisa.getItems().removeAll(areas);
		comboBoxPesquisa.getItems().addAll(areas);

	}

}
