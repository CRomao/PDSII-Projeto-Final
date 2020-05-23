package br.com.pickshow.controller;

import java.io.IOException;

import br.com.pickshow.padroes.CadastroFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Classe para poder escolher qual o cadastro de usuário que a pessoa pode
 * escolher, se é profissional ou cliente.
 * 
 * @author Cicero Romão
 * @see A classe CadastroFactory, pois é nela que faz a chamada da classe do
 *      cadastro que o usuário escolheu.
 */
public class EscolherCadastroController {

	CadastroFactory cadastroFactory = new CadastroFactory();

	@FXML
	public Button btnCadastrarProfiss;
	@FXML
	public Button btnCadastrarCliente;
	@FXML
	public Button btnVoltar;

	/**
	 * Método para poder verificar quando o usuário clicou em cancelar, para poder
	 * capturar a tela atual e fechar ela.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnVoltar() {
		Stage stage = (Stage) btnVoltar.getScene().getWindow();
		stage.close();
	}

	/**
	 * Método para passar para a fábrica de cadastro que é para criar um cadastro de
	 * profissional.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnCadastrarProfiss() throws IOException {
		cadastroFactory.criarCadastro(CadastroFactory.PROFISSIONAL);
		actionBtnVoltar();
	}

	/**
	 * Método para passar para a fábrica de cadastro que é para criar um cadastro de
	 * cliente.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnCadastrarCliente() throws IOException {
		cadastroFactory.criarCadastro(CadastroFactory.CLIENTE);
		actionBtnVoltar();
	}

}
