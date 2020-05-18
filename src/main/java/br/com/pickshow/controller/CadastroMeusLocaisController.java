package br.com.pickshow.controller;

import br.com.pickshow.model.CadastroMeusLocaisModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class CadastroMeusLocaisController implements VerificarCampos {

	@FXML
	public TextField txtNomeLocal;
	@FXML
	public TextField txtRuaLocal;
	@FXML
	public TextField txtTelefoneContato;
	@FXML
	public TextArea txtAreaInformacoes;
	@FXML
	public ComboBox comboBoxEscolhaArea;
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
		String msg = verificarCampos();
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

	@FXML
	public void actionComboBoxEscolhaArea() {
		String[] areas = { "Aniversário", "Casamento", "Confraternização", "Festa", "Lazer" };
		comboBoxEscolhaArea.getItems().removeAll(areas);
		comboBoxEscolhaArea.getItems().addAll(areas);

	}

	@Override
	public String verificarCampos() {
		if (txtNomeLocal.getText().equals("")) {
			return "Preencha o nome do local.";
		} else if (txtRuaLocal.getText().equals("")) {
			return "Preencha a rua do local.";
		} else if (txtTelefoneContato.getText().equals("")) {
			return "Preencha o DDD e número do telefone para contato.";
		} else if (txtAreaInformacoes.getText().equals("")) {
			return "Descreva as informações do local.";
		} else if (comboBoxEscolhaArea.getSelectionModel().getSelectedIndex() == -1) {
			return "Escolha o tipo do local.";
		} else {
			return CadastroMeusLocaisModel.conectar(txtNomeLocal.getText(), txtRuaLocal.getText(), txtTelefoneContato.getText(),
					txtAreaInformacoes.getText(),
					comboBoxEscolhaArea.getSelectionModel().getSelectedIndex(), LoginController.pegarIdusuario());
		}
	}

}
