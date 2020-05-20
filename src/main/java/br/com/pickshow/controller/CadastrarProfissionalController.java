package br.com.pickshow.controller;

import br.com.pickshow.model.CadastrarProfissionalModel;
import br.com.pickshow.padroes.VerificarCampos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class CadastrarProfissionalController implements VerificarCampos {

	@FXML
	public TextField txtNome;
	@FXML
	public TextField txtSobreNome;
	@FXML
	public TextField txtEmail;
	@FXML
	public TextField txtSenha;
	@FXML
	public TextField txtCpfCnpj;
	@FXML
	public Button btnCadastrarProfissional;
	@FXML
	public Button btnCancelar;
	@FXML
	public ComboBox comboBoxEscolhaArea;

	@FXML
	public void actionBtnCancelar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void actionBtnCadastrarProfiss() {
		String msg = verificarCampos();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText(msg);
		alert.showAndWait();

		if (msg.equals("Cadastro realizado com sucesso!")) {
			Stage stage = (Stage) btnCadastrarProfissional.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	public void actionComboBoxEscolhaArea() {
		String[] areas = { "Fotógrafo(a)", "Desenhista", "Cozinheiro(a)", "Pianista" };
		comboBoxEscolhaArea.getItems().removeAll(areas);
		comboBoxEscolhaArea.getItems().addAll(areas);

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
		} else if (txtCpfCnpj.getText().equals("")) {
			return "Preencha seu CPF ou CNPJ.";
		} else if (comboBoxEscolhaArea.getSelectionModel().getSelectedIndex() == -1) {
			return "Escolha a área de sua atuação.";
		} else {
			return CadastrarProfissionalModel.conectar(txtNome.getText(), txtSobreNome.getText(), txtEmail.getText(),
					txtSenha.getText(), txtCpfCnpj.getText(),
					comboBoxEscolhaArea.getSelectionModel().getSelectedIndex());
		}
	}

}
