package br.com.pickshow.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.model.CadastrarLocaisModel;
import br.com.pickshow.padroes.VerificarCampos;
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
public class CadastrarLocaisController implements VerificarCampos, Initializable {

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

		if (msg.equals("Cadastro realizado com sucesso!") || msg.equals("Alterado com sucesso!")) {
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
			if (LocaisProfissionalController.verifyButton == 1) {
				LocaisProfissionalController.verifyButton = 0;
				return CadastrarLocaisModel.insert(txtNomeLocal.getText(), txtRuaLocal.getText(),
						txtTelefoneContato.getText(), txtAreaInformacoes.getText(),
						(comboBoxEscolhaArea.getSelectionModel().getSelectedIndex()+1), LoginController.pegarIdusuario());

			} else {
				return CadastrarLocaisModel.update(txtNomeLocal.getText(), txtRuaLocal.getText(),
						txtTelefoneContato.getText(), txtAreaInformacoes.getText(),
						(comboBoxEscolhaArea.getSelectionModel().getSelectedIndex()+1), LocaisProfissionalController.localSelecionado.getCod());
			}

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LocaisProfissionalController.verifyButton != 1) {
			System.out.println("Aqui");
			txtNomeLocal.setText(LocaisProfissionalController.localSelecionado.getNomeLocal());
			txtRuaLocal.setText(LocaisProfissionalController.localSelecionado.getRuaLocal());
			txtTelefoneContato.setText(LocaisProfissionalController.localSelecionado.getTelefoneLocal());
			txtAreaInformacoes.setText(CadastrarLocaisModel.selectInformacoesLocal(LocaisProfissionalController.localSelecionado.getCod()));
			comboBoxEscolhaArea.setValue(LocaisProfissionalController.localSelecionado.getTipoLocal());;
			btnCadastrarLocal.setText("Salvar");
			
		}

	}

}
