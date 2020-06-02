package br.com.pickshow.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.interfaces.VerificarCampos;
import br.com.pickshow.model.CadastrarLocaisModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Classe controller para cadastrar cliente, onde terá os métodos de verificação
 * dos campos da tela, fechamento do Stage, a ação sobre o botão cadastrar, o
 * preenchimento do comboBox do tipo de área do local e o método initialize,
 * pois dependendo do status da flag LocaisProfissionalController.verifyButton,
 * esta tela pode ser de cadastro ou alteração. A classe implementa as
 * interfaces VerificarCampos e Initializable.
 * 
 * @author Cicero Romão e Eduardo Marculino
 * 
 */
public class CadastrarLocaisController implements VerificarCampos, Initializable {

	@SuppressWarnings("exports")
	@FXML
	public TextField txtNomeLocal;
	@SuppressWarnings("exports")
	@FXML
	public TextField txtRuaLocal;
	@SuppressWarnings("exports")
	@FXML
	public TextField txtTelefoneContato;
	@SuppressWarnings("exports")
	@FXML
	public TextArea txtAreaInformacoes;
	@FXML
	public ComboBox<String> comboBoxEscolhaArea;
	@SuppressWarnings("exports")
	@FXML
	public Button btnCadastrarLocal;
	@SuppressWarnings("exports")
	@FXML
	public Button btnCancelar;
	@SuppressWarnings("exports")
	@FXML
	public ImageView imgLocal;

	/**
	 * Método para poder verificar quando o usuário clicou em cancelar, para poder
	 * capturar a tela atual e fechar ela.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnCancelar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	/**
	 * Método para poder verificar os campos da tela, para saber se ficou algum
	 * campo ser preenchido. Caso todos estejam preencidos e selecionados, é chamado
	 * o método 'insert' ou 'update' da classe CadastrarLocaisModel - dependendo da
	 * flag LocaisProfissionalController.verifyButton - passando o conteúdo dos
	 * campos da tela, e que terá um retorno em String, mostrando se o cadastro deu
	 * certo.
	 * 
	 * @author Cicero Romão
	 * 
	 * @return String - Dependendo de qual condição caia, será informado uma
	 *         mensagem específica.
	 */
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
						(comboBoxEscolhaArea.getSelectionModel().getSelectedIndex() + 1),
						LoginController.pegarIdusuario());

			} else {
				return CadastrarLocaisModel.update(txtNomeLocal.getText(), txtRuaLocal.getText(),
						txtTelefoneContato.getText(), txtAreaInformacoes.getText(),
						(comboBoxEscolhaArea.getSelectionModel().getSelectedIndex() + 1),
						LocaisProfissionalController.localSelecionado.getCod());
			}

		}
	}

	/**
	 * Método para poder controlar a ação do botão de cadastrar na tela de cadastro
	 * do local, onde ele faz chamada da função de verificarCampos(), para ver se os
	 * campos foram preenchidos. Se tiver cadastrado ou alterado, ele fecha a tela.
	 * 
	 * @author Cicero Romão
	 * 
	 */
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

	/**
	 * Método para poder popular o comboBox de tipo de área do local.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionComboBoxEscolhaArea() {
		String[] areas = { "Aniversário", "Casamento", "Confraternização", "Festa", "Lazer" };
		comboBoxEscolhaArea.getItems().removeAll(areas);
		comboBoxEscolhaArea.getItems().addAll(areas);

	}

	/**
	 * Método para poder alterar o conteúdo dos campos assim que a tela for
	 * 'iniciada', alterando alguns campos do local e fazendo a seleção de alguns
	 * dados do banco.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LocaisProfissionalController.verifyButton != 1) {
			txtNomeLocal.setText(LocaisProfissionalController.localSelecionado.getNomeLocal());
			txtRuaLocal.setText(LocaisProfissionalController.localSelecionado.getRuaLocal());
			txtTelefoneContato.setText(LocaisProfissionalController.localSelecionado.getTelefoneLocal());
			txtAreaInformacoes.setText(CadastrarLocaisModel
					.selectInformacoesLocal(LocaisProfissionalController.localSelecionado.getCod()));
			comboBoxEscolhaArea.setValue(LocaisProfissionalController.localSelecionado.getTipoLocal());
			btnCadastrarLocal.setText("Salvar");

		}
		imgLocal.setImage(new Image(new File("img/fotoLocal.jpg").toURI().toString()));

	}

}
