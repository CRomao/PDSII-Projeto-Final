package br.com.pickshow.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;

import br.com.pickshow.chat.ReceberDoServidor;
import br.com.pickshow.controller.VisualizarLocaisController.Local;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Classe controller para ter o controle da tela onde o cliente entra no chat
 * com o profissional, implementando a interface Initializable, para alterar os
 * campos assim que a tela for iniciada.
 * 
 * @author Cicero Romão e Eduardo Marculino
 * 
 */
public class LocalSelecionadoClienteChatController implements Initializable {

	private Socket cliente;
	private BufferedReader veioDoServidor;
	private DataOutputStream vaiPraServidor;

	@FXML
	public Label lblNomeLocal;
	@FXML
	public Label lblRuaLocal;
	@FXML
	public Label lblTelefoneContato;
	@FXML
	public TextArea txtAreaInformacoes;
	@FXML
	public Button btnEnviarMsg;
	@FXML
	public TextField txtMsg;
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
	 * Método para poder alterar o conteúdo dos campos assim que a tela for
	 * 'iniciada' para a tela do chat, alterando alguns campos do local e fazendo a
	 * seleção de alguns dados do banco, como o nome do usuário, para poder setar no
	 * chat. Aqui ele cria um Socket, para poder ter acesso ao chat e inicia uma
	 * thread para poder receber as informações que estão passando pelo servidor.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Local local = new VisualizarLocaisController().localSelecionado;
		lblNomeLocal.setText(local.getNomeLocal());
		lblTelefoneContato.setText(local.getTelefoneLocal());
		lblRuaLocal.setText(local.getRuaLocal());
		imgLocal.setImage(new Image(new File("img/fotoLocal.jpg").toURI().toString()));
		imgRecomend.setImage(new Image(new File("img/festaPiseiro.jpg").toURI().toString()));
		try {
			cliente = new Socket("127.0.0.1", 8001);

			vaiPraServidor = new DataOutputStream(cliente.getOutputStream());

			ReceberDoServidor t = new ReceberDoServidor(cliente, txtAreaInformacoes);
			t.start();

			vaiPraServidor.writeBytes(LoginController.pegarNomeUsuario() + '\n');

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método para poder controlar quando o usuário clicar em enviar a mensagem,
	 * fazendo com que chame a função de enviarMsg(), para enviar a mensagem.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnEnviarMsg() {
		enviarMsg();
	}

	/**
	 * Método para poder enviar a mensagem no fluxo estabelecido, onde ele faz o
	 * envio do conteúdo que está no TextField, e joga o mesmo no TextArea,
	 * simulando um chat.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	public void enviarMsg() {

		// Verifica se o TextField está vazio, se tiver avisa ao cliente para digitar
		// algo.
		if (txtMsg.getText().trim().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informação");
			alert.setContentText("Digite algo antes de enviar!");
			alert.showAndWait();
			return;

		}

		// Se o cliente digitar algo, ele manda a mensagem para o servidor e atualiza o
		// TextArea.
		try {
			vaiPraServidor.writeBytes(txtMsg.getText() + '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}

		txtAreaInformacoes.appendText("> " + txtMsg.getText() + "\n");

		// Se o cliente digitar "sair", desabilita o botao de enviar e o TextField.
		if (txtMsg.getText().equalsIgnoreCase("sair")) {
			btnEnviarMsg.setDisable(true);
			txtMsg.setDisable(true);
		}

		txtMsg.setText("");

	}

}
