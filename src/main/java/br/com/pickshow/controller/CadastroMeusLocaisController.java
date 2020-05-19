package br.com.pickshow.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.model.CadastroMeusLocaisModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class CadastroMeusLocaisController implements VerificarCampos, Initializable {

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
			if (MeusLocaisController.verifyButton == 1) {
				MeusLocaisController.verifyButton = 0;
				return CadastroMeusLocaisModel.insert(txtNomeLocal.getText(), txtRuaLocal.getText(),
						txtTelefoneContato.getText(), txtAreaInformacoes.getText(),
						comboBoxEscolhaArea.getSelectionModel().getSelectedIndex(), LoginController.pegarIdusuario());

			} else {
				return CadastroMeusLocaisModel.update(txtNomeLocal.getText(), txtRuaLocal.getText(),
						txtTelefoneContato.getText(), txtAreaInformacoes.getText(),
						comboBoxEscolhaArea.getSelectionModel().getSelectedIndex(), LoginController.pegarIdusuario());
			}

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (MeusLocaisController.verifyButton != 1) {
			txtNomeLocal.setText(MeusLocaisController.localSelecionado.getNomeLocal());
			txtRuaLocal.setText(MeusLocaisController.localSelecionado.getRuaLocal());
			txtTelefoneContato.setText(MeusLocaisController.localSelecionado.getTelefoneLocal());
			//txtAreaInformacoes.setText(MeusLocaisController.localSelecionado.getCod());
			comboBoxEscolhaArea.getSelectionModel().select(CadastroMeusLocaisModel.selectIdTipoLocal(
					MeusLocaisController.localSelecionado.getTipoLocal()));
			btnCadastrarLocal.setText("Salvar");
			
			comboBoxEscolhaArea.getSelectionModel().select
		}

	}

}
