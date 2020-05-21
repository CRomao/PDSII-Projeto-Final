package br.com.pickshow.controller;

import br.com.pickshow.model.CadastrarClienteModel;
import br.com.pickshow.padroes.VerificarCampos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class CadastrarClienteController implements VerificarCampos {

	@FXML
	public TextField txtNome;
	@FXML
	public TextField txtSobreNome;
	@FXML
	public TextField txtEmail;
	@FXML
	public TextField txtSenha;
	@FXML
	public TextField txtCpf;
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
	public void actionBtnCadastrarCliente() {
		String msg = verificarCampos();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText(msg);
		alert.showAndWait();

		if (msg.equals("Cadastro realizado com sucesso!")) {
			Stage stage = (Stage) btnCadastrarCliente.getScene().getWindow();
			stage.close();
		}
	}

	@Override
	public String verificarCampos() {
		if (txtNome.getText().equals("")) {
			return "Preencha o seu nome.";
		} else if (txtSobreNome.getText().equals("")) {
			return "Preencha seu sobrenome.";
		} else if (txtEmail.getText().equals("")) {
			return "Preencha seu email.";
		} else if (txtSenha.getText().equals("")) {
			return "Preencha sua senha.";
		} else if (txtCpf.getText().equals("")) {
			return "Preencha seu CPF.";
		} else {
			return CadastrarClienteModel.insert(txtNome.getText(), txtSobreNome.getText(), txtEmail.getText(),
					txtSenha.getText(), txtCpf.getText());
		}
	}

}
