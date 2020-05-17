package br.com.pickshow.controller;

import java.io.IOException;

import br.com.pickshow.model.LoginModel;
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
public class LoginController implements VerificarCampos{

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
		String[] tipo = { "Profissional", "Cliente" };
		comboBoxEscolha.getItems().removeAll(tipo);
		comboBoxEscolha.getItems().addAll(tipo);

	}

	@FXML
	public void actionBtnEntrar() {
		String msg = verificarCampos();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText(msg);
		alert.showAndWait();
		
		if(msg.equals("Login realizado com sucesso!")) {
			Stage stage = (Stage) btnEntrar.getScene().getWindow();
			stage.close();
			//Colocar para abrir a tela HOME
		}
	}

	@FXML
	public void onMouseClickedLblCadastrar() {
		try {
			new EscolherCadastro().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String verificarCampos() {
		if (txtEmail.getText().equals("")) {
			return "Preencha o seu Email.";
		} else if (txtSenha.getText().equals("")) {
			return "Preencha sua senha.";
		} else if (comboBoxEscolha.getSelectionModel().getSelectedIndex() == -1) {
			return "Escolha que tipo de usuário você é.";
		} else {
			return LoginModel.conectar(comboBoxEscolha.getSelectionModel().getSelectedIndex(),
					txtEmail.getText(), txtSenha.getText());
		}
	}

}
