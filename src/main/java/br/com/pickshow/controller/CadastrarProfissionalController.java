package br.com.pickshow.controller;

import br.com.pickshow.interfaces.VerificarCampos;
import br.com.pickshow.model.CadastrarProfissionalModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Classe controller para cadastrar o profissional, onde terá os métodos de
 * verificação dos campos da tela, fechamento do Stage, a ação sobre o botão
 * cadastrar e a população do comboBox da área de atuação do profissional .
 * 
 * @author Cicero Romão
 * 
 */
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
	 * o método 'insert' da classe CadastrarProfissionalModel, passando o conteúdo dos
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
			return CadastrarProfissionalModel.insert(txtNome.getText(), txtSobreNome.getText(), txtEmail.getText(),
					txtSenha.getText(), txtCpfCnpj.getText(),
					comboBoxEscolhaArea.getSelectionModel().getSelectedIndex());
		}
	}

	/**
	 * Método para poder controlar a ação do botão de cadastrar na tela de cadastro
	 * de profissional, onde ele faz chamada da função de verificarCampos(), para ver se
	 * os campos foram preenchidos.
	 * 
	 * @author Cicero Romão
	 * 
	 */
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

	/**
	 * Método para poder popular o comboBox da área de atuação do profissional.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionComboBoxEscolhaArea() {
		String[] areas = { "Fotógrafo(a)", "Desenhista", "Cozinheiro(a)", "Pianista" };
		comboBoxEscolhaArea.getItems().removeAll(areas);
		comboBoxEscolhaArea.getItems().addAll(areas);

	}

}
