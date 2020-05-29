package br.com.pickshow.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.pickshow.interfaces.VerificarCampos;
import br.com.pickshow.model.LoginModel;
import br.com.pickshow.serializacao.DesserializarUsuario;
import br.com.pickshow.serializacao.SerializarUsuario;
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

/**
 * Classe controller para o login, onde é a primeira tela a ser chamada na
 * aplicação.
 * 
 * @author Cicero Romão e Eduardo Marculino
 * 
 */
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
	public static int tipoUsuario;

	/**
	 * Método para retornar o nome do usuário, pelo método pegarNomeUsuario da
	 * classe LoginModel.
	 * 
	 * @author Cicero Romão
	 * @return String - Nome do usuário logado.
	 */
	public static String pegarNomeUsuario() {
		return LoginModel.pegarNomeUsuario();
	}

	/**
	 * Método para retornar o id do usuário, pelo método pegarIdusuario da classe
	 * LoginModel.
	 * 
	 * @author Cicero Romão
	 * @return int - ID do usuário logado.
	 */
	public static int pegarIdusuario() {
		return LoginModel.pegarIdusuario();
	}

	/**
	 * Método para popular o comboBox do tipo de usuário, onde ao clicar nele,
	 * sempre é removido os valores que tem e em seguida inseridos novamente.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionComboBoxEscolha() {
		String[] tipo = { "Profissional", "Cliente" };
		comboBoxEscolha.getItems().removeAll(tipo);
		comboBoxEscolha.getItems().addAll(tipo);

	}

	/**
	 * Método para poder controlar a ação do botão de entrar na tela de login, onde
	 * ele faz chamada da função de verificarCampos(), para ver se os campos foram
	 * preenchidos. Se sim, ele chama a tela home.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void actionBtnEntrar() {
		String msg = verificarCampos();

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Informação");
		alert.setContentText(msg);
		alert.showAndWait();

		if (msg.equals("Login realizado com sucesso!")) {
			if(comboBoxEscolha.getSelectionModel().getSelectedItem().equals("Cliente"))tipoUsuario = 1;
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

	/**
	 * Método para poder abrir a tela de escolher o cadastro que o usuário quer
	 * realizar. Isso ocorre quando ele clica na label 'quero me cadastrar.'.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@FXML
	public void onMouseClickedLblCadastrar() {
		try {
			new EscolherCadastroView().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para poder verificar os campos da tela, para saber se ficou algum
	 * campo ser preenchido. Caso todos estejam preencidos e selecionados, é chamado
	 * o método 'fazerLogin' da classe LoginModel, passando o conteúdo dos campos da
	 * tela, e que terá um retorno em String, mostrando se o cadastro deu certo.
	 * 
	 * @author Cicero Romão
	 * 
	 * @return String - Dependendo de qual condição caia, será informado uma
	 *         mensagem específica.
	 */
	@Override
	public String verificarCampos() {
		if (txtEmail.getText().equals("")) {
			return "Preencha o seu Email.";
		} else if (txtSenha.getText().equals("")) {
			return "Preencha sua senha.";
		} else if (comboBoxEscolha.getSelectionModel().getSelectedIndex() == -1) {
			return "Escolha que tipo de usuário você é.";
		} else {
			return LoginModel.fazerLogin(comboBoxEscolha.getSelectionModel().getSelectedIndex(), txtEmail.getText(),
					txtSenha.getText());
		}
	}

	/**
	 * Método para poder chamar a função de desserializar os dados do arquivo
	 * 'arquivo.ser', antes de abrir a tela do login, isso serve para saber se ele
	 * marcou o checkBox se quer continuar conectado.
	 * 
	 * @author Cicero Romão
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			des();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método para poder serializar os dados do login do usuário, onde é criado um
	 * arquivo chamado 'verificacao.txt' e se o checkBox de 'continuar conectado'
	 * estiver marcado, ele faz o processo de serializar os dados do login no
	 * arquivo 'arquivo.ser', e no arquivo 'verificacao.txt' preenche com o valor 1,
	 * informando que quando a aplicação iniciar novamente, ele verifique e se for
	 * 1, preencha os campos da tela login ao inciar a tela.
	 * 
	 * @author Cicero Romão
	 */
	public void ser() throws IOException {
		FileWriter arq = null;
		arq = new FileWriter("verificacao.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		if (checkConectado.isSelected()) {
			Usuario usu = new Usuario(txtEmail.getText(), txtSenha.getText(), checkConectado.isSelected());
			SerializarUsuario ser = new SerializarUsuario(usu);
			ser.serializar();
			gravarArq.println(1);
		} else {
			gravarArq.println(0);
		}
		arq.close();
	}

	/**
	 * Método para poder desserializar os dados do usuário que estão no arquivo
	 * 'arquivo.ser'. Inicialmente é feito a leitura do arquivo 'verificacao.txt',
	 * se o conteúdo for 1, ele faz o processo de desserialização e carrega para os
	 * campos da tela do login.
	 * 
	 * @author Cicero Romão
	 */
	public void des() throws IOException {
		int flag = 0;

		BufferedReader buffRead = new BufferedReader(new FileReader("verificacao.txt"));
		flag = Integer.parseInt(buffRead.readLine());

		if (flag == 1) {
			DesserializarUsuario desser = new DesserializarUsuario("arquivo.ser");
			desser.desserializar();
			Usuario usu = desser.getLista();

			if (usu.isConectado()) {
				txtEmail.setText(usu.getNome());
				txtSenha.setText(usu.getSenha());
				checkConectado.setSelected(usu.isConectado());
			}
		}
	}

}
