package br.com.pickshow.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.model.LoginModel;
import br.com.pickshow.padroes.VerificarCampos;
import br.com.pickshow.serializacao.DesserializarListaUsuario;
import br.com.pickshow.serializacao.SerializarListaUsuario;
import br.com.pickshow.serializacao.Usuario;
import br.com.pickshow.view.EscolherCadastroView;
import br.com.pickshow.view.HomeView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

//Classe para o controle dos Cadastros.
public class LoginController implements VerificarCampos, Initializable {

	@FXML
	public TextField txtEmail;
	@FXML
	public TextField txtSenha;
	@FXML
	public Button btnEntrar;
	@FXML
	public Label lblCadastrar;
	@FXML
	public ComboBox comboBoxEscolha;
	@FXML
	public CheckBox checkConectado;

	public static String pegarNomeUsuario() {
		return LoginModel.pegarNomeUsuario();
	}

	public static int pegarIdusuario() {
		return LoginModel.pegarIdusuario();
	}

	@FXML
	public void actionComboBoxEscolha() {
		String[] tipo = { "Profissional", "Cliente" };
		comboBoxEscolha.getItems().removeAll(tipo);
		comboBoxEscolha.getItems().addAll(tipo);

	}

	@FXML
	public void actionBtnEntrar() {
		String msg = verificarCampos();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText(msg);
		alert.showAndWait();

		if (msg.equals("Login realizado com sucesso!")) {
			Stage stage = (Stage) btnEntrar.getScene().getWindow();
			stage.close();
			try {

				new HomeView().start(new Stage());
				ser();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void onMouseClickedLblCadastrar() {
		try {
			new EscolherCadastroView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String verificarCampos() {
		if (txtEmail.getText().equals("")) {
			return "Preencha o seu Email.";
		} else if (txtSenha.getText().equals("")) {
			return "Preencha sua senha.";
		} else if (comboBoxEscolha.getSelectionModel().getSelectedIndex() == -1) {
			return "Escolha que tipo de usuário você é.";
		} else {
			return LoginModel.conectar(comboBoxEscolha.getSelectionModel().getSelectedIndex(), txtEmail.getText(),
					txtSenha.getText());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			des();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void ser() {
		if (checkConectado.isSelected()) {
			Usuario usu = new Usuario(txtEmail.getText(), txtSenha.getText(), checkConectado.isSelected());
			SerializarListaUsuario ser = new SerializarListaUsuario(usu);
			ser.serializar();
			FileWriter arq = null;
			try {
				arq = new FileWriter("verificacao.txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.println(1);
				arq.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			FileWriter arq = null;
			try {
				arq = new FileWriter("verificacao.txt");
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.println(0);
				arq.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void des() throws IOException {
		int flag = 0;
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader("verificacao.txt"));
			flag = Integer.parseInt(buffRead.readLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (flag == 1) {
			DesserializarListaUsuario desser = new DesserializarListaUsuario("arquivo.ser");
			desser.desserializar();
			Usuario usu = new Usuario("", "", false);
			usu = desser.getLista();

			if (usu.isConectado()) {
				txtEmail.setText(usu.getNome());
				txtSenha.setText(usu.getSenha());
				checkConectado.setSelected(usu.isConectado());
			}
		}
	}

}
