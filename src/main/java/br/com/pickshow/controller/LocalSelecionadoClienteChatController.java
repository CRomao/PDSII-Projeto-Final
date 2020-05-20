package br.com.pickshow.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
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
	public void actionBtnCancelar() {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Local local = new VisualizarLocaisController().localSelecionado;
		lblNomeLocal.setText(local.getNomeLocal());
		lblTelefoneContato.setText(local.getTelefoneLocal());
		lblRuaLocal.setText(local.getRuaLocal());

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

	@FXML
	public void actionBtnEnviarMsg() {
		enviarMsg();
	}

	public void enviarMsg() {

		// Verifica se o TextField está vazio, se tiver avisa ao cliente para digitar
		// algo.
		if (txtMsg.getText().trim().equals("")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
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
