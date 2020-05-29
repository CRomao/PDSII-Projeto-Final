package br.com.pickshow.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import br.com.pickshow.controller.VisualizarLocaisController.Local;
import br.com.pickshow.view.LocalSelecionadoClienteChatView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Classe controller para servir como uma entrada para a tela onde o cliente
 * entra no chat com o profissional, implementando a interface Initializable,
 * para alterar os campos assim que a tela for iniciada.
 * 
 * @author Cicero Romão e Eduardo Marculino
 * 
 */
public class LocalSelecionadoClienteController implements Initializable {

	@FXML
	public Label lblNomeLocal;
	@FXML
	public Label lblRuaLocal;
	@FXML
	public Label lblTelefoneContato;
	@FXML
	public TextArea txtAreaInformacoes;
	@FXML
	public Label lblAbrirChat;
	@FXML
	public Button btnCancelar;
	@FXML
	public ImageView imgLocal;
	@FXML
	public ImageView imgRecomend;

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
	 * Método para poder verificar quando o usuário clicou na label 'clique aqui',
	 * abrir o chat com o profissional.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionLblAbrirChat() {
		try {
			new LocalSelecionadoClienteChatView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) lblAbrirChat.getScene().getWindow();
		stage.close();

	}

	/**
	 * Método para poder alterar o conteúdo dos campos assim que a tela for
	 * 'iniciada' para a tela de entrada do chat, alterando alguns campos do local.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Local local = VisualizarLocaisController.localSelecionado;
		lblNomeLocal.setText(local.getNomeLocal());
		lblTelefoneContato.setText(local.getTelefoneLocal());
		lblRuaLocal.setText(local.getRuaLocal());
		imgLocal.setImage(new Image(new File("img/fotoLocal.jpg").toURI().toString()));
		imgRecomend.setImage(new Image(new File("img/festaPiseiro.jpg").toURI().toString()));

	}

}
