package br.com.pickshow.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.pickshow.model.ConnectionFactory;
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
		String[] tipo = { "Profissional", "Cliente" };
		comboBoxEscolha.getItems().removeAll(tipo);
		comboBoxEscolha.getItems().addAll(tipo);

	}

	@FXML
	public void actionBtnEntrar() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText(verificarCampos());
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

	public String verificarCampos() {
		if (txtEmail.getText().equals("")) {
			return "Preencha o seu Email.";
		} else if (txtSenha.getText().equals("")) {
			return "Preencha sua senha.";
		} else if (comboBoxEscolha.getSelectionModel().getSelectedIndex() == -1) {
			return "Escolha que tipo de usuário você é.";
		} else {
			return conectar();
		}
	}

	public String conectar() {
		try {
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement ps;
			
			int tipo = comboBoxEscolha.getSelectionModel().getSelectedIndex();
			String sql = "";
			
			if (tipo == 0) {
				sql = "SELECT * FROM AD_profissional where email = '" + txtEmail.getText() + "' AND senha = '"
						+ txtSenha.getText() + "';";
			} else if (tipo == 1) {
				sql = "SELECT * FROM AD_cliente where email = '" + txtEmail.getText() + "' AND senha = '"
						+ txtSenha.getText() + "';";
			}

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return "Login realizado com sucesso!";
			} else {
				return "Email ou senha inválidos.";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Conexão com o banco não estabelecida.";
	}

}
